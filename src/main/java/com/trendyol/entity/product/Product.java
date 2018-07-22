package com.trendyol.entity.product;

import com.trendyol.entity.AbstractEntity;
import com.trendyol.entity.category.Category;

public class Product extends AbstractEntity {

	public Product(String title) {
		super(title);
	}

	public Product(String title, double price, Category category) {
		this(title);
		this.price = price;
		this.category = category;
	}

	private static final long serialVersionUID = 1L;

	private double price;

	private Category category;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [price=" + price + ", category=" + category + "]";
	}
	
	
}
