package com.firstclub.membership.membership_program.Repository;

import com.firstclub.membership.membership_program.Entity.UserMembership;
import com.firstclub.membership.membership_program.Enum.MembershipStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserMembershipRepository
        extends JpaRepository<UserMembership, Long> {

    Optional<UserMembership> findByUserIdAndStatus(
            Long userId,
            MembershipStatus status
    );
        List<UserMembership> findByStatusAndExpiryDateBefore(
        MembershipStatus status,
        LocalDate date
);
}

