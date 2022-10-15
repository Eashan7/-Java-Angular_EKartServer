package com.infy.ekart.service;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.dto.ProductDTO;
import com.infy.ekart.entity.DealsForToday;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.CustomerDealForTodayRepository;


@Service(value = "customerDealForTodayService")
@Transactional
public class CustomerDealForTodayServiceImpl implements CustomerDealForTodayService{
	
	@Autowired
	private CustomerDealForTodayRepository customerDealForTodayRepository;
	
	
	@Override
	public List<DealsForTodayDTO> viewProductOfDealsForToday() throws EKartException {

	Pageable pageable = PageRequest.of(0, 10);
	Page<DealsForToday> entityLists = customerDealForTodayRepository.findAll(pageable);
	if (entityLists.isEmpty()) {
		throw new EKartException("CustomerDealForTodayService.NO_PRODUCTS_IN_THIS_PAGE");
	}
	List<DealsForToday> entityList = entityLists.getContent();
	List<DealsForTodayDTO> dealsForTodayDTOs=new ArrayList<>();

	entityList.forEach(ds->{
		String dealMsg;
		DealsForTodayDTO p = new DealsForTodayDTO();
		ProductDTO pds=new ProductDTO();
		p.setDealDiscount(ds.getDealDiscount());
		p.setDealEndsAt(ds.getDealEndsAt());
		p.setDealStartsAt(ds.getDealStartsAt());
		p.setDealId(ds.getDealId());	
		p.setSellerEmailId(ds.getSellerEmailId());
		pds.setBrand(ds.getProduct().getBrand());
		pds.setCategory(ds.getProduct().getCategory());
		pds.setDiscount(ds.getProduct().getDiscount());
		pds.setDescription(ds.getProduct().getDescription());
		pds.setPrice(ds.getProduct().getPrice());
		pds.setQuantity(ds.getProduct().getQuantity());
		pds.setProductId(ds.getProduct().getProductId());
		pds.setName(ds.getProduct().getName());
		p.setProductDTO(pds);
	
	LocalDate startDate=p.getDealStartsAt().toLocalDate();
	LocalDate endDate=p.getDealEndsAt().toLocalDate();
	LocalDate today=LocalDate.now();
	
	if((today.isAfter(startDate) && today.isBefore(endDate)) || (today.isEqual(startDate) || (today.isEqual(endDate))))
	{
		if(p.getDealEndsAt().isBefore(LocalDateTime.now())) 
			dealMsg="Deal Completed";		    		
		else if(p.getDealStartsAt().isBefore(LocalDateTime.now()) && p.getDealEndsAt().isAfter(LocalDateTime.now())) 
			dealMsg="Deal Is On";
		else
			dealMsg="Deal Yet To Start";
	p.setDealMsg(dealMsg);
}
	dealsForTodayDTOs.add(p);
    		    
	});
return dealsForTodayDTOs;

	}
}
