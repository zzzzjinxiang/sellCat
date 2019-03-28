package immoc.sell.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnums {
    UP(0,"UP"),
    DOWN(1,"DIE")
    ;
    private Integer code;
    private String message;

    ProductStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
