package com.trendyol.entity.coupon;

import java.io.Serializable;

import com.trendyol.entity.enums.DiscountType;

public class Coupon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Coupon(double amount, int discount, DiscountType discountType) {
		this.amount = amount;
		this.discount = discount;
		this.discountType = discountType;
	}

	private double amount;
	
	private int discount;
	
	private DiscountType discountType;

	public double getAmount() {
		return amount;
	}

	public int getDiscount() {
		return discount;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + discount;
		result = prime * result + ((discountType == null) ? 0 : discountType.hashCode());
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
		Coupon other = (Coupon) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (discount != other.discount)
			return false;
		if (discountType != other.discountType)
			return false;
		return true;
	}
	
}
