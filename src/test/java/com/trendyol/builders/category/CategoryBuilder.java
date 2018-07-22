package com.trendyol.builders.category;

import com.trendyol.builders.AbstractBuilder;
import com.trendyol.entity.category.Category;

public class CategoryBuilder extends AbstractBuilder<Category> {
	
	private String title;

	private Category parentCategory;

	public CategoryBuilder title(String title) {
		this.title = title;
		return this;
	}
	
	public CategoryBuilder parentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
		return this;
	}

	@Override
	public Category build() {
		Category category = new Category(title,parentCategory);
		return category;
	}

}
