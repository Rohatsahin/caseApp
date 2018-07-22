package com.trendyol.service.impl;

import com.trendyol.domain.ShoppingCard;
import com.trendyol.service.DeliveryCostCalculator;
import com.trendyol.utils.ProductUtils;

public class DeliveryCostCalculatorImpl implements DeliveryCostCalculator {

	private double costPerDelivery;
	
	private double costPerProduct;
	
	private double fixedCost;
		
	public DeliveryCostCalculatorImpl(double costPerDelivery, double costPerProduct, double fixedCost) {
		this.costPerDelivery = costPerDelivery;
		this.costPerProduct = costPerProduct;
		this.fixedCost = fixedCost;
	}
	
	@Override
	public double calculateFor(ShoppingCard shoppingCard) {
		return (costPerDelivery * ProductUtils.getNumberOfDeliveries(shoppingCard.getProducts())) 
			+ costPerProduct * ProductUtils.getNumberOfProducts(shoppingCard.getProducts())
			+ fixedCost;
	}
	
}
