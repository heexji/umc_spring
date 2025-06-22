package umc.study.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import umc.study.web.dto.MemberRequestDto;

import umc.study.service.MemberService.MemberCommandService;

import java.security.Principal;

@Profile("session")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class SessionAuthController {

    private final MemberCommandService memberCommandService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody @Valid MemberRequestDto.JoinDto joinDto) {
        memberCommandService.joinMember(joinDto);
        return ResponseEntity.ok("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid MemberRequestDto.LoginRequestDTO loginDto,
                                        HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpSession session = request.getSession(true); // 세션 생성
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return ResponseEntity.ok("✅ 세션 로그인 성공");
    }

    @GetMapping("/info")
    public ResponseEntity<String> info(Principal principal) {
        return ResponseEntity.ok("로그인된 사용자: " + principal.getName());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession(false).invalidate();
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
}
