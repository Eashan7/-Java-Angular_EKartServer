package com.infy.ekart.repository;


import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.DealsForToday;

@Repository(value="dealsForTodayRepository")
public interface DealsForTodayRepository extends PagingAndSortingRepository<DealsForToday,Integer>{
	DealsForToday findByProductProductId(Integer productId);

 	List<DealsForToday> findBySellerEmailId(String sellerEmailId);

	}


