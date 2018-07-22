package com.trendyol.entity.campaign;

import java.io.Serializable;

import com.trendyol.entity.category.Category;
import com.trendyol.entity.enums.DiscountType;

public class Campaign implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	public Campaign(Category category, double discount, int quantity, DiscountType discountType) {
		this.category = category;
		this.discount = discount;
		this.quantity = quantity;
		this.discountType = discountType;
	}

	private Category category;
	
	private double discount;
	
	private long quantity;
	
	private DiscountType discountType;

	public Category getCategory() {
		return category;
	}

	public double getDiscount() {
		return discount;
	}

	public long getQuantity() {
		return quantity;
	}


	public DiscountType getDiscountType() {
		return discountType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		long temp;
		temp = Double.doubleToLongBits(discount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((discountType == null) ? 0 : discountType.hashCode());
		result = prime * result + (int) (quantity ^ (quantity >>> 32));
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
		Campaign other = (Campaign) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (Double.doubleToLongBits(discount) != Double.doubleToLongBits(other.discount))
			return false;
		if (discountType != other.discountType)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}
