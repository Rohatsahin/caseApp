package com.trendyol.data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.trendyol.entity.campaign.Campaign;
import com.trendyol.entity.category.Category;

public class SampleCampaignSource {

	/*
	 * filter campaign by category in all campaign
	 * static data taken by SampleData.java
	 */
	public List<Campaign> getCampaingByCategory(Category category) {
		return Arrays.asList(SampleData.campaign1, SampleData.campaign2, SampleData.campaign3).stream()
				.filter(campaign -> (campaign.getCategory().equals(category) || campaign.getCategory().equals(category.getParentCategory())))
				.collect(Collectors.toList());
	}
}
