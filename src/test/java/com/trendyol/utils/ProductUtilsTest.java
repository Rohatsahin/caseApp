package com.trendyol.utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.trendyol.builders.category.CategoryBuilder;
import com.trendyol.builders.product.ProductBuilder;
import com.trendyol.entity.category.Category;
import com.trendyol.entity.product.Product;

public class ProductUtilsTest {

	@Test
	public void shouldRetrunZeroForDeliveryWhenProductsEmpty() {
		
		long numberOfProduct = ProductUtils.getNumberOfDeliveries(Collections.emptyList());
		
		assertThat(numberOfProduct, equalTo(0L));
	}
	
	@Test
	public void shouldReturnZeroForDeliveryWhenProductsNotHaveCategory() {
		
		Product product = new ProductBuilder().title("cable").price(100.0).category(null).build();
		Product product1 = new ProductBuilder().title("batery").price(150.0).category(null).build();
		
		List<Product> products = Arrays.asList(product, product1);
		
        long numberOfProduct = ProductUtils.getNumberOfDeliveries(products);
		
		assertThat(numberOfProduct, equalTo(0L));
	}
	
	@Test
	public void shouldReturnGreaterThanZeroForDeliveryWhenProductsHaveCategory() {
		
		Category category = new CategoryBuilder().title("electronic").build();
		Product product = new ProductBuilder().title("cable").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("batery").price(150.0).category(category).build();
		
		List<Product> products = Arrays.asList(product, product1);
		
        long numberOfProduct = ProductUtils.getNumberOfDeliveries(products);
		
		assertThat(numberOfProduct, equalTo(1L));
	}
	
	@Test
	public void shouldRetrunZeroNumberOfProductsWhenProductsEmpty() {
		
		long numberOfProduct = ProductUtils.getNumberOfProducts(Collections.emptyList());
		
		assertThat(numberOfProduct, equalTo(0L));
	}
	
	
	@Test
	public void shouldReturnGreaterThanZeroWhenProductsHave() {
		
		Category category = new CategoryBuilder().title("electronic").build();
		Product product = new ProductBuilder().title("cable").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("batery").price(150.0).category(category).build();
		
		List<Product> products = Arrays.asList(product, product1);
		
        long numberOfProduct = ProductUtils.getNumberOfProducts(products);
		
		assertThat(numberOfProduct, equalTo(2L));
	}
	
	@Test
	public void shouldRetrunZeroTotalAmountOfProductsWhenProductsEmpty() {
		
		double numberOfProduct = ProductUtils.getTotalAmountOfProducts(Collections.emptyList());
		
		assertThat(numberOfProduct, equalTo(0.0));
	}
	
	@Test
	public void shouldReturnGreaterThanZeroTotalAmountOfProductsWhenProductsHave() {
		
		Category category = new CategoryBuilder().title("electronic").build();
		Product product = new ProductBuilder().title("cable").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("batery").price(150.0).category(category).build();
		
		List<Product> products = Arrays.asList(product, product1);
		
        double numberOfProduct = ProductUtils.getTotalAmountOfProducts(products);
		
		assertThat(numberOfProduct, equalTo(250.0));
	}
	
	@Test
	public void shouldRetrunEmptyListCategoryByProductWhenProductsEmpty() {
		
		Category category = new CategoryBuilder().title("electronic").build();
		
		List<Product> products = ProductUtils.categoryByProduct(Collections.emptyList(),category);

		assertThat(products.size(), equalTo(0));
	}
	
	@Test
	public void shouldRetrunNonEmptyListCategoryByProductWhenProductsEmpty() {
		
		Category category = new CategoryBuilder().title("electronic").build();
		Product product = new ProductBuilder().title("cable").price(100.0).category(category).build();
		Product product1 = new ProductBuilder().title("batery").price(150.0).category(null).build();
		
		List<Product> products = Arrays.asList(product, product1);
		
		List<Product> filteredProducts = ProductUtils.categoryByProduct(products,category);

		assertThat(filteredProducts.size(), equalTo(1));
		assertThat(filteredProducts, hasItem(product));
	}
	
}
