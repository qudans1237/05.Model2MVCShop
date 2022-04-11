package com.model2.mvc.service.purchase.test;

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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.purchase.PurchaseService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	 //@Test
		public void testAddPurchase() throws Exception {

			User buyer = new User();
			Product purchaseProd = new Product();

			Purchase purchase = new Purchase();

			purchaseProd.setProdNo(10047);
			buyer.setUserId("user03");

			purchase.setPurchaseProd(purchaseProd);
			purchase.setBuyer(buyer);
			purchase.setPaymentOption("1");
			purchase.setReceiverName("테스트");
			purchase.setReceiverPhone("010-1111-2222");
			purchase.setDivyAddr("테스트입니다");
			purchase.setDivyRequest("빠른배송RG");
			purchase.setTranCode("001");
			purchase.setDivyDate("20220411");

			purchaseService.addPurchase(purchase);
			System.out.println("addpurchase 값 확인 " + purchase);

			// purchase = purchaseService.getPurchase2(11111);
			// System.out.println("prodNo1111값 확인 " +purchaseService.getPurchase2(11111));

			Assert.assertEquals(10047, purchase.getPurchaseProd().getProdNo());
			Assert.assertEquals("user03", purchase.getBuyer().getUserId());
			Assert.assertEquals("테스트입니다", purchase.getDivyAddr());
			Assert.assertEquals("테스트", purchase.getReceiverName());

		}

		@Test
		public void testGetPurchase() throws Exception {

			purchaseService.getPurchase2(10019);
			purchaseService.getPurchase(10010);

			System.out.println(purchaseService.getPurchase2(10019));
			System.out.println(purchaseService.getPurchase(10010));

			Assert.assertNotNull(purchaseService.getPurchase2(10019));
			Assert.assertNotNull(purchaseService.getPurchase(10010));

		}

		//@Test
		public void testUpdatePurchase() throws Exception {

			Purchase purchase = purchaseService.getPurchase(10010);

			System.out.println(purchase);

			Assert.assertNotNull(purchase);

			Assert.assertEquals("vaio구매", purchase.getReceiverName());
			Assert.assertEquals(10019, purchase.getPurchaseProd().getProdNo());

			purchase.setPaymentOption("1");
			purchase.setReceiverName("택배왔쑝");
			purchase.setReceiverPhone("010-1234-5678");
			purchase.setDivyAddr("제주도");
			purchase.setDivyRequest("테스트용입니다");
			purchase.setDivyDate("20220411");

			purchaseService.updatePurchase(purchase);

			purchase = purchaseService.getPurchase(10010);

			Assert.assertNotNull(purchase);
			System.out.println(purchaseService.getPurchase(10010));

		}

		//@Test
		public void testGetPurchaseListAll() throws Exception {

			Search search = new Search();
			search.setCurrentPage(1);
			search.setPageSize(3);
			

			User user = new User();
			user.setUserId("user03");

			Map<String, Object> map = purchaseService.getPurchaseList(search, "user03");
			
			
			List<Object> list = (List<Object>) map.get("list");
			Assert.assertEquals(3, list.size());

			// ==> console 확인
			// System.out.println(list);

			Integer totalCount = (Integer) map.get("totalCount");
			System.out.println(totalCount);

			System.out.println("=======================================");

			search.setCurrentPage(1);
			search.setPageSize(3);
			map = purchaseService.getPurchaseList(search, "user03");
			

			list = (List<Object>) map.get("list");
			Assert.assertEquals(3, list.size());

			// ==> console 확인
			// System.out.println(list);

			totalCount = (Integer) map.get("totalCount");
			System.out.println(map);
		}
		//@Test
		public void testUpdateTranCode() throws Exception {
			
			Purchase purchase = purchaseService.getPurchase(10010);

			System.out.println(purchase);

			Assert.assertNotNull(purchase);

			Assert.assertEquals("택배왔쑝", purchase.getReceiverName());
			Assert.assertEquals(10019, purchase.getPurchaseProd().getProdNo());

			purchase.setTranCode("002");
			
			purchaseService.updateTranCode(purchase);

			purchase = purchaseService.getPurchase(10010);

			Assert.assertNotNull(purchase);
			System.out.println(purchaseService.getPurchase(10010));

		}

	}