package cn.itbeien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * 启动程序
 * Copyright© 2024 itbeien
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class DtPayApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(DtPayApplication.class, args);
        System.out.println("  dtpay启动成功     \n" +
                " .-------.       ____     __        \n");
    }
}
