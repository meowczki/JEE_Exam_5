package pl.coderslab.meowczki.JEE_Exam_5.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({ "param1", "param2" })
public class Exam5Controller {
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
//	składa się tylko z małych liter
//	ma rozszerzenie (gif, jpg, png)
//	ma kropkę przed rozszerzeniem np.: cat.jpg
	@RequestMapping("regex/{param1}")
	public ModelAndView regexView(@PathVariable String param1) {
		ModelAndView mav = new ModelAndView("regex");
		Pattern compiledPattern = Pattern.compile("^[a-z]+.(gif|jpg|png)$");
		Matcher matcher = compiledPattern.matcher(param1);
		if(matcher.matches()) {
			param1="cat.jpg";
			
		}else {
			
			mav.addObject("tekst", "niepoprwana nazwa");
		}
		return mav;
	}
}
