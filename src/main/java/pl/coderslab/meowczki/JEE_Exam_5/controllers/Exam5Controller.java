package pl.coderslab.meowczki.JEE_Exam_5.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

@Controller
@SessionAttributes({ "param1", "param2" })
public class Exam5Controller {
	@Autowired
	private AnimalDao animalDao;

	@RequestMapping("/")
	public String homeView() {
		return "home";
	}

	@RequestMapping(path = "/first/{param1}/{param2}")
	public ModelAndView firstView(@PathVariable String param1, @PathVariable String param2) {
		ModelAndView mav = new ModelAndView("first");
		mav.addObject("param1", param1);
		mav.addObject("param2", param2);
		return mav;
	}

	@RequestMapping("check")
	public String checkView() {
		return "check";
	}

	// składa się tylko z małych liter
	// ma rozszerzenie (gif, jpg, png)
	// ma kropkę przed rozszerzeniem np.: cat.jpg
	@RequestMapping("regex/{param1:.+}")
	public String regexView(@PathVariable String param1, HttpServletRequest request) {
		Pattern compiledPattern = Pattern.compile("^[a-z]+.(gif|jpg|png)$");
		Matcher matcher = compiledPattern.matcher(param1);
		System.out.println(param1);
		if (matcher.matches()) {
			request.setAttribute(param1, "cat.jpg");
			return "redirect:http://google.pl";
		} else {
			return "regex";
		}

	}

	@RequestMapping(path = "createCookie/{cookieName}/{cookieValue}/{cookieTime}")
	public void createCookie(HttpServletResponse response, @PathVariable String cookieName,
			@PathVariable String cookieValue, @PathVariable int cookieTime) {
		Cookie cookie1 = new Cookie(cookieName, cookieValue);
		cookie1.setPath("/");
		cookie1.setMaxAge(cookieTime * 60);
		response.addCookie(cookie1);
	}

	@RequestMapping("/deleteCookie/{cookieName}")
	public void deleteCookie(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String cookieName) {
		Cookie cookie = WebUtils.getCookie(request, cookieName);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);

	}

	@RequestMapping("animals")
	public ModelAndView animals() {
		animalDao = new AnimalDao();
		ModelAndView mav = new ModelAndView("animal");
		mav.addObject("animalList", animalDao.getList());

		return mav;
	}

}
