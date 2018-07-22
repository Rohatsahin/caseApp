package com.trendyol.service.impl;

import com.trendyol.entity.coupon.Coupon;
import com.trendyol.entity.enums.DiscountType;
import com.trendyol.service.CouponCalculator;

public class CouponCalculatorImpl implements CouponCalculator {

	@Override
	public double calculateFor(Coupon coupon, double totalDiscountBeforeCounpon) {
		return calculate(coupon, totalDiscountBeforeCounpon);
	}

	private double calculate(Coupon coupon, double totalDiscountBeforeCounpon) {
		
		if (coupon.getDiscountType().equals(DiscountType.Rate) && totalDiscountBeforeCounpon > coupon.getAmount()) {
			return (totalDiscountBeforeCounpon * coupon.getDiscount()) / 100;
		} else if (coupon.getDiscountType().equals(DiscountType.Amount) && totalDiscountBeforeCounpon > coupon.getAmount()) {
			return coupon.getDiscount();
		}
		return 0.0;
	}

}
