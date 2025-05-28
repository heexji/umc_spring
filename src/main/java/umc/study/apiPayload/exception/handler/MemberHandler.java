package umc.study.apiPayload.exception.handler;

import lombok.Getter;

@Getter
public class MemberHandler extends RuntimeException {

    private final String message;

    public MemberHandler(String message) {
        super(message);
        this.message = message;
    }
}
