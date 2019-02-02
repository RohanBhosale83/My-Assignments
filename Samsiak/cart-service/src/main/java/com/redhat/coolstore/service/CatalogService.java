package com.redhat.coolstore.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.redhat.coolstore.model.Product;

@FeignClient(name = "catalogService", url = "${catalog.endpoint}")
interface CatalogService {
	@RequestMapping(method = RequestMethod.GET, value = "/api/products")
    List<Product> products();
}