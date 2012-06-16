package com.truck.service;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.service.IdEntityService;

import com.truck.entity.Product;
import com.truck.util.PageModule;

/**
 * 产品服务类
 * 
 * @author shiChao
 */
public class ProductService extends IdEntityService<Product> {
	
	public ProductService() {
		super();
	}

	public ProductService(Dao dao) {
		super(dao);
	}

	public List<Product> getProductByPage(PageModule<Product> pageModule){
		Pager pager = this.dao().createPager(pageModule.getPage(), pageModule.getPageSize());
		return this.query(null, pager);
	}
	
	public List<Product> getAllProduct(){
		List<Product> productList = this.dao().query(Product.class, null, null);
		return productList;
	}
}
