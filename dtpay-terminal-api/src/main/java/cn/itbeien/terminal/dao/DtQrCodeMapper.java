package cn.itbeien.terminal.dao;


import cn.itbeien.terminal.entity.DtQrCode;

public interface DtQrCodeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(DtQrCode row);

    int insertSelective(DtQrCode row);


    DtQrCode selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(DtQrCode row);

    int updateByPrimaryKey(DtQrCode row);
}