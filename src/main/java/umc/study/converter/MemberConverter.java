package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.enums.Gender;
import umc.study.web.dto.MemberRequestDto;
import umc.study.web.dto.MemberResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDto.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDto.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDto.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())   // 추가된 코드
                .password(request.getPassword())   // 추가된 코드
                .gender(gender)
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .role(request.getRole())   // 추가된 코드
                .memberPreferList(new ArrayList<>())
                .build();
    }

    // 새로 추가된 메서드 - Member 엔티티를 MemberInfoDTO로 변환
    public static MemberResponseDto.MemberInfoDTO toMemberInfoDTO(Member member) {
        return MemberResponseDto.MemberInfoDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .gender(member.getGender().toString()) // Gender enum을 String으로 변환
                .build();
    }
}