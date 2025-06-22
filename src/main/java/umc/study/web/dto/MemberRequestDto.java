package umc.study.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import umc.study.domain.enums.Role;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class JoinDto {

        @NotBlank
        private String name;

        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String password;

        @NotNull
        private Integer gender;

        @NotNull
        private Integer birthYear;

        @NotNull
        private Integer birthMonth;

        @NotNull
        private Integer birthDay;

        @NotBlank
        @Size(min = 5, max = 12)
        private String address;

        @NotBlank
        @Size(min = 5, max = 12)
        private String specAddress;

        @NotNull
        @ExistCategories
        private List<Long> preferCategory;

        @NotNull
        private Role role;
    }

    @Getter
    @Setter
    public static class LoginRequestDTO {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message = "패스워드는 필수입니다.")
        private String password;
    }
}
