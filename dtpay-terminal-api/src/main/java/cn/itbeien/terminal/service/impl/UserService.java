package cn.itbeien.terminal.service.impl;

import cn.itbeien.common.core.domain.entity.SysUser;
import cn.itbeien.terminal.dao.SysUserMapper;
import cn.itbeien.terminal.service.IUserService;
import cn.itbeien.terminal.util.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Copyright© 2024 itbeien
 */
@Service
public class UserService implements IUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Value("${jwt.secret}")
    private String secret;

    /**
     * 实现用户注册功能
     * 用户使用小程序进程登录
     * 使用手机号在数据库中进行查询-》如果查询到数据(返回用户信息，包含token)
     * 如果没有查询到数据->注册用户数据(insert)
     * @param sysUser
     * @return
     */
    @Override
    public SysUser regUser(SysUser sysUser) {

        SysUser dbSysUser = this.sysUserMapper.findByPhone(sysUser.getPhone());
        if(dbSysUser == null){
            //String token = DtUtil.uuid();//token;
            //sysUser.setNickName(DtUtil.uuid16());//昵称
            sysUser.setNickName(sysUser.getPhone());
            sysUser.setUserName(sysUser.getPhone());//用户名
            sysUser.setPhonenumber(sysUser.getPhone());//手机号
            //如何为空则代表当前用户没有注册，进行自动注册
          int number =  this.sysUserMapper.insertUser(sysUser);
          sysUser.setToken(String.valueOf(JwtUtils.signUid(String.valueOf(sysUser.getUserId()),secret)));
          if(number<=0){
              throw new RuntimeException("注册用户失败!");
          }else{
              return sysUser;
          }
        }
        String token = JwtUtils.signUid(String.valueOf(dbSysUser.getUserId()),secret);
        dbSysUser.setToken(token);
        //该用户已经存在,返回当前用户信息
        return dbSysUser;
    }
}
