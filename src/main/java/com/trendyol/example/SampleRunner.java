package com.trendyol.example;

import com.trendyol.constants.BaseConstants;
import com.trendyol.data.SampleData;
import com.trendyol.domain.ShoppingCard;
import com.trendyol.service.CampaignCalculator;
import com.trendyol.service.CouponCalculator;
import com.trendyol.service.DeliveryCostCalculator;
import com.trendyol.service.impl.CampaignCalculatorImpl;
import com.trendyol.service.impl.CouponCalculatorImpl;
import com.trendyol.service.impl.DeliveryCostCalculatorImpl;

public class SampleRunner {

	
	public static void main(String[] args) {
		
		ShoppingCard shoppingCard = new ShoppingCard();
		DeliveryCostCalculator deliveryCostCalculator = 
				new DeliveryCostCalculatorImpl(BaseConstants.costPerDelivery, BaseConstants.costPerProduct, BaseConstants.fixedCost);
		CampaignCalculator campaignCalculator = new CampaignCalculatorImpl();
		CouponCalculator couponCalculator = new CouponCalculatorImpl();
		
		
		shoppingCard.addItem(SampleData.apple, 2);
		shoppingCard.addItem(SampleData.almond, 3);
		shoppingCard.addCoupon(SampleData.coupon);
		shoppingCard.setDeliveryCostCalculator(deliveryCostCalculator);
		shoppingCard.setCouponService(couponCalculator);
		shoppingCard.setCampaignService(campaignCalculator);
		
		shoppingCard.print();
		System.out.println("Delivery Const : " + shoppingCard.getDeliveryCost() +" TL");

	}

}
