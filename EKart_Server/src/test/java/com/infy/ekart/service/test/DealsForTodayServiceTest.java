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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.infy.ekart.dto.DealsForTodayDTO;
import com.infy.ekart.dto.ProductDTO;

import com.infy.ekart.entity.DealsForToday;
import com.infy.ekart.entity.Product;

import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.DealsForTodayRepository;
import com.infy.ekart.repository.ProductRepository;
import com.infy.ekart.service.DealsForTodayService;
import com.infy.ekart.service.DealsForTodayServiceImpl;

@SpringBootTest(classes= {DealsForTodayServiceTest.class})
@ExtendWith(SpringExtension.class)
public class DealsForTodayServiceTest {
	
	@Mock
	private DealsForTodayRepository dealsForTodayRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private DealsForTodayService dealsForTodayService=new DealsForTodayServiceImpl();
	
	@Test

	public void addProductToDealsForTodayValidTest() throws EKartException{
		DealsForTodayDTO dealDTO =new DealsForTodayDTO();
		dealDTO.setDealId(1001);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setBrand("Motobot");
		productDTO.setCategory("Electronics - Mobile");
		productDTO.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		productDTO.setDiscount(5.0);
		productDTO.setName("Xpress");
		productDTO.setPrice(16000.0);
		productDTO.setProductId(1001);
		productDTO.setQuantity(150);
		dealDTO.setProductDTO(productDTO);
		dealDTO.setDealDiscount(15.0);
		dealDTO.setDealStartsAt(LocalDateTime.now());
		dealDTO.setDealEndsAt(LocalDateTime.now().plusDays(4));
		dealDTO.setSellerEmailId("jacky123@gmail.com");
		
		dealDTO.setErrorMessage(null);
		dealDTO.setSuccessMessage(null);
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
		
		
		Mockito.when(dealsForTodayRepository.save(Mockito.any())).thenReturn(deal);
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
		Mockito.when(dealsForTodayRepository.findByProductProductId(Mockito.anyInt())).thenReturn(null);
		Assertions.assertEquals(dealDTO.getDealId(), dealsForTodayService.addProductToDealsForToday(dealDTO));
		
		
	}
	
	@Test

	public void addProductToDealsForTodayProductFoundTest() throws EKartException{
		DealsForTodayDTO dealDTO =new DealsForTodayDTO();
		dealDTO.setDealId(1001);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setBrand("Motobot");
		productDTO.setCategory("Electronics - Mobile");
		productDTO.setDescription("Smart phone with (13+13) MP rear camera and 8MP front camera, 4GB RAM and 64GB ROM,5.5 inch FHD display, Snapdrag 625 processor");
		productDTO.setDiscount(5.0);
		productDTO.setName("Xpress");
		productDTO.setPrice(16000.0);
		productDTO.setProductId(1001);
		productDTO.setQuantity(150);
		dealDTO.setProductDTO(productDTO);
		dealDTO.setDealDiscount(15.0);
		dealDTO.setDealStartsAt(LocalDateTime.now());
		dealDTO.setDealEndsAt(LocalDateTime.now().plusDays(4));
		dealDTO.setSellerEmailId("jacky123@gmail.com");
		
		dealDTO.setErrorMessage(null);
		dealDTO.setSuccessMessage(null);
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
		
		
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
		Mockito.when(dealsForTodayRepository.findByProductProductId(Mockito.anyInt())).thenReturn(deal);
		Exception e=Assertions.assertThrows(EKartException.class, ()->dealsForTodayService.addProductToDealsForToday(dealDTO));
		Assertions.assertEquals("DealsForTodayService.PRODUCT_ALREADY_PRESENT", e.getMessage());
		
		
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
		Mockito.when(dealsForTodayRepository.findBySellerEmailId(Mockito.anyString())).thenReturn(l);
		Assertions.assertNotNull(dealsForTodayService.viewProductOfDealsForToday("qwe@infosys.com"));
		
		
	}
	
	@Test
	public void viewProductOfDealsForTodayInvalidTest() throws EKartException{
		List<DealsForToday> l=new ArrayList<>();
		Mockito.when(dealsForTodayRepository.findBySellerEmailId(Mockito.anyString())).thenReturn(l);
		Exception e=Assertions.assertThrows(EKartException.class, ()->dealsForTodayService.viewProductOfDealsForToday("jacky@infosys.com"));
		Assertions.assertEquals("DealsForTodayService.NO_PRODUCTS_IN_THIS_PAGE", e.getMessage());
		
		
		
	}
}
