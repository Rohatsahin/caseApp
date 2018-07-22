package com.trendyol.entity.category;

import com.trendyol.entity.AbstractEntity;

public class Category extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	public Category(String title) {
		super(title);
	}
	
	public Category(String title, Category parentCategory) {
		  this(title);
		  this.parentCategory = parentCategory;
	}

	private Category parentCategory;

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((parentCategory == null) ? 0 : parentCategory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (parentCategory == null) {
			if (other.parentCategory != null)
				return false;
		} else if (!parentCategory.equals(other.parentCategory))
			return false;
		return true;
	}
	
}
