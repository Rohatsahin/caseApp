package com.trendyol.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.trendyol.builders.category.CategoryBuilder;
import com.trendyol.builders.product.ProductBuilder;
import com.trendyol.constants.TestConstants;
import com.trendyol.domain.ShoppingCard;
import com.trendyol.entity.category.Category;
import com.trendyol.entity.product.Product;
import com.trendyol.service.impl.DeliveryCostCalculatorImpl;

public class DeliveryCostCalculatorTest {
	
   private DeliveryCostCalculator deliveryCostCalculator; 	
	
   @Before
   public void init() {
	   deliveryCostCalculator = new DeliveryCostCalculatorImpl(TestConstants.costPerDelivery, TestConstants.costPerProduct, TestConstants.fixedCost);
   }
   
   @After
   public void clear() {
	   deliveryCostCalculator = null;
   }

   @Test
   public void shouldReturnFixedCostWhenShoppingCardHaveEmptyProduct() {
	   
	   ShoppingCard shoppingCard = new ShoppingCard();
	   
	   double deliveryConst = deliveryCostCalculator.calculateFor(shoppingCard);
	   
	   assertThat(deliveryConst, equalTo(TestConstants.fixedCost));
   }
   
   @Test
   public void shouldAddProductAndDeliveryConstWhenShoppingCardHaveProduct() {
	   
	   Category category = new CategoryBuilder().title("food").build();
	   Product product = new ProductBuilder().title("apple").price(100.0).category(category).build();
	   Product product1 = new ProductBuilder().title("almonds").price(150.0).category(category).build();
	   
	   ShoppingCard shoppingCard = new ShoppingCard();
	   shoppingCard.addItem(product, 2);
	   shoppingCard.addItem(product1, 1);
	   
       double deliveryConst = deliveryCostCalculator.calculateFor(shoppingCard);
	   
	   assertThat(deliveryConst, equalTo(14.99));
	   
   }

}
