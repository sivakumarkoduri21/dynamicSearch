package com.ashokit.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.InsurancePlans;

public interface planRepository extends JpaRepository<InsurancePlans, Integer> {

	
	/*
	 * @Query("from InsurancePlans where  planName= ?1") List<InsurancePlans>
	 * findByPlanName(String planName);
	 * 
	 * @Query("from InsurancePlans where  planStatus= ?1") List<InsurancePlans>
	 * findByPlanStatus(String planStatus);
	 * 
	 * @Query("from InsurancePlans where planName=?1 and planStatus= ?2 ")
	 * List<InsurancePlans> findByPlanNameAndPlanStatus(String planName,String
	 * planStatus);
	 */
}
