package cn.itbeien.common.vo;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * @author itbeien
 * 项目网站：https://www.itbeien.cn
 * 公众号：贝恩聊架构
 * 全网同名，欢迎小伙伴们关注
 * Java/AI/支付系统/SAAS多租户基础技术平台学习社群
 * Copyright© 2025 itbeien
 */
@Data
public class BootTable<T> {

	public long total; // 总记录数
	public int pages; // 总页数
	private List<T> list;  //结果集
	
	public BootTable(List<T> list){
        if (list instanceof Page){
            Page<T> page = (Page<T>) list;
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list = page;
        }
    }

}
