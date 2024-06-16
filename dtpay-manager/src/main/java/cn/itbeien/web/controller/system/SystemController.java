package cn.itbeien.web.controller.system;

import cn.itbeien.business.base.domain.YlAreaVO;
import cn.itbeien.business.base.service.impl.SystemService;
import cn.itbeien.common.core.controller.BaseController;
import cn.itbeien.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author beien
 * @date 2024-05-27 15:49
 * Copyright© 2024 beien
 */
@RestController
@RequestMapping("/dt/area")
public class SystemController extends BaseController {

    @Autowired
    private SystemService systemService;

    /**
     * 获取所有的省份数据
     */
    @PreAuthorize("@dss.hasPermi('system:area:query')")
    @GetMapping("/ylArea")
    public AjaxResult ylAreaselect(String parentCode)
    {
        if(!"0".equals(parentCode)){
            //根据yl_code查id
            parentCode =   this.systemService.selectYlIdAreaByCode(parentCode);
        }
        List<YlAreaVO> ylAreaVOS = this.systemService.selectYlArea(parentCode);//省
        return success(ylAreaVOS);
    }

}
