package com.trendyol.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.trendyol.data.SampleData;
import com.trendyol.entity.campaign.Campaign;
import com.trendyol.entity.category.Category;
import com.trendyol.entity.enums.DiscountType;
import com.trendyol.entity.product.Product;
import com.trendyol.service.CampaignCalculator;

public class CampaignCalculatorImpl implements CampaignCalculator {

	@Override
	public double[] calculateFor(Category category, List<Product> products) {

		List<Campaign> campaigns = getCampaingByCategory(category);
		long totalProduct = products.stream().count();
		double totalProductAmount = products.stream().map(Product::getPrice).reduce(0.0, (x, y) -> x + y).doubleValue();

		return campaigns.stream().filter(Objects::nonNull).map(campaign -> this.calculate(campaign, totalProduct, totalProductAmount))
				.mapToDouble(Double::doubleValue).toArray();
		
	}

	private double calculate(Campaign campaign, long totalProduct, double totalProductAmount) {

		if (campaign.getDiscountType().equals(DiscountType.Rate) && totalProduct > campaign.getQuantity()) {
			return (totalProductAmount * campaign.getDiscount()) / 100;
		} else if (campaign.getDiscountType().equals(DiscountType.Amount) && totalProduct > campaign.getQuantity()) {
			return campaign.getDiscount();
		}
		return 0.0;
	}

	/*
	 * filter campaign by category in all sample campaign
	 */
	private List<Campaign> getCampaingByCategory(Category category) {
		return Arrays.asList(SampleData.campaign1, SampleData.campaign2, SampleData.campaign3).stream()
				.filter(campaign -> (campaign.getCategory().equals(category) || campaign.getCategory().equals(category.getParentCategory())))
				.collect(Collectors.toList());
	}
}
