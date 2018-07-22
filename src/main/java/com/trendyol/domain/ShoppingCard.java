package com.trendyol.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.trendyol.entity.category.Category;
import com.trendyol.entity.coupon.Coupon;
import com.trendyol.entity.product.Product;
import com.trendyol.service.CampaignCalculator;
import com.trendyol.service.CouponCalculator;
import com.trendyol.service.DeliveryCostCalculator;
import com.trendyol.utils.PrinterUtils;
import com.trendyol.utils.ProductUtils;

public class ShoppingCard {

	private List<Product> products;

	private Coupon coupon;

	private DeliveryCostCalculator deliveryCostCalculator;

	private CampaignCalculator campaignCalculator;

	private CouponCalculator couponCalculator;
	
	public ShoppingCard() {
		this.products = new ArrayList<>();
	}

	public void setDeliveryCostCalculator(DeliveryCostCalculator deliveryCostCalculator) {
		this.deliveryCostCalculator = deliveryCostCalculator;
	}

	public void setCampaignService(CampaignCalculator campaignService) {
		this.campaignCalculator = campaignService;
	}

	public void setCouponService(CouponCalculator couponCalculator) {
		this.couponCalculator = couponCalculator;
	}

	public List<Product> getProducts() {
		return products;
	}

	public double getTotalAmountAfterDiscounts() {
		return ProductUtils.getTotalAmountOfProducts(products) - getCampaignDiscount() - getCouponDiscount();
	}

	public double getCampaignDiscount() {

		List<Category> categories = products.stream().map(Product::getCategory).filter(Objects::nonNull).distinct().collect(Collectors.toList());
		List<Double> totalDiscounts = categories.stream()
				.map(category -> applyDiscounts(campaignCalculator.calculateFor(category, ProductUtils.categoryByProduct(products, category))))
				.collect(Collectors.toList());

		return totalDiscounts.stream().reduce(0.0, (x, y) -> x + y).doubleValue();
	}

	public double getCouponDiscount() {
		return applyCoupon(coupon);
	}

	public double getDeliveryCost() {
		return deliveryCostCalculator.calculateFor(this);
	}

	public void addItem(Product product, int quantity) {
		IntStream.rangeClosed(1, quantity).forEach(index -> products.add(product));
	}

	public void print() {
		double totalPrice = this.getTotalAmountAfterDiscounts();
		double totalDiscount = this.getCampaignDiscount() + this.getCouponDiscount();
		PrinterUtils.print(products, totalPrice, totalDiscount);
	}

	public void addCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	private double applyDiscounts(double... discounts) {
		return Arrays.stream(discounts).max().orElse(0.0);
	}

	private double applyCoupon(Coupon coupon) {
		double totalDiscountBeforeCounpon = ProductUtils.getTotalAmountOfProducts(products) - getCampaignDiscount();
		
		return couponCalculator.calculateFor(coupon, totalDiscountBeforeCounpon);
	}

}
