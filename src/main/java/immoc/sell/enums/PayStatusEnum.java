package immoc.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    SUCCESS(0,"支付"),
    WAIT(1,"未支付"),
    ;
    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
