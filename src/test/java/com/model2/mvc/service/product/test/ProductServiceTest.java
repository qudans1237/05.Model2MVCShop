package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	//@Test
	public void testAddProduct() throws Exception{
		
		Product product = new Product();
		product.setProdName("���̹�Ƽ��");
		product.setProdDetail("ù�׽�Ʈ");
		product.setManuDate("20220407");
		product.setPrice(7777);
		product.setFileName("�̹���");
		
		productService.addProduct(product);
		System.out.println(product);
		product = productService.getProduct(10048);
		
		//==> console Ȯ��
		System.out.println(product);
		
		//==> API Ȯ��
		Assert.assertEquals(10038, product.getProdNo());
		Assert.assertEquals("���̹�Ƽ��", product.getProdName());
		Assert.assertEquals("ù�׽�Ʈ", product.getProdDetail());
		Assert.assertEquals("20220407", product.getManuDate());
		Assert.assertEquals(7777, product.getPrice());
		Assert.assertEquals("�̹���", product.getFileName());
	}
	
	//@Test
	public void testGetProduct() throws Exception{
		Product product = new Product();
		
		product = productService.getProduct(10048);
		
		Assert.assertEquals(10047, product.getProdNo());
		Assert.assertEquals("���̹�Ƽ��", product.getProdName());
		Assert.assertEquals("ù�׽�Ʈ", product.getProdDetail());
		Assert.assertEquals("20220407", product.getManuDate());
		Assert.assertEquals(7777, product.getPrice());
		Assert.assertEquals("�̹���", product.getFileName());
		
		Assert.assertNotNull(productService.getProduct(10019));
		Assert.assertNotNull(productService.getProduct(10038));
	}
	
	//@Test
	public void testUpdateProduct() throws Exception{
		
		Product product = productService.getProduct(10048);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("���̹�Ƽ������", product.getProdName());
		Assert.assertEquals("ù�׽�Ʈ����", product.getProdDetail());
		Assert.assertEquals("20220408", product.getManuDate());
		Assert.assertEquals(28504030, product.getPrice());
		Assert.assertEquals("�ٲ��̹���", product.getFileName());
		
		product.setProdName("��Ƽ������");
		product.setProdDetail("�׽�Ʈ����");
		product.setManuDate("20220407");
		product.setPrice(7777);
		product.setFileName("�̹���");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10048);
		Assert.assertNotNull(product);
		
		//==> console Ȯ��
		System.out.println(product);
		
		//==> API Ȯ��
		Assert.assertEquals("��Ƽ������", product.getProdName());
		Assert.assertEquals("�׽�Ʈ����", product.getProdDetail());
		Assert.assertEquals("20220407", product.getManuDate());
		Assert.assertEquals(7777, product.getPrice());
		Assert.assertEquals("�̹���", product.getFileName());
	}
	
	@Test
	public void testGetUserListAll() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("===================================");
		
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		
		System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
	
	//@Test
	public void testGetProductListByProdNo() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("48");
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
		
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	}
	
	
	//@Test
	public void testGetProductListByProdName() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("���̹�Ƽ��");
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(2, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	}
	
	
}
