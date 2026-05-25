package com.firstclub.membership.membership_program.Service;


import com.firstclub.membership.membership_program.DTO.MembershipResponse;
import com.firstclub.membership.membership_program.DTO.PlanResponse;
import com.firstclub.membership.membership_program.DTO.SubscribeRequest;

import java.util.List;

public interface MembershipService {

    List<PlanResponse> getPlans();

    MembershipResponse subscribe(SubscribeRequest request);

    MembershipResponse upgrade(Long userId);

    MembershipResponse downgrade(Long userId);

    void cancel(Long userId);

    MembershipResponse getCurrentMembership(Long userId);
}
