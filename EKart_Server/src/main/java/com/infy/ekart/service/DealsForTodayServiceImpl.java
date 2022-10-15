package com.infy.ekart.service;

import java.util.ArrayList;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.dto.ProductDTO;
import com.infy.ekart.dto.SellerDTO;
import com.infy.ekart.entity.DealsForToday;
import com.infy.ekart.entity.Product;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.DealsForTodayRepository;
import com.infy.ekart.repository.ProductRepository;

@Service(value = "dealsForTodayService")
@Transactional
public class DealsForTodayServiceImpl implements DealsForTodayService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private DealsForTodayRepository dealsForTodayRepository;
	
	@Override
	public Integer addProductToDealsForToday(DealsForTodayDTO dealsForTodayDTO) throws EKartException{
		Product product=new Product();
		product.setName(dealsForTodayDTO.getProductDTO().getName());
		product.setBrand(dealsForTodayDTO.getProductDTO().getBrand());
		product.setCategory(dealsForTodayDTO.getProductDTO().getCategory());
		product.setDiscount(dealsForTodayDTO.getProductDTO().getDiscount());
		product.setDescription(dealsForTodayDTO.getProductDTO().getDescription());
		product.setPrice(dealsForTodayDTO.getProductDTO().getPrice());
		product.setQuantity(dealsForTodayDTO.getProductDTO().getQuantity());
		Product productDB=productRepository.save(product);
		DealsForToday dealnew=dealsForTodayRepository.findByProductProductId(productDB.getProductId());
		if(dealnew !=null)
		{
			throw new EKartException("DealsForTodayService.PRODUCT_ALREADY_PRESENT");
		}
		DealsForToday deal=new DealsForToday();
		deal.setProduct(productDB);
		deal.setDealDiscount(dealsForTodayDTO.getDealDiscount());
		deal.setDealStartsAt(dealsForTodayDTO.getDealStartsAt());
		deal.setDealEndsAt(dealsForTodayDTO.getDealEndsAt());

		deal.setSellerEmailId(dealsForTodayDTO.getSellerEmailId());
		DealsForToday dealDB=dealsForTodayRepository.save(deal);
		return dealDB.getDealId();
		
	}
	@Override
	public List<DealsForTodayDTO> viewSellerDealsForToday(String sellerEmailId) throws EKartException
	{
		List<DealsForToday> list = dealsForTodayRepository.findBySellerEmailId(sellerEmailId);
		List<DealsForTodayDTO> dealsForTodayDTOs=new ArrayList<DealsForTodayDTO>();
		if(list==null || list.isEmpty())
			throw new EKartException("DealsForToday.NO_PRODUCTS_ON_DEAL");
		for(DealsForToday d:list) {
			DealsForTodayDTO dto= new DealsForTodayDTO();
			dto.setDealDiscount(d.getDealDiscount());
		    dto.setDealId(d.getDealId());
		    dto.setDealEndsAt(d.getDealEndsAt());
		    dto.setDealStartsAt(d.getDealStartsAt());
		    
		    ProductDTO pdto=new ProductDTO();
		    pdto.setBrand(d.getProduct().getBrand());
		    pdto.setCategory(d.getProduct().getCategory());
		    pdto.setDescription(d.getProduct().getDescription());
		    pdto.setDiscount(d.getProduct().getDiscount());
		    pdto.setQuantity(d.getProduct().getQuantity());
		    pdto.setErrorMessage("Error");
		    pdto.setName(d.getProduct().getName());
		    pdto.setPrice(d.getProduct().getPrice());
		    pdto.setProductId(d.getProduct().getProductId());
		    pdto.setSuccessMessage("success");
		    
//		    SellerDTO sdto=new SellerDTO();
//		    sdto.setName(d.ge);
//		   
		    dealsForTodayDTOs.add(dto);
		    
		}
		return dealsForTodayDTOs;
	}

	@Override
	public List<DealsForTodayDTO> viewProductOfDealsForToday(String sellerEmailId) throws EKartException {

		
		List<DealsForToday> entityList = dealsForTodayRepository.findBySellerEmailId(sellerEmailId);
		if (entityList.isEmpty()) {
			throw new EKartException("DealsForTodayService.NO_PRODUCTS_IN_THIS_PAGE");
		}
		List<DealsForTodayDTO> dealsForTodayDTOs=new ArrayList<>();
		for(DealsForToday ds:entityList){
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
			dealsForTodayDTOs.add(p);
		}
		
		
		return dealsForTodayDTOs;
	
}
}
