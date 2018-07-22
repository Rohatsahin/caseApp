package com.trendyol.data;

import com.trendyol.entity.campaign.Campaign;
import com.trendyol.entity.category.Category;
import com.trendyol.entity.coupon.Coupon;
import com.trendyol.entity.enums.DiscountType;
import com.trendyol.entity.product.Product;

public class SampleData {
	
	private SampleData() {}
	
	/**
	 *  sample creating a new  category
	 */
	public static final Category food = new Category("food");
	public static final Category foodFress = new Category("foodFress",food);

	
	/**
	 *  sample products
	 */
    public static final Product apple = new Product("Apple", 100.0, food);
    public static final Product almond = new Product("Almonds", 150.0, food);
    public static final Product almondFress = new Product("almondFress", 150.0, foodFress);
    
    /**
     * discount rules can be 20% on a category if bought more than 3 items
     */
    public static final Campaign campaign1 = new Campaign(food, 20.0, 3, DiscountType.Rate);
    
    /**
     * discount rules can be 50% on a category if bought more than 5 items
     */
    public static final Campaign campaign2 = new Campaign(food, 50.0, 5, DiscountType.Rate);
    
    /**
     * discount rule 5 TL amount discount on a category if bought more than items
     */
    public static final Campaign campaign3 = new Campaign(food, 5.0, 0, DiscountType.Amount);
    
    /**
     * discount rule 5 TL amount discount on a subCategory if bought more than items
     */
    public static final Campaign campaign4 = new Campaign(foodFress, 5.0, 0, DiscountType.Amount);
    
    /**
     *  coupon for 100 TL min purchase amount for a 10% discount
     */
    public static final Coupon coupon = new Coupon(100.0, 10, DiscountType.Rate);

}
