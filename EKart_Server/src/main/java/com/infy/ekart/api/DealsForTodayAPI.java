package com.infy.ekart.api;


import java.util.List;



import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.service.DealsForTodayService;

@CrossOrigin
@RestController
@RequestMapping(value="DealsForTodayAPI-api")
@Validated
public class DealsForTodayAPI {
	
	@Autowired
	private DealsForTodayService dealsForTodayService;
	
	@Autowired
	private Environment environment;
	
	static Log logger = LogFactory.getLog(DealsForTodayAPI.class);
	
	@PostMapping(value = "addProductToDeals")
	public ResponseEntity<String> addProductToDeals(@RequestBody @Valid DealsForTodayDTO dealsDTO) throws EKartException
	{
		logger.info("ADDING PRODUCT TO DEAL FOR TODAY ,PRODUCT NAME: "+dealsDTO.getProductDTO().getName()+"\tSELLER NAME: "+dealsDTO.getSellerEmailId());
		Integer id=dealsForTodayService.addProductToDealsForToday(dealsDTO);
		logger.info("PRODUCT ADDED SUCCESSFULLY TO DEALS FOR TODAY,PRODUCT NAME: "+dealsDTO.getProductDTO().getName()+"\tSELLER NAME: "+dealsDTO.getSellerEmailId());
		String message=environment.getProperty("DealsForTodayAPI.PRODUCT_ADDED_TO_DEALS_FOR_TODAY")+" with id: "+id;
		dealsDTO.setSuccessMessage(message);
		dealsDTO.setDealId(id);
		return new ResponseEntity<String>(message,HttpStatus.CREATED);
	}

	
	 @GetMapping(value="viewProductToDeals/{sellerEmailId}")
	    public ResponseEntity<List<DealsForTodayDTO>> viewProductToDeals(@PathVariable("sellerEmailId") String sellerEmailId) throws EKartException
	    {
	    	
	    	List<DealsForTodayDTO> list = dealsForTodayService.viewProductOfDealsForToday(sellerEmailId);
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
	    @GetMapping(value="/sellerDealsForToday/{sellerEmailId}")
	    public ResponseEntity<List<DealsForTodayDTO>> getSellerDealsForToday(@PathVariable String sellerEmailId) throws EKartException
	    {
	    	
	    	List<DealsForTodayDTO> list = dealsForTodayService.viewSellerDealsForToday(sellerEmailId);
			return new ResponseEntity<>(list,HttpStatus.OK);
	    }
}
