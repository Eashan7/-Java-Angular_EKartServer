package com.infy.ekart.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.service.CustomerDealForTodayService;
@CrossOrigin
@RestController
@RequestMapping(value = "CustomerDealsForTodayAPI")
public class CustomerDealsForTodayAPI {
	
	@Autowired
	private CustomerDealForTodayService customerDealForTodayService;
	
	@GetMapping(value = "viewAllDealsForCustomer")
	public ResponseEntity<List<DealsForTodayDTO>> viewAllDealsForCustomer() throws EKartException{
		List<DealsForTodayDTO> dealsList=customerDealForTodayService.viewProductOfDealsForToday();
		return new ResponseEntity<List<DealsForTodayDTO>>(dealsList,HttpStatus.OK);
	}

}
