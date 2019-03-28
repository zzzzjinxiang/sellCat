package immoc.sell.VO;

import lombok.Data;

@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    //返回的具体内容T
    private T data;

}
