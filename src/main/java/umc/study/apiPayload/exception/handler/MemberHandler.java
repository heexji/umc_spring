package umc.study.apiPayload.exception.handler;

import lombok.Getter;
import umc.study.apiPayload.code.status.ErrorStatus;

@Getter
public class MemberHandler extends RuntimeException {

    private final ErrorStatus errorStatus;

    public MemberHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }
}
