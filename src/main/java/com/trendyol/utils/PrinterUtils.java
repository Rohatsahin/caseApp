package com.trendyol.utils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.trendyol.entity.category.Category;
import com.trendyol.entity.product.Product;

public class PrinterUtils {

	private PrinterUtils() {
	}

	public static void print(List<Product> products, double totalPrice, double totalDiscount) {

		Map<Category, List<Product>> groupByCategory = products.stream()
				.collect(Collectors.groupingBy(Product::getCategory));

		groupByCategory.entrySet().stream().forEach(entry -> {
			System.out.println("Category Name : " + entry.getKey().getTitle());

			Map<Product, Long> productByQuantity = entry.getValue().stream().filter(product -> product.getCategory().equals(entry.getKey()))
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

			productByQuantity.entrySet().stream().forEach(product -> {
				System.out.println("Product Name : " + product.getKey().getTitle() + ", Quantity : " + product.getValue()
						+ ", Unit Price : " + product.getKey().getPrice());
				
			});

		});

		System.out.println("Total price : " + totalPrice + " TL");
		System.out.println("Total discount : " + totalDiscount + " TL");
	}
}
