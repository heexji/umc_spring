package umc.study.apiPayload.code;
import umc.study.apiPayload.code.ReasonDTO;
//구체화 하는 Status에서 두 개의 메소드를 반드시 Override할 것을 강제하는 역할
public interface BaseCode {

    ReasonDTO getReason();

    ReasonDTO getReasonHttpStatus();
}