package com.firstclub.membership.membership_program.Mapper;


import com.firstclub.membership.membership_program.DTO.MembershipResponse;
import com.firstclub.membership.membership_program.DTO.PlanResponse;
import com.firstclub.membership.membership_program.DTO.TierResponse;
import com.firstclub.membership.membership_program.Entity.MembershipPlan;
import com.firstclub.membership.membership_program.Entity.MembershipTier;
import com.firstclub.membership.membership_program.Entity.UserMembership;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MembershipMapper {

    public MembershipResponse toMembershipResponse(
            UserMembership membership
    ) {
        return MembershipResponse.builder()
                .membershipId(membership.getId())
                .userId(membership.getUserId())
                .planName(
                        membership.getMembershipPlan()
                                .getPlanType()
                                .name()
                )
                .tierName(
                        membership.getMembershipTier()
                                .getTierType()
                                .name()
                )
                .status(membership.getStatus().name())
                .startDate(membership.getStartDate())
                .expiryDate(membership.getExpiryDate())
                .build();
    }

    public PlanResponse toPlanResponse(
            MembershipPlan plan,
            List<MembershipTier> tiers
    ) {
        return PlanResponse.builder()
                .planId(plan.getId())
                .planName(plan.getPlanType().name())
                .durationDays(plan.getDurationDays())
                .price(plan.getPrice())
                .tiers(
                        tiers.stream()
                                .map(this::toTierResponse)
                                .toList()
                )
                .build();
    }

    public TierResponse toTierResponse(
            MembershipTier tier
    ) {
        return TierResponse.builder()
                .tierId(tier.getId())
                .tierName(tier.getTierType().name())
                .discountPercent(tier.getDiscountPercent())
                .freeDelivery(tier.getFreeDelivery())
                .prioritySupport(tier.getPrioritySupport())
                .earlyAccess(tier.getEarlyAccess())
                .build();
    }
}