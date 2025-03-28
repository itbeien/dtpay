package cn.itbeien.common.vo;

import java.io.Serializable;

public class BaseBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int pageNum;	// 第几页
	private int pageSize;   // 每页显示几条
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
