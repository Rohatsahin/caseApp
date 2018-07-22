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
import com.trendyol.entity.category.Category;
import com.trendyol.entity.product.Product;
import com.trendyol.service.impl.CampaignCalculatorImpl;


public class CampaignCalculatorTest {

	private CampaignCalculator campaignCalculator;

	@Before
	public void init() {
		SampleCampaignSource sampleCampaignSource = new SampleCampaignSource();
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
		
		double[] discount = campaignCalculator.calculateFor(category, products);
		
		assertThat(discount.length, equalTo(3));
	}

}
