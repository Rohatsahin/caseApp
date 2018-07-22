package com.trendyol.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.trendyol.entity.category.Category;
import com.trendyol.entity.product.Product;

public class ProductUtils {

	private ProductUtils() {}

	public static long getNumberOfDeliveries(List<Product> products) {
		return products.stream().map(Product::getCategory).filter(Objects::nonNull).distinct().count();
	}

	public static long getNumberOfProducts(List<Product> products) {
		return products.stream().map(Product::getTitle).filter(Objects::nonNull).distinct().count();
	}

	public static List<Product> categoryByProduct(List<Product> products, Category category) {
		return products.stream()
				.filter(product -> (Objects.nonNull(product.getCategory()) && product.getCategory().equals(category)))
				.collect(Collectors.toList());
	}

	public static double getTotalAmountOfProducts(List<Product> products) {
		return products.stream().map(Product::getPrice).filter(Objects::nonNull).reduce(0.0, (x, y) -> x + y)
				.doubleValue();
	}
}
