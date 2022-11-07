package com.cognixia.jump.model;

import java.util.List;

public class Order {
	
	private String customerUserName;
	private String storeName;
	private List<String> card;
	private List<Integer> requestCopys;
	private double total;
	public Order(String customerUserName, String storeName, List<String> card, List<Integer> requestCopys,
			double total) {
		super();
		this.customerUserName = customerUserName;
		this.storeName = storeName;
		this.card = card;
		this.requestCopys = requestCopys;
		this.total = total;
	}
	@Override
	public String toString() {
		return "Order [customerUserName=" + customerUserName + ", storeName=" + storeName + ", card=" + card
				+ ", requestCopys=" + requestCopys + ", total=" + total + "]";
	}
	public String getCustomerUserName() {
		return customerUserName;
	}
	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public List<String> getCard() {
		return card;
	}
	public void setCard(List<String> card) {
		this.card = card;
	}
	public List<Integer> getRequestCopys() {
		return requestCopys;
	}
	public void setRequestCopys(List<Integer> requestCopys) {
		this.requestCopys = requestCopys;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
