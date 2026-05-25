package com.firstclub.membership.membership_program.StrategyPattern;

import com.firstclub.membership.membership_program.DTO.UserMetrics;
import com.firstclub.membership.membership_program.Entity.MembershipTier;
import org.springframework.stereotype.Component;

@Component
public class CompositeTierEligibilityStrategy
        implements TierEligibilityStrategy {

    @Override
    public boolean isEligible(
            UserMetrics metrics,
            MembershipTier tier
    ) {
        /*
         Simplified for assignment.
         In production parse criteriaJson dynamically.
         */

        if (tier.getRank() == 1) {
            return true;
        }

        if (tier.getRank() == 2) {
            return metrics.getOrderCount() >= 10 &&
                    metrics.getTotalOrderValue()
                            .intValue() >= 5000;
        }

        if (tier.getRank() == 3) {
            return metrics.getOrderCount() >= 25 &&
                    metrics.getTotalOrderValue()
                            .intValue() >= 15000;
        }

        return false;
    }
}
