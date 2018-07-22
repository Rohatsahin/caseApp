package com.trendyol.data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.trendyol.entity.campaign.Campaign;
import com.trendyol.entity.category.Category;

public class SampleCampaignSource {
	
	 private List<Campaign>  campaigns;
	 
	 public SampleCampaignSource(List<Campaign> campaigns) {
	   this.campaigns = campaigns;
	}

	/*
	 * filter campaign by category in all campaign
	 * static data taken by SampleData.java
	 */
	public List<Campaign> getCampaingByCategory(Category category) {
		return campaigns.stream()
				.filter(Objects::nonNull)
				.filter(campaign -> (campaign.getCategory().equals(category) || campaign.getCategory().equals(category.getParentCategory())))
				.collect(Collectors.toList());
	}
}
