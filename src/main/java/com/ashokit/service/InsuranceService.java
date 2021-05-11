package com.ashokit.service;

import java.util.List;

import com.ashokit.entity.InsurancePlans;
import com.ashokit.entity.SearchCriteria;

public interface InsuranceService {

	public List<InsurancePlans> getAllPlans();

	public List<InsurancePlans> getPlansByName(String planName);

	public List<InsurancePlans> getPlansByStatus(String planStatus);

	public List<InsurancePlans> getPlansByNameAndStatus(String planName, String planStatus);

	//public List<InsurancePlans> search(String name, String status);

	public List<InsurancePlans> getAllPlans(SearchCriteria plans);

	public List<InsurancePlans> getPlans(String name, String status);
}
