package com.truck.mvc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.truck.dto.ProductDTO;
import com.truck.entity.Attribute;
import com.truck.entity.AttributeType;
import com.truck.entity.AttributeValue;
import com.truck.entity.FileInfo;
import com.truck.entity.Product;
import com.truck.service.ProductService;
import com.truck.util.PageModule;

/**
 * 商品模块类
 * @author shiChao
 *
 */
@InjectName("productModule")
@At("/product")
@Fail("json")
public class ProductModule {

	private ProductService productService;
	
	/**
	 * 查询所有的商品
	 * @return 商品列表
	 */
	@At
	@Ok("json")
	public PageModule<Product> allByPage(@Param("page") int page, @Param("rows") int pageSize) {
		PageModule<Product> pageModule = new PageModule<Product>(page, productService.count(), pageSize);
		List<Product> productList = productService.getProductByPage(pageModule);
		pageModule.setRows(productList);
		return pageModule;
	}
	
	@At
	@Ok("json")
	public List<ProductDTO> getAll(){
		List<Product> productList = productService.getAllProduct();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : productList) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(product.getId());
			productDTO.setAuthor(product.getAuthor());
			productDTO.setName(product.getName());
			productDTO.setContent(product.getContent());
			productDTO.setPorder(product.getPorder());
			List<FileInfo> fileInfos = productService.dao().query(
					FileInfo.class,
					Cnd.where("proId", "=", product.getId()).desc("id"), null);
			if (fileInfos !=null && fileInfos.size() > 0) {
				productDTO.setPicPath(fileInfos.get(0).getPath());
			}
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	
	/**
	 * 保存新的产品信息
	 * @param attributeValues
	 * @param request
	 * @return 新增产品的ID
	 */
	@At
	@Ok("json")
	@Fail("json")
	public int saveNewProduct(@Param("::product.") Product product) {
		product.setLastUpdateDate(new Date());
		if (product.getId() != 0) {
			productService.dao().update(product);
		} else {
			productService.dao().insert(product);
		}
		return product.getId();
	}
	
	@At
	@Ok("json")
	public ProductDTO getProductById(@Param("proId") int proId) {
		Product product = productService.fetch(proId);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setAuthor(product.getAuthor());
		productDTO.setName(product.getName());
		productDTO.setContent(product.getContent());
		productDTO.setPorder(product.getPorder());
		productDTO.setLastUpdateDate(product.getLastUpdateDate());
		List<FileInfo> fileInfos = productService.dao().query(FileInfo.class,
				Cnd.where("proId", "=", product.getId()).desc("id"), null);
		if (fileInfos != null && fileInfos.size() > 0) {
			productDTO.setPicPath(fileInfos.get(0).getPath());
			List<String> pics = new ArrayList<String>();
			for (int i = 1; i < fileInfos.size(); i++) {
				String picPath = fileInfos.get(i).getPath();
				pics.add(picPath);
			}
			productDTO.setElsePics(pics);
		}
		Sql sql = Sqls
				.create("SELECT * FROM t_attributetype t2 WHERE t2.id IN( SELECT t1.attributeTypeId FROM t_attributevalue t1 WHERE t1.productId = @proId GROUP BY t1.attributeTypeId) ORDER BY t2.id");
		sql.params().set("proId", proId);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(productService.dao().getEntity(AttributeType.class));
		productService.dao().execute(sql);
		productDTO.setProTypes(sql.getList(AttributeType.class));
		
		Sql sql2 = Sqls.create("SELECT * FROM t_attributevalue t WHERE t.productId = @proId ORDER BY attributeId,attributeTypeId");
		sql2.params().set("proId", proId);
		sql2.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<AttributeValue> list = new LinkedList<AttributeValue>();
				while (rs.next()) {
					AttributeValue attributeValue = new AttributeValue();
					attributeValue.setId(rs.getInt("id"));
					attributeValue.setText(rs.getString("text"));
					Attribute attribute = productService.dao().fetch(Attribute.class, rs.getInt("attributeId"));
					attributeValue.setAttribute(attribute);
					list.add(attributeValue);
				}
				return list;
			}
		});
