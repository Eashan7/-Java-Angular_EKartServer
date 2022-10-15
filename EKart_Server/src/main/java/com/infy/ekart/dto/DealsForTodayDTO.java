package com.infy.ekart.dto;

import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

//import com.infy.ekart.entity.Seller;


public class DealsForTodayDTO {
	 private Integer dealId;
	 @NotNull(message = "{deals.absent.product}")
	 @Valid
     private ProductDTO productDTO;
	 private SellerDTO sellerDTO;
	 @NotNull(message = "{deals.absent.dealdiscount}")
     private Double dealDiscount;
	 @NotNull(message = "{deals.absent.dealstartsat}")
     private LocalDateTime dealStartsAt;
	 @NotNull(message = "{deals.absent.dealendsat}")
     private LocalDateTime dealEndsAt;
	 @Valid
	 private String sellerEmailId;
	 private String dealMsg;
	 private String successMessage;
	 private String errorMessage;
	
	public String getDealMsg() {
		return dealMsg;
	}
	public void setDealMsg(String dealMsg) {
		this.dealMsg = dealMsg;
	}
	public Integer getDealId() {
		return dealId;
	}
	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	public Double getDealDiscount() {
		return dealDiscount;
	}
	public void setDealDiscount(Double dealDiscount) {
		this.dealDiscount = dealDiscount;
	}
	public LocalDateTime getDealStartsAt() {
		return dealStartsAt;
	}
	public void setDealStartsAt(LocalDateTime dealStartsAt) {
		this.dealStartsAt = dealStartsAt;
	}
	public LocalDateTime getDealEndsAt() {
		return dealEndsAt;
	}
	public void setDealEndsAt(LocalDateTime dealEndsAt) {
		this.dealEndsAt = dealEndsAt;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getSellerEmailId() {
		return sellerEmailId;
	}
	public void setSellerEmailId(String sellerEmailId) {
		this.sellerEmailId = sellerEmailId;
	}
	@Override
	public String toString() {
		return "DealsForTodayDTO [dealId=" + dealId + ", productDTO=" + productDTO + ", dealDiscount=" + dealDiscount
				+ ", dealStartsAt=" + dealStartsAt + ", dealEndsAt=" + dealEndsAt + ", sellerEmailId=" + sellerEmailId
				+ ", dealMsg=" + dealMsg + ", successMessage=" + successMessage + ", errorMessage=" + errorMessage
				+ "]";
	}
	

	 
	
     
}
