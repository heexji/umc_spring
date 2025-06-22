package umc.study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umc.study.domain.Member;
import umc.study.repository.MemberRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MemberLoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자"));
        return new User(member.getEmail(), member.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(member.getRole().name())));
    }
}
