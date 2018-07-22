package com.trendyol.service;

import com.trendyol.entity.coupon.Coupon;

public interface CouponCalculator {

	double calculateFor(Coupon coupon, double totalDiscountBeforeCounpon);
}
