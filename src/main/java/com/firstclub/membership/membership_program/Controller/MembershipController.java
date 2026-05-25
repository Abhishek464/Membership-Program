package com.firstclub.membership.membership_program.Controller;

import com.firstclub.membership.membership_program.DTO.MembershipResponse;
import com.firstclub.membership.membership_program.DTO.PlanResponse;
import com.firstclub.membership.membership_program.DTO.SubscribeRequest;
import com.firstclub.membership.membership_program.Service.MembershipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @GetMapping("/plans")
    public List<PlanResponse> getPlans() {
        return membershipService.getPlans();
    }

    @PostMapping("/subscribe")
    public MembershipResponse subscribe(
            @Valid @RequestBody SubscribeRequest request
    ) {
        return membershipService.subscribe(request);
    }

    @PutMapping("/{userId}/upgrade")
    public MembershipResponse upgrade(
            @PathVariable Long userId
    ) {
        return membershipService.upgrade(userId);
    }

    @PutMapping("/{userId}/downgrade")
    public MembershipResponse downgrade(
            @PathVariable Long userId
    ) {
        return membershipService.downgrade(userId);
    }

    @PutMapping("/{userId}/cancel")
    public void cancel(
            @PathVariable Long userId
    ) {
        membershipService.cancel(userId);
    }

    @GetMapping("/{userId}")
    public MembershipResponse getCurrentMembership(
            @PathVariable Long userId
    ) {
        return membershipService.getCurrentMembership(userId);
    }
}
