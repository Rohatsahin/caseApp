package com.trendyol.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.trendyol.builders.category.CategoryBuilder;
import com.trendyol.builders.coupon.CouponBuilder;
import com.trendyol.builders.product.ProductBuilder;
import com.trendyol.constants.TestConstants;
import com.trendyol.data.SampleCampaignSource;
import com.trendyol.data.SampleData;
import com.trendyol.entity.campaign.Campaign;
import com.trendyol.entity.category.Category;
import com.trendyol.entity.coupon.Coupon;
import com.trendyol.entity.enums.DiscountType;
import com.trendyol.entity.product.Product;
import com.trendyol.service.CampaignCalculator;
import com.trendyol.service.CouponCalculator;
import com.trendyol.service.DeliveryCostCalculator;
import com.trendyol.service.impl.CampaignCalculatorImpl;
import com.trendyol.service.impl.CouponCalculatorImpl;
import com.trendyol.service.impl.DeliveryCostCalculatorImpl;

public class ShoppingCardTest {

	private DeliveryCostCalculator deliveryCostCalculator;

	private CampaignCalculator campaignCalculator;

	private CouponCalculator couponCalculator;

	private ShoppingCard shoppingCard;

	@Before
	public void init() {
		deliveryCostCalculator = new DeliveryCostCalculatorImpl(TestConstants.costPerDelivery,
				TestConstants.costPerProduct, TestConstants.fixedCost);
		couponCalculator = new CouponCalculatorImpl();
		List<Campaign> campaigns = Arrays.asList(SampleData.campaign1, SampleData.campaign2, SampleData.campaign3);
		SampleCampaignSource sampleCampaignSource = new SampleCampaignSource(campaigns);
		campaignCalculator = new CampaignCalculatorImpl(sampleCampaignSource);

		shoppingCard = new ShoppingCard();
		shoppingCard.setDeliveryCostCalculator(deliveryCostCalculator);
		shoppingCard.setCouponService(couponCalculator);
		shoppingCard.setCampaignService(campaignCalculator);
	}

	@After
	public void clear() {
		deliveryCostCalculator = null;
		campaignCalculator = null;
		couponCalculator = null;
		shoppingCard = null;
	}
	
	@Test
	public void shouldAddProductItemOfShoppingCard() {
		
		Category category = new CategoryBuilder().title("electronic").build();
		Product product = new ProductBuilder().title("cable").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("batery").price(150.0).category(category).build();
		
		shoppingCard.addItem(product, 3);
		shoppingCard.addItem(product1, 2);
		
		assertThat(shoppingCard.getProducts().size(), equalTo(5));
	}
	
	@Test
	public void shouldReturnGreaterThanZeroDiscountWhenProductsCategoryHaveCampaign() {
		
		Category category = new CategoryBuilder().title("food").build();
		Product product = new ProductBuilder().title("apple").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("almonds").price(150.0).category(category).build();
		
		shoppingCard.addItem(product, 3);
		shoppingCard.addItem(product1, 2);
		
		double capaignDiscount = shoppingCard.getCampaignDiscount();
		
		assertThat(capaignDiscount, equalTo(120.0));
	}
	
	@Test
	public void shouldReturnGreaterThanZeroCouponDiscountWhenShoppingCardHaveCoupon() {
		
		Category category = new CategoryBuilder().title("food").build();
		Product product = new ProductBuilder().title("apple").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("almonds").price(150.0).category(category).build();
		Coupon coupon = new CouponBuilder().amount(10).discount(10).discountType(DiscountType.Rate).build();
		
		shoppingCard.addItem(product, 3);
		shoppingCard.addItem(product1, 2);
		shoppingCard.addCoupon(coupon);
		
		double capaignDiscount = shoppingCard.getCouponDiscount();
		
		assertThat(capaignDiscount, equalTo(48.0));
	}
	
	@Test
	public void shouldReturnGreaterThanZeroCouponDiscountWhenShoppingCardHaveCouponAndNotHaveCampaign() {
		
		Product product = new ProductBuilder().title("apple").price(100.0).category(null).build();
		Product product1 = new ProductBuilder().title("almonds").price(150.0).category(null).build();
		Coupon coupon = new CouponBuilder().amount(10).discount(10).discountType(DiscountType.Rate).build();
		
		shoppingCard.addItem(product, 3);
		shoppingCard.addItem(product1, 2);
		shoppingCard.addCoupon(coupon);
		
		double capaignDiscount = shoppingCard.getCouponDiscount();
		
		assertThat(capaignDiscount, equalTo(60.0));
	}
	
	@Test
	public void shouldReturnZeroOfCampaignDiscountWhenShoppingCardNotHaveProduct() {
		
		assertThat(shoppingCard.getCampaignDiscount(), equalTo(0.0));
	}
	
	@Test
	public void shouldReturnZeroOfCouponDiscountWhenShoppingCardNotHaveProduct() {
		
		assertThat(shoppingCard.getCouponDiscount(), equalTo(0.0));
	}
	
	@Test
	public void shouldReturnFixedCostOfDeliveryCostWhenShoppingCardNotHaveProduct() {
		
		assertThat(shoppingCard.getDeliveryCost(), equalTo(TestConstants.fixedCost));
	}
	
	@Test
	public void shouldReturnProductAmountWhenShoppingCardProductNotHaveCampaignAndCoupon() {
		
		Product product = new ProductBuilder().title("apple").price(100.0).category(null).build();
		shoppingCard.addItem(product, 2);
		
		double totalAmountAfterDiscounts = shoppingCard.getTotalAmountAfterDiscounts();
		
		assertThat(totalAmountAfterDiscounts, equalTo(200.0));
	}
	
	@Test
	public void shouldReturnAmountAfterDiscountsWhenShoppingCardProductHaveCampaignAndCoupon() {
		
		Category category = new CategoryBuilder().title("food").build();
		Product product = new ProductBuilder().title("apple").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("almonds").price(150.0).category(category).build();
		Coupon coupon = new CouponBuilder().amount(10).discount(10).discountType(DiscountType.Rate).build();
		
		shoppingCard.addItem(product, 3);
		shoppingCard.addItem(product1, 2);
		shoppingCard.addCoupon(coupon);
				
		double totalAmountAfterDiscounts = shoppingCard.getTotalAmountAfterDiscounts();
		
		assertThat(totalAmountAfterDiscounts, equalTo(432.0));
	}
	
	@Test
	public void shouldPrintCard() {
		
		Category category = new CategoryBuilder().title("food").build();
		Product product = new ProductBuilder().title("apple").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("almonds").price(150.0).category(category).build();
		Coupon coupon = new CouponBuilder().amount(100).discount(10).discountType(DiscountType.Rate).build();
		
		shoppingCard.addItem(product, 3);
		shoppingCard.addItem(product1, 1);
		shoppingCard.addCoupon(coupon);
		
		shoppingCard.print();
		System.out.println("Delivery Const : "+ shoppingCard.getDeliveryCost());
	}

}
