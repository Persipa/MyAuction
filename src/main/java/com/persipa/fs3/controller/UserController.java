package com.persipa.fs3.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.persipa.fs3.pojo.Auctionuser;
import com.persipa.fs3.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param inputCode
	 * @param session
	 * @param mv
	 * @return
	 */
	@RequestMapping("/doLogin")
	public String doLogin(String username, String password, String inputCode, HttpSession session, Model mv) {
		// 验证验证码
		if (!inputCode.equals(session.getAttribute("numrand"))) {
			mv.addAttribute("errorMsg", "验证码错误");
			return "login";
		}

		Auctionuser user = userService.login(username, password);
		if (user != null) {
			session.setAttribute("user", user);
			return "redirect:/auction/findAuctions";
		} else {
			mv.addAttribute("errorMsg", "用户名或密码错误");
			return "login";
		}

	}

	/**
	 * 注册
	 * @param model
	 * @param user
	 * @param bingdingResult
	 * @return
	 */
	@RequestMapping("register")
	public String register(Model model,@Validated Auctionuser user, BindingResult bingdingResult) {

		if (bingdingResult.hasErrors()) {
			/*
		 	 * List<ObjectError> errors = bingdingResult.getAllErrors();
			 * for (ObjectError objectError : errors) {
		     * 	System.out.println(objectError.getDefaultMessage());
		   	 * }
			 * model.addAttribute("errors", errors);
			 * return "register";
			 */
		
			List<FieldError> errors = bingdingResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				model.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return "register";
		}
		
		userService .addUser(user);
		return "login";
	}
	
	/**
	 * 注销
	 * @return
	 */
	@RequestMapping("doLogout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "login";
		
	}
	
}
