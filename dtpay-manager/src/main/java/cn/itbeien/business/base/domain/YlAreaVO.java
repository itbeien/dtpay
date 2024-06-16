package cn.itbeien.business.base.domain;

import lombok.Data;

import java.util.List;

/**
 * @author beien
 * @date 2024-04-21 17:30
 * Copyright© 2024 beien
 */
@Data
public class YlAreaVO {
    private String value;

    private String label;

    private List<YlAreaVO> children;
}
