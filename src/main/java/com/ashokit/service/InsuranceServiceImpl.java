package com.ashokit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.entity.InsurancePlans;
import com.ashokit.entity.SearchCriteria;
import com.ashokit.repository.planRepository;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	private planRepository repo;

	@Override
	public List<InsurancePlans> getAllPlans(SearchCriteria plans) {

		InsurancePlans planEx = new InsurancePlans();
		if (plans.getPlanName() != null && !"".equals(plans.getPlanStatus())) {
			planEx.setPlanName(plans.getPlanName());
		}
		if (plans.getPlanStatus() != null && !"".equals(plans.getPlanName())) {
			planEx.setPlanStatus(plans.getPlanStatus());
		}
	   

		Example<InsurancePlans> example = Example.of(planEx);
		List<InsurancePlans> result = repo.findAll(example);
		return result;
	}

	/*
	 * public List<InsurancePlans> search(String name, String status) {
	 * 
	 * InsurancePlans planEx = new InsurancePlans();
	 * 
	 * if (name != null && status != null) { planEx.setPlanName(name);
	 * planEx.setPlanStatus(status); } else if (name != null) {
	 * planEx.setPlanName(name);
	 * 
	 * } else if (status != null) { planEx.setPlanStatus(status);
	 * 
	 * } else {
	 * 
	 * return repo.findAll(); } Example<InsurancePlans> example =
	 * Example.of(planEx); List<InsurancePlans> result = repo.findAll(example);
	 * return result; }
	 */

	@Override
	public List<InsurancePlans> getPlans(String name, String status) {

		System.out.println(name + " " + status);

		InsurancePlans planEx = new InsurancePlans();

		if (name != null && !"".equals(status)) {
			planEx.setPlanName(name);
		}
		if (status != null && !"".equals(name)) {
			planEx.setPlanStatus(status);
		}

		System.out.println(planEx.getPlanName() + " " + planEx.getPlanStatus());
		Example<InsurancePlans> example = Example.of(planEx);
		List<InsurancePlans> result = repo.findAll(example);
		return result;
	}

	@Override
	public List<InsurancePlans> getAllPlans() {

		return repo.findAll();

	}

	@Override
	public List<InsurancePlans> getPlansByName(String planName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InsurancePlans> getPlansByStatus(String planStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InsurancePlans> getPlansByNameAndStatus(String planName, String planStatus) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	/*
	 * @Override public List<InsurancePlans> getPlansByName(String planName) {
	 * 
	 * return repo.findByPlanName(planName); }
	 * 
	 * @Override public List<InsurancePlans> getPlansByStatus(String planStatus) {
	 * return repo.findByPlanStatus(planStatus); }
	 * 
	 * @Override public List<InsurancePlans> getPlansByNameAndStatus(String
	 * planName, String planStatus) {
	 * 
	 * return repo.findByPlanNameAndPlanStatus(planName, planStatus); }
	 */

}
