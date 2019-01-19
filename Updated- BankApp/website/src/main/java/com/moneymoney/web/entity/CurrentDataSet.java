package com.moneymoney.web.entity;

import java.util.List;

public class CurrentDataSet {

	private Link link;
	private List<Transaction> transactions;
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public Link getPreviousLink() {
		return previousLink;
	}
	public void setPreviousLink(Link previousLink) {
		this.previousLink = previousLink;
	}
	public Link getNextLink() {
		return nextLink;
	}
	public void setNextLink(Link nextLink) {
		this.nextLink = nextLink;
	}
	private Link previousLink;
	private Link nextLink;
	@Override
	public String toString() {
		return "CurrentDataSet [transactions=" + transactions + ", getTransactions()=" + getTransactions() + "]";
	}
	
	
	
}
