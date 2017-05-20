package com.fly.bean;

/**
 * Created by adarsh on 05/04/17.
 */
public class TransactionBean {
	
	
	private String source;
	private String destination;
	private String comment;
	private String date;
	private String toFrom;
	private String creditDebit;
	private Double amount;
	private Double newBalance;
	
	
	
	public String getToFrom() {
		return toFrom;
	}
	public void setToFrom(String toFrom) {
		this.toFrom = toFrom;
	}
	public String getCreditDebit() {
		return creditDebit;
	}
	public void setCreditDebit(String creditDebit) {
		this.creditDebit = creditDebit;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getNewBalance() {
		return newBalance;
	}
	public void setNewBalance(Double newBalance) {
		this.newBalance = newBalance;
	}
    
	

}
