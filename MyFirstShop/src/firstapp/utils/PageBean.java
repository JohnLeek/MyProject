package firstapp.utils;

import java.util.List;
//查询右侧商品页数  总数 个数的封装
public class PageBean<T> {

	private int page;//页数	
	private int totalCount;//总数 
	private int totalPage; //总页数
	private int limit;//每页个数
	private List<T> list;//商品的集合
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
