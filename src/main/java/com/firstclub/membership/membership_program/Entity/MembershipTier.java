package com.firstclub.membership.membership_program.Entity;

import com.firstclub.membership.membership_program.Enum.TierType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "membership_tiers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TierType tierType;

    @Column(nullable = false)
    private Integer rank;

    @Column(nullable = false)
    private Boolean freeDelivery;

    @Column(nullable = false)
    private Integer discountPercent;

    @Column(nullable = false)
    private Boolean prioritySupport;

    @Column(nullable = false)
    private Boolean earlyAccess;

    /*
      JSON-like config for extensibility
      Example:
      {
        "minOrderCount": 10,
        "minOrderValue": 5000,
        "eligibleCohorts": ["PREMIUM_CITY"]
      }
     */
    @Column(columnDefinition = "TEXT")
    private String criteriaJson;
}