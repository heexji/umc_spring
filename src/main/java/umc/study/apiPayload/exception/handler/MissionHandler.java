package umc.study.apiPayload.exception.handler;

import lombok.Getter;

@Getter
public class MissionHandler extends RuntimeException {

    private final String message;

    public MissionHandler(String message) {
        super(message);
        this.message = message;
    }
}
