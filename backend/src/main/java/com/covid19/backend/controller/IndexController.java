package com.covid19.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 主页转发到静态页面
 */
@Controller(value = "/")
public class IndexController {

	@RequestMapping("/")
	@ApiIgnore
	public String index(Model model){
		model.addAttribute("title", "JiYan API");
		return "forward:home.html";
	}
}
