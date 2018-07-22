package com.trendyol.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.trendyol.builders.coupon.CouponBuilder;
import com.trendyol.entity.coupon.Coupon;
import com.trendyol.entity.enums.DiscountType;
import com.trendyol.service.impl.CouponCalculatorImpl;

public class CouponCalculatorTest {

	private CouponCalculator couponCalculator;

	@Before
	public void init() {
		couponCalculator = new CouponCalculatorImpl();
	}

	@After
	public void clear() {
		couponCalculator = null;
	}

	@Test
	public void shoultReturnZeroAmountWhenCouponIsNull() {

		double totalDiscountBeforeCounpon = 125.0;

		double couponDiscount = couponCalculator.calculateFor(null, totalDiscountBeforeCounpon);

		assertThat(couponDiscount, equalTo(0.0));
	}
	
	@Test
	public void shoultReturnZeroAmountWhenAmountIsLowerThanCouponAmount() {

		double totalDiscountBeforeCounpon = 45.0;
		Coupon coupon = new CouponBuilder().amount(50).discount(10).discountType(DiscountType.Rate).build();

		double couponDiscount = couponCalculator.calculateFor(coupon, totalDiscountBeforeCounpon);

		assertThat(couponDiscount, equalTo(0.0));
	}
	
	@Test
	public void shoultReturnCouponDiscountWithRateWhenCouponIsNotNullAndDiscountTypeRate() {

		double totalDiscountBeforeCounpon = 125.0;
		Coupon coupon = new CouponBuilder().amount(50).discount(10).discountType(DiscountType.Rate).build();

		double couponDiscount = couponCalculator.calculateFor(coupon, totalDiscountBeforeCounpon);

		assertThat(couponDiscount, equalTo(12.5));
	}
	
	@Test
	public void shoultReturnCouponDiscountWithAmountWhenCouponIsNotNullAndDiscountTypeAmount() {

		double totalDiscountBeforeCounpon = 125.0;
		Coupon coupon = new CouponBuilder().amount(50).discount(10).discountType(DiscountType.Amount).build();

		double couponDiscount = couponCalculator.calculateFor(coupon, totalDiscountBeforeCounpon);

		assertThat(couponDiscount, equalTo(10.0));
	}

}
