package com.trendyol.builders.product;

import com.trendyol.builders.AbstractBuilder;
import com.trendyol.entity.category.Category;
import com.trendyol.entity.product.Product;

public class ProductBuilder extends AbstractBuilder<Product> {

	private String title;

	private double price;

	private Category category;

	public ProductBuilder title(String title) {
		this.title = title;
		return this;
	}

	public ProductBuilder price(double price) {
		this.price = price;
		return this;
	}

	public ProductBuilder category(Category category) {
		this.category = category;
		return this;
	}

	@Override
	public Product build() {
        return  new Product(title,price,category);
	}

}
