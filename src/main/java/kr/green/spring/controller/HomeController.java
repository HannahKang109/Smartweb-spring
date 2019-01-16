package kr.green.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.spring.service.AccountService;
import kr.green.spring.vo.AccountVo;

/**
 * Handles requests for the application home page.
 * @Controller - annotation을 해 주면 이름만 붙이는 것이 아니라 기능까지 구현
 * logger - 로그한 기록을 보여줌
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private AccountService accountService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeGet(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		/*컨트롤러에서 jsp로 데이터를 전달하기 위해서
		 * 1. 매개변수에 Model 객체를 추가 : Model model
		 * 2. model.addAttribute()를 통해 데이터 전달
		 * 	  model.addAttribute("jsp에서 받는 이름", 전달할 데이터);*/
		
		model.addAttribute("serverTime", formattedDate );
		System.out.println(accountService.getAccount("hannahkang109"));
		
		return "home";
	}
		
		@RequestMapping(value = "/", method = RequestMethod.POST)
		public String homePost(String name) {
			/* jsp에서 넘겨준 데이터를 받기 위해 태그의 속성 name과 일치하는 이름을 매개변수의 이름으로 설정*/
			System.out.println("jsp에서 넘어온 값 :" + name);
			return "redirect:/";
		
	}
		@RequestMapping(value = "/signup", method = RequestMethod.GET)
		public String signupGet(Boolean fail, Model model) {
			if(fail == null) {
				fail = false;
			}
			model.addAttribute("fail", fail);
			return "account/signup";
		}
		//int -> Integer, double -> Double, boolean -> Boolean(null값 허용) Wrapper 클래스
		@RequestMapping(value = "/signup", method = RequestMethod.POST)
		public String signupPost(AccountVo accountVo, Model model) {
			if(accountService.signup(accountVo))
				return "redirect:/";
			else {
				model.addAttribute("fail",true);
				return "redirect:/signup";
			}
			
		}
}