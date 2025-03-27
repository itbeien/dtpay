package cn.itbeien.task.service;

import cn.itbeien.task.entity.AgentOrg;
import cn.itbeien.task.entity.MerchantInfo;

import java.util.List;

public interface IMerchantService {

    public List<MerchantInfo> findMerchant();
    public void generateMerchantReport(String merchantNo)throws Exception;
}
