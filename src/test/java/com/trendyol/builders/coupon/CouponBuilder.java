package com.trendyol.builders.coupon;

import com.trendyol.entity.coupon.Coupon;
import com.trendyol.entity.enums.DiscountType;

public class CouponBuilder {

	private double amount;

	private int discount;

	private DiscountType discountType;

	public CouponBuilder amount(double amount) {
		this.amount = amount;
		return this;
	}

	public CouponBuilder discount(int discount) {
		this.discount = discount;
		return this;
	}

	public CouponBuilder discountType(DiscountType discountType) {
		this.discountType = discountType;
		return this;
	}

	public Coupon build() {
		return new Coupon(amount, discount, discountType);
	}

}
