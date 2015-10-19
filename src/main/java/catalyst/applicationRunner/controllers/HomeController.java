package catalyst.applicationRunner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(){
		return "/home.html";
	}
	
	@RequestMapping(value = "/todo/update", method = RequestMethod.GET)
	public String update(){
		return "/todo-update.html";
	}
	
	
	@RequestMapping(value = "/todo/create", method = RequestMethod.GET)
	public String create(){
		return "/todo-create.html";
	}
	
	@RequestMapping(value = "/todo/index", method = RequestMethod.GET)
	public String index(){
		return "/todo-index.html";
	}
	
	@RequestMapping(value = "/todo/delete", method = RequestMethod.GET)
	public String delete(){
		return "/todo-delete.html";
	}

}
