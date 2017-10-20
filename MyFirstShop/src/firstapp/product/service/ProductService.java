package firstapp.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import firstapp.product.dao.ProductDao;
import firstapp.product.vo.Product;
import firstapp.utils.PageBean;
//加transaction 就是会用到其他操作 修改删除添加
@Transactional
public class ProductService {

	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	//热门商品
	public List<Product> findHot() {
		return productDao.findHot();
	}
	//最新商品
	public List<Product> findNew() {
		return productDao.findNew();
	}
	//商品详情
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}
	//查询右侧的商品详情
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//页数
		pageBean.setPage(page);
		//每页显示的数目
		int limit = 8;
		pageBean.setLimit(limit);
		//查询得到的商品总数
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}
		else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//起始查询
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//左侧二级分类查询 右侧的商品信息 带分页
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//页数
		pageBean.setPage(page);
		//每页显示的数目
		int limit = 8;
		pageBean.setLimit(limit);
		//查询得到的商品总数
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}
		else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//起始查询
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//分页查询后台的商品数
	public PageBean<Product> findAllByPage(int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//页数
		pageBean.setPage(page);
		//每页显示记录数
		int limit = 10;
		pageBean.setLimit(limit);
		//总记录数
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}
		else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = 0;
		begin = (page - 1)*limit;
		//分页的集合
		List<Product> list = productDao.findAllByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//保存商品的方法
	public void save(Product product) {

		productDao.save(product);
	}
	//删除商品
	public void delete(Product product) {
		productDao.delete(product);
	}
	public void update(Product product) {
		productDao.update(product);
	}
	
}
