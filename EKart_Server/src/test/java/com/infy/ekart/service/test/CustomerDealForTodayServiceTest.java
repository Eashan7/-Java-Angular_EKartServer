package com.infy.ekart.service.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.infy.ekart.entity.DealsForToday;
import com.infy.ekart.entity.Product;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.CustomerDealForTodayRepository;
import com.infy.ekart.service.CustomerDealForTodayService;
import com.infy.ekart.service.CustomerDealForTodayServiceImpl;


@SpringBootTest(classes= {CustomerDealForTodayServiceTest.class})
@ExtendWith(SpringExtension.class)
public class CustomerDealForTodayServiceTest {
	
	@Mock
	private CustomerDealForTodayRepository customerDealForTodayRepository;
	
	@InjectMocks
	private CustomerDealForTodayService customerDealForTodayService=new CustomerDealForTodayServiceImpl();
	
	@Test
	public void viewProductOfDealsForTodayNoProductFoundTest() throws EKartException{
		List<DealsForToday> l=new ArrayList<>();
		PageImpl<DealsForToday> page=new PageImpl<DealsForToday>(l);
		
		Mockito.when(customerDealForTodayRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
		Exception e=Assertions.assertThrows(EKartException.class,()->customerDealForTodayService.viewProductOfDealsForToday());
		Assertions.assertEquals("CustomerDealForTodayService.NO_PRODUCTS_IN_THIS_PAGE", e.getMessage());
	}	
		@Test
		public void viewProductOfDealsForTodayValidTest() throws EKartException{
			List<DealsForToday> l=new ArrayList<>();
			DealsForToday deal=new DealsForToday();
			deal.setDealId(1001);
			Product product = new Product();
			product.setBrand("Motobot");
			product.setCategory("Electronics - Mobile");
			product.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
			product.setDiscount(5.0);
			product.setName("Xpress");
			product.setPrice(16000.0);
			product.setProductId(1001);
			product.setQuantity(150);
			deal.setProduct(product);
			deal.setDealDiscount(15.0);
			deal.setDealStartsAt(LocalDateTime.now());
			deal.setDealEndsAt(LocalDateTime.now().plusDays(4));
			deal.setSellerEmailId("jacky123@gmail.com");
			l.add(deal);
			PageImpl<DealsForToday> page=new PageImpl<DealsForToday>(l);
			
			Mockito.when(customerDealForTodayRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
			Assertions.assertNotNull(customerDealForTodayService.viewProductOfDealsForToday());
				
	}
	
}
