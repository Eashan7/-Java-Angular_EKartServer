package com.infy.ekart.service;

import java.util.List;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.exception.EKartException;

public interface DealsForTodayService {
	Integer addProductToDealsForToday(DealsForTodayDTO dealsForTodayDTO) throws EKartException;
	
	List<DealsForTodayDTO> viewProductOfDealsForToday(String sellerEmailId) throws EKartException;
	
	List<DealsForTodayDTO> viewSellerDealsForToday(String sellerEmailId) throws EKartException;
}
