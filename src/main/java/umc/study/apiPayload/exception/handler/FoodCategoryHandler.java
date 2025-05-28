package umc.study.apiPayload.exception.handler;

import umc.study.apiPayload.code.status.ErrorStatus;

public class FoodCategoryHandler extends RuntimeException {

    private final ErrorStatus errorStatus;

    public FoodCategoryHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    public ErrorStatus getErrorStatus() {
        return errorStatus;
    }
}
