package com.firstclub.membership.membership_program.Config;


import com.firstclub.membership.membership_program.Entity.UserMembership;
import com.firstclub.membership.membership_program.Enum.MembershipStatus;
import com.firstclub.membership.membership_program.Repository.UserMembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MembershipExpiryScheduler {

    private final UserMembershipRepository userMembershipRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void expireMemberships() {

        List<UserMembership> memberships =
                userMembershipRepository
                        .findByStatusAndExpiryDateBefore(
                                MembershipStatus.ACTIVE,
                                LocalDate.now()
                        );

        memberships.forEach(m ->
                m.setStatus(MembershipStatus.EXPIRED)
        );

        userMembershipRepository.saveAll(memberships);
    }
}
