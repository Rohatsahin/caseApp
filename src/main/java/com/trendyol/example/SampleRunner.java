package com.trendyol.example;

import java.util.Arrays;
import java.util.List;

import com.trendyol.constants.BaseConstants;
import com.trendyol.data.SampleCampaignSource;
import com.trendyol.data.SampleData;
import com.trendyol.domain.ShoppingCard;
import com.trendyol.entity.campaign.Campaign;
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
		List<Campaign> campaigns = Arrays.asList(SampleData.campaign1,SampleData.campaign2,SampleData.campaign3);
		SampleCampaignSource sampleCampaignSource = new SampleCampaignSource(campaigns);
		CampaignCalculator campaignCalculator = new CampaignCalculatorImpl(sampleCampaignSource);
		
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
