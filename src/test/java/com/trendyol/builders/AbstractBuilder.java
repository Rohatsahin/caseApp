package com.trendyol.builders;

import com.trendyol.entity.AbstractEntity;

public abstract class AbstractBuilder<T extends AbstractEntity> {
	
	public abstract T build();

}
