package cn.itbeien.terminal.dao;

import cn.itbeien.terminal.vo.BankVO;
import cn.itbeien.terminal.vo.WtZfxtInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface WtZfxtInfoMapper {
    List<WtZfxtInfoVO> selectWtZfxtInfo(@Param("keyword") String keyword, @Param("page")Integer page, @Param("pageSize") Integer pageSize);
//    List<WtZfxtInfoVO> selectWtZfxtInfo(@Param("keyword") String keyword);
    Integer selectWtZfxtInfoCount(String keyword);

    List<BankVO> selectBankByHbdm(String hbdm);
}
