package com.situ.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value = "/indexBack")
	public String index() {
		return "/index_back";
	}

}
