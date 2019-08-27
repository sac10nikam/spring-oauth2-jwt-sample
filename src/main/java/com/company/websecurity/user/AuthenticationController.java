package com.company.websecurity.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
	@RequestMapping(value = "/products")
	public String getProductName() {
		return "Honey";
	}
}
