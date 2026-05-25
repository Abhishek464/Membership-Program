package com.firstclub.membership.membership_program.Service;

import com.firstclub.membership.membership_program.DTO.MembershipResponse;
import com.firstclub.membership.membership_program.DTO.PlanResponse;
import com.firstclub.membership.membership_program.DTO.SubscribeRequest;
import com.firstclub.membership.membership_program.Entity.MembershipPlan;
import com.firstclub.membership.membership_program.Entity.MembershipTier;
import com.firstclub.membership.membership_program.Entity.UserMembership;
import com.firstclub.membership.membership_program.Enum.MembershipStatus;
import com.firstclub.membership.membership_program.Enum.TierType;
import com.firstclub.membership.membership_program.Exception.BusinessException;
import com.firstclub.membership.membership_program.Exception.ResourceNotFoundException;
import com.firstclub.membership.membership_program.Mapper.MembershipMapper;
import com.firstclub.membership.membership_program.Repository.MembershipPlanRepository;
import com.firstclub.membership.membership_program.Repository.MembershipTierRepository;
import com.firstclub.membership.membership_program.Repository.UserMembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipPlanRepository membershipPlanRepository;
    private final MembershipTierRepository membershipTierRepository;
    private final UserMembershipRepository userMembershipRepository;
    private final MembershipMapper membershipMapper;

    @Override
    public List<PlanResponse> getPlans() {
        List<MembershipPlan> plans =
                membershipPlanRepository.findByActiveTrue();

        List<MembershipTier> tiers =
                membershipTierRepository.findAll();

        return plans.stream()
                .map(plan -> membershipMapper.toPlanResponse(plan, tiers))
                .toList();
    }

    @Override
    @Transactional
    public MembershipResponse subscribe(
            SubscribeRequest request
    ) {

        userMembershipRepository.findByUserIdAndStatus(
                request.getUserId(),
                MembershipStatus.ACTIVE
        ).ifPresent(membership -> {
            throw new BusinessException(
                    "User already has an active membership"
            );
        });

        MembershipPlan plan =
                membershipPlanRepository.findById(request.getPlanId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Membership plan not found"
                                ));

        MembershipTier tier =
                membershipTierRepository.findById(request.getTierId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Membership tier not found"
                                ));

        UserMembership membership =
                UserMembership.builder()
                        .userId(request.getUserId())
                        .membershipPlan(plan)
                        .membershipTier(tier)
                        .status(MembershipStatus.ACTIVE)
                        .startDate(LocalDate.now())
                        .expiryDate(
                                LocalDate.now()
                                        .plusDays(plan.getDurationDays())
                        )
                        .build();

        userMembershipRepository.save(membership);

        return membershipMapper.toMembershipResponse(membership);
    }

    @Override
    @Transactional(readOnly = true)
    public MembershipResponse getCurrentMembership(Long userId) {

        UserMembership membership = getActiveMembership(userId);

        return membershipMapper.toMembershipResponse(membership);
    }

    @Override
    @Transactional
    public void cancel(Long userId) {

        UserMembership membership = getActiveMembership(userId);

        membership.setStatus(MembershipStatus.CANCELLED);

        userMembershipRepository.save(membership);
    }

    @Override
    @Transactional
    public MembershipResponse upgrade(Long userId) {

        UserMembership membership = getActiveMembership(userId);

        TierType currentTier =
                membership.getMembershipTier().getTierType();

        TierType nextTier = switch (currentTier) {
            case SILVER -> TierType.GOLD;
            case GOLD -> TierType.PLATINUM;
            case PLATINUM ->
                    throw new BusinessException(
                            "Already at highest tier"
                    );
        };

        MembershipTier upgradedTier =
                membershipTierRepository.findByTierType(nextTier)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Tier not found"
                                ));

        membership.setMembershipTier(upgradedTier);

        userMembershipRepository.save(membership);

        return membershipMapper.toMembershipResponse(membership);
    }

    @Override
    @Transactional
    public MembershipResponse downgrade(Long userId) {

        UserMembership membership = getActiveMembership(userId);

        TierType currentTier =
                membership.getMembershipTier().getTierType();

        TierType lowerTier = switch (currentTier) {
            case PLATINUM -> TierType.GOLD;
            case GOLD -> TierType.SILVER;
            case SILVER ->
                    throw new BusinessException(
                            "Already at lowest tier"
                    );
        };

        MembershipTier downgradedTier =
                membershipTierRepository.findByTierType(lowerTier)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Tier not found"
                                ));

        membership.setMembershipTier(downgradedTier);

        userMembershipRepository.save(membership);

        return membershipMapper.toMembershipResponse(membership);
    }

    private UserMembership getActiveMembership(Long userId) {
        return userMembershipRepository.findByUserIdAndStatus(
                userId,
                MembershipStatus.ACTIVE
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Active membership not found"
                ));
    }
}
