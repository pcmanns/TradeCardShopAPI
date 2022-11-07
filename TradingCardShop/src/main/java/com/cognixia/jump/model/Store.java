package com.cognixia.jump.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Store {

	
	@Id
	private String id;
	private String ownerid;
	@NotBlank
	@Indexed(unique = true)
	private String storeName;
	
	private List<Cards> cardCollection=new ArrayList<Cards>();
	
	private List<Order> tradesOrder=new ArrayList<Order>();
	
	

	public Store(String id, @NotBlank String ownerid, @NotBlank String storeName, List<Cards> cardCollection,
			List<Order> tradesOrder) {
		super();
		this.id = id;
		this.ownerid = ownerid;
		this.storeName = storeName;
		this.cardCollection = cardCollection;
		this.tradesOrder = tradesOrder;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", ownerid=" + ownerid + ", StoreName=" + storeName + ", cardCollection="
				+ cardCollection + ", tradesOrder=" + tradesOrder + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public List<Cards> getCardCollection() {
		return cardCollection;
	}

	public void setCardCollection(List<Cards> cardCollection) {
		this.cardCollection = cardCollection;
	}

	public List<Order> getTradesOrder() {
		return tradesOrder;
	}

	public void setTradesOrder(List<Order> tradesOrder) {
		this.tradesOrder = tradesOrder;
	}


	
	
	
}
