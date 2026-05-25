package com.firstclub.membership.membership_program.Repository;

import com.firstclub.membership.membership_program.Entity.MembershipTier;
import com.firstclub.membership.membership_program.Enum.TierType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipTierRepository
        extends JpaRepository<MembershipTier, Long> {

    Optional<MembershipTier> findByTierType(TierType tierType);
}
