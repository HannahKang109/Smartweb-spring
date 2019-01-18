package kr.green.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//uri중 bbs로 들어오는 애들은 아래와 같이 넘어가라
@RequestMapping(value="/bbs")
public class BoardController {
	@RequestMapping(value="/bbs/list")
	public String list() {
		return "bbs/list";
	}
}
