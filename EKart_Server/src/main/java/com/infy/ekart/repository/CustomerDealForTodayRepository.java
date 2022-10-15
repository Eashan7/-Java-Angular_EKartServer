package com.infy.ekart.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.DealsForToday;

@Repository(value = "customerDealForTodayRepository")
public interface CustomerDealForTodayRepository extends PagingAndSortingRepository<DealsForToday,Integer>{
	
}