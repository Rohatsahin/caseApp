package com.trendyol.service;

import java.util.List;

import com.trendyol.entity.category.Category;
import com.trendyol.entity.product.Product;

public interface CampaignCalculator {
	
	double[] calculateFor(Category category, List<Product> products);
}
