package com.firstclub.membership.membership_program.StrategyPattern;

import com.firstclub.membership.membership_program.DTO.UserMetrics;
import com.firstclub.membership.membership_program.Entity.MembershipTier;

public interface TierEligibilityStrategy {

    boolean isEligible(
            UserMetrics metrics,
            MembershipTier tier
    );
}
