package com.trendyol.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.trendyol.builders.category.CategoryBuilder;
import com.trendyol.builders.product.ProductBuilder;
import com.trendyol.data.SampleCampaignSource;
import com.trendyol.data.SampleData;
import com.trendyol.entity.campaign.Campaign;
import com.trendyol.entity.category.Category;
import com.trendyol.entity.product.Product;
import com.trendyol.service.impl.CampaignCalculatorImpl;
import com.trendyol.testUtils.ArrayUtils;


public class CampaignCalculatorTest {

	private CampaignCalculator campaignCalculator;
    
	/**
     * create campaignCalculator instance with sample campaign datas 
     */
	@Before
	public void init() {
		List<Campaign> campaigns = Arrays.asList(SampleData.campaign1 ,SampleData.campaign3);
		SampleCampaignSource sampleCampaignSource = new SampleCampaignSource(campaigns);
		campaignCalculator = new CampaignCalculatorImpl(sampleCampaignSource);
	}

	@After
	public void clear() {
		campaignCalculator = null;
	}

	@Test
	public void shouldReturnEmptyDiscountsWhenCategoryNotHaveCampaign() {

		Category category = new CategoryBuilder().title("electronic").build();
		Product product = new ProductBuilder().title("cable").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("batery").price(150.0).category(category).build();
		
		List<Product> products = Arrays.asList(product, product1);
		
		double[] discount = campaignCalculator.calculateFor(category, products);
		
		assertThat(discount.length, equalTo(0));

	}
	
	@Test
	public void shouldReturnDiscountsWhenCategoryHaveCampaign() {

		Category category = new CategoryBuilder().title("food").build();
		Product product = new ProductBuilder().title("apple").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("apple").price(100.0).category(category).build();
		Product product2 = new ProductBuilder().title("almonds").price(150.0).category(category).build();
		
		List<Product> products = Arrays.asList(product, product1,product2);
		
		double[] discounts = campaignCalculator.calculateFor(category, products);
		
		assertThat(discounts.length, equalTo(2));
		assertThat(ArrayUtils.getMaxElementOfArray(discounts), equalTo(5.0));
	}
	
	@Test
	public void shouldReturnDiscountsWhenCategoryHaveCampaignWithSubCategory() {

		Category category = new CategoryBuilder().title("food").build();
		Category category1 = new CategoryBuilder().title("foodFress").parentCategory(category).build();
		
		Product product = new ProductBuilder().title("apple").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("apple").price(100.0).category(category).build();
		Product product2 = new ProductBuilder().title("almonds").price(150.0).category(category).build();
		Product product3 = new ProductBuilder().title("almondFress").price(180.0).category(category1).build();
		
		List<Product> products = Arrays.asList(product, product1,product2,product3);
		
		double[] discounts = campaignCalculator.calculateFor(category, products);
		
		assertThat(discounts.length, equalTo(2));
		assertThat(ArrayUtils.getMaxElementOfArray(discounts), equalTo(106.0));
	}
	
	@Test
	public void shouldReturnDiscountsRateWhenProductSizeOfCategoryGreaterThanCampaignQuantity() {

		Category category = new CategoryBuilder().title("food").build();
		Product product = new ProductBuilder().title("apple").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("apple").price(100.0).category(category).build();
		Product product2 = new ProductBuilder().title("almonds").price(150.0).category(category).build();
		Product product3 = new ProductBuilder().title("almonds").price(150.0).category(category).build();
		
		List<Product> products = Arrays.asList(product, product1,product2, product3);
		
		double[] discounts = campaignCalculator.calculateFor(category, products);
		
		assertThat(discounts.length, equalTo(2));
		assertThat(ArrayUtils.getMaxElementOfArray(discounts), equalTo(100.0));
	}

}
