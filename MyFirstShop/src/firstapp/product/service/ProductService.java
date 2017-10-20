package firstapp.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import firstapp.product.dao.ProductDao;
import firstapp.product.vo.Product;
import firstapp.utils.PageBean;
//��transaction ���ǻ��õ��������� �޸�ɾ�����
@Transactional
public class ProductService {

	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	//������Ʒ
	public List<Product> findHot() {
		return productDao.findHot();
	}
	//������Ʒ
	public List<Product> findNew() {
		return productDao.findNew();
	}
	//��Ʒ����
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}
	//��ѯ�Ҳ����Ʒ����
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//ҳ��
		pageBean.setPage(page);
		//ÿҳ��ʾ����Ŀ
		int limit = 8;
		pageBean.setLimit(limit);
		//��ѯ�õ�����Ʒ����
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}
		else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//��ʼ��ѯ
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//�����������ѯ �Ҳ����Ʒ��Ϣ ����ҳ
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//ҳ��
		pageBean.setPage(page);
		//ÿҳ��ʾ����Ŀ
		int limit = 8;
		pageBean.setLimit(limit);
		//��ѯ�õ�����Ʒ����
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}
		else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//��ʼ��ѯ
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//��ҳ��ѯ��̨����Ʒ��
	public PageBean<Product> findAllByPage(int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//ҳ��
		pageBean.setPage(page);
		//ÿҳ��ʾ��¼��
		int limit = 10;
		pageBean.setLimit(limit);
		//�ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//��ҳ��
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
		//��ҳ�ļ���
		List<Product> list = productDao.findAllByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//������Ʒ�ķ���
	public void save(Product product) {

		productDao.save(product);
	}
	//ɾ����Ʒ
	public void delete(Product product) {
		productDao.delete(product);
	}
	public void update(Product product) {
		productDao.update(product);
	}
	
}
