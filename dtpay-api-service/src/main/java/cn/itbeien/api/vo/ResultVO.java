package cn.itbeien.api.vo;

import lombok.Data;

/**
 * @author beien
 * @date 2024-04-06 0:22
 * Copyright© 2024 beien
 */
@Data
public class ResultVO<T> {
    private String code;
    private T data;
    private String message;
}
