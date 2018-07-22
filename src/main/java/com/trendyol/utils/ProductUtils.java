package com.trendyol.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.trendyol.entity.category.Category;
import com.trendyol.entity.product.Product;

public class ProductUtils {
	
	private ProductUtils() {}

	public static int getNumberOfDeliveries(List<Product> products) {
		return Math.toIntExact(products.stream().map(Product::getCategory).distinct().count());
	}

	public static int getNumberOfProducts(List<Product> products) {
		return Math.toIntExact(products.stream().map(Product::getTitle).distinct().count());
	}
	
	public static List<Product> categoryByProduct(List<Product> products, Category category) {
		return products.stream().filter(product -> (product.getCategory().equals(category))).collect(Collectors.toList());
	}
	
	public static double getTotalAmountOfProducts(List<Product> products) {
		return products.stream().map(Product::getPrice).reduce(0.0, (x, y) -> x + y).doubleValue();
	}
}
