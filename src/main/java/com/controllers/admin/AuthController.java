package com.controllers.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.user.Account;
import com.entities.user.AccountDetails;
import com.services.dao.IAccount;
import com.validation.RegisterValidation;

@Controller
public class AuthController {
	@Autowired
	IAccount accountService;
	
	@RequestMapping(value= {"/login", "/"})
	public String login (@Nullable @RequestParam(value = "error") String error, Model model, HttpServletRequest req)
	{
		
		if(error!=null)
		{
			model.addAttribute("msg", "Invalid email and password!");
		}
		
		return "admin/login";
	}
	

	@RequestMapping(value = "/checkrole")
	public String checkRole() {
		//lấy thông tin tài khoản
		AccountDetails account = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		//duyệt role để kiểm tra và điều hướng
	
		for(var g: account.getAuthorities()) {
		
			if(g.getAuthority().equals("ROLE_ADMIN")) {
		
				return "redirect:/admin1/";
		
			}
	
			if(g.getAuthority().equals("ROLE_MANAGER")) {
		
			return "redirect:/student/";
		
			}
			if(g.getAuthority().equals("ROLE_USER")) {
				
				return "redirect:/user/";
			
			}
		}
	
		return "errorpage/403";

	}
//	
//
//	
	@RequestMapping(value = {"register"}, method = RequestMethod.GET)
	public String register(Model model)
	{
		model.addAttribute("account", new Account());
		return "admin/register";
	}
//	
	@RequestMapping(value = {"post-register"}, method = RequestMethod.POST)
	public String registerPost(@Validated(RegisterValidation.class) @ModelAttribute("account") Account acc,BindingResult result,Model model)
	{
		if(result.hasErrors()) {
			model.addAttribute("account", acc);
			return "admin/register";
		}
		
		Account account  = accountService.register(acc);
		
		if(account!=null) {
			
			model.addAttribute("msg", "Register thành công");
			return "redirect:/login/";
		}
		model.addAttribute("msg", "Register thất bại");
		return "admin/register";
	}

	

	@RequestMapping("/logoutSuccess")
	public String logout(Model model,HttpServletRequest req) {


		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:/login";

	}
//
	@RequestMapping("/403")
	public String accessDenied(Model model) {
		model.addAttribute("msg", "BẠN KHÔNG CÓ QUYỀN TRUY CẬP VÀO TRANG NÀY");
		return "/errorpage/403";
	}
}
