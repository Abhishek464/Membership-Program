package com.firstclub.membership.membership_program;

import com.firstclub.membership.membership_program.Mapper.MembershipMapper;
import com.firstclub.membership.membership_program.Repository.MembershipPlanRepository;
import com.firstclub.membership.membership_program.Repository.MembershipTierRepository;
import com.firstclub.membership.membership_program.Repository.UserMembershipRepository;
import com.firstclub.membership.membership_program.Service.MembershipServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceImplTest {
    @Mock
    private MembershipPlanRepository membershipPlanRepository;

    @Mock
    private MembershipTierRepository membershipTierRepository;

    @Mock
    private UserMembershipRepository userMembershipRepository;

    @Mock
    private MembershipMapper membershipMapper;

    @InjectMocks
    private MembershipServiceImpl membershipService;

    @Test
    void shouldSubscribeSuccessfully() {}
    @Test
    void shouldThrowWhenActiveMembershipExists() {}
    @Test
    void shouldUpgradeSilverToGold() {}
    @Test
    void shouldCancelMembership() {}
    @Test
    void shouldDowngradePlatinumToGold() {}

}
