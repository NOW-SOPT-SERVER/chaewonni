package org.sopt.daangnMarket.common.auth.service;

import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.common.auth.dto.CustomUserDetails;
import org.sopt.daangnMarket.domain.Member;
import org.sopt.daangnMarket.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member userData = memberRepository.findByUsername(username);

        if(userData != null) {

            return new CustomUserDetails(userData);
        }

        return null;
    }
}
