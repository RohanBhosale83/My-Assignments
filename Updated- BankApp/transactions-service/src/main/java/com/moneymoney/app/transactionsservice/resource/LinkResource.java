package com.moneymoney.app.transactionsservice.resource;

import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.hateoas.ResourceSupport;

import com.moneymoney.app.transactionsservice.entity.Transaction;

public class LinkResource extends ResourceSupport  {

	final Transaction transaction;
	
	public LinkResource(final Transaction transaction) {
		this.transaction=transaction;
	}
	
	Link link = new Link(null);
}
