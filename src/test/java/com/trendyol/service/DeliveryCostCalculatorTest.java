package com.trendyol.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.trendyol.constants.TestConstants;
import com.trendyol.domain.ShoppingCard;
import com.trendyol.service.impl.DeliveryCostCalculatorImpl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class DeliveryCostCalculatorTest {
	
   private DeliveryCostCalculator deliveryCostCalculator; 	
	
   @Before
   public void init() {
	   deliveryCostCalculator = new DeliveryCostCalculatorImpl(TestConstants.costPerDelivery, TestConstants.costPerProduct, TestConstants.fixedCost);
   }

   @Test
   public void shouldReturnFixedCostWhenShoppingCardHaveEmptyProduct() {
	   
	   ShoppingCard shoppingCard = new ShoppingCard();
	   
	   double deliveryConst = deliveryCostCalculator.calculateFor(shoppingCard);
	   
	   assertThat(deliveryConst, equalTo(TestConstants.fixedCost));
   }
   
   @Test
   public void shouldAddProductAndDeliveryConstWhenShoppingCardHaveProduct() {
	   
	   
   }

}