//		sql2.setEntity(productService.dao().getEntity(AttributeValue.class));
		productService.dao().execute(sql2);
		List<AttributeValue> attributeValueList = sql2.getList(AttributeValue.class);
		
		LinkedHashMap<String, List<String>> attributeValues = new LinkedHashMap<String, List<String>>();
		for (AttributeValue attribute : attributeValueList) {
			List<String> values = new ArrayList<String>();
			String attributeName = attribute.getAttribute().getName();
			if (attributeValues.containsKey(attributeName)) {
				values = attributeValues.get(attributeName);
			}
			values.add(attribute.getText());
			attributeValues.put(attributeName, values);
		}
		productDTO.setAttributeValues(attributeValues);
		return productDTO;
	}
	
	@At
	@Ok("json")
	public ProductDTO getProductByIdByEdit(@Param("proId") int proId) {
		Product product = productService.fetch(proId);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setAuthor(product.getAuthor());
		productDTO.setName(product.getName());
		productDTO.setContent(product.getContent());
		productDTO.setPorder(product.getPorder());
		productDTO.setLastUpdateDate(product.getLastUpdateDate());
		List<FileInfo> fileInfos = productService.dao().query(FileInfo.class,
				Cnd.where("proId", "=", product.getId()).desc("id"), null);
		if (fileInfos != null && fileInfos.size() > 0) {
			List<String> pics = new ArrayList<String>();
			for (int i = 0; i < fileInfos.size(); i++) {
				int picId = fileInfos.get(i).getId();
				String picPath = fileInfos.get(i).getPath();
				pics.add(picId + ":" + picPath);
			}
			productDTO.setElsePics(pics);
		}
		Sql sql = Sqls
				.create("SELECT * FROM t_attributetype t2 WHERE t2.id IN( SELECT t1.attributeTypeId FROM t_attributevalue t1 WHERE t1.productId = @proId GROUP BY t1.attributeTypeId) ORDER BY t2.id");
		sql.params().set("proId", proId);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(productService.dao().getEntity(AttributeType.class));
		productService.dao().execute(sql);
		productDTO.setProTypes(sql.getList(AttributeType.class));
		
		Sql sql2 = Sqls.create("SELECT * FROM t_attributevalue t WHERE t.productId = @proId ORDER BY attributeId,attributeTypeId");
		sql2.params().set("proId", proId);
		sql2.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<AttributeValue> list = new LinkedList<AttributeValue>();
				while (rs.next()) {
					AttributeValue attributeValue = new AttributeValue();
					attributeValue.setId(rs.getInt("id"));
					attributeValue.setText(rs.getString("text"));
					Attribute attribute = productService.dao().fetch(Attribute.class, rs.getInt("attributeId"));
					attributeValue.setAttribute(attribute);
					list.add(attributeValue);
				}
				return list;
			}
		});
//		sql2.setEntity(productService.dao().getEntity(AttributeValue.class));
		productService.dao().execute(sql2);
		List<AttributeValue> attributeValueList = sql2.getList(AttributeValue.class);
		
		LinkedHashMap<String, List<String>> attributeValues = new LinkedHashMap<String, List<String>>();
		for (AttributeValue attribute : attributeValueList) {
			List<String> values = new ArrayList<String>();
			int attributeId = attribute.getAttribute().getId();
			if (attributeValues.containsKey(String.valueOf(attributeId))) {
				values = attributeValues.get(String.valueOf(attributeId));
			}
			values.add(attribute.getText());
			attributeValues.put(String.valueOf(attributeId), values);
		}
		productDTO.setAttributeValues(attributeValues);
		return productDTO;
	}
	
	@At
	@Ok("json")
	public boolean delPro(@Param("proId") int proId) {
		productService.dao().execute(Sqls.create("delete FROM t_attributevalue where productId="+proId));
		return productService.delete(proId) > 0;
	}
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
