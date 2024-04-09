package com.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entities.user.Account;
import com.entities.user.AccountDetails;
import com.entities.user.AccountPage;
import com.entities.user.AccountRole;
import com.entities.user.Role;
import com.services.dao.IAccount;
import com.services.dao.IRole;
import com.validation.AddUserValidation;

@Controller
@RequestMapping("/admin1")
public class AccountController {
	@Autowired
	IAccount accountService;
	
	@Autowired
	IRole roleService;
	
	
	
//	DASHBOARD PAGE
	@RequestMapping(value = "/")
	public String index(Model model)
	{
		
		model.addAttribute("page","index");
		model.addAttribute("model","dashboard");
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountDetails account = (AccountDetails) principal;
        model.addAttribute("account", account);
        
		return "admin";
	}
	
//	USER CONTROLLER
	@RequestMapping(value= {"user"})
	public String user(String searchname,Integer pageno,Model model) 
	{
		
		pageno = pageno == null ? 1 : pageno;
		searchname = searchname == null ? "" : searchname;
		
		AccountPage accountPage = accountService.paginate(searchname, pageno, 10);
		model.addAttribute("accounts", accountPage.getAccounts());
		model.addAttribute("totalpage", accountPage.getTotalPages());
		model.addAttribute("searchname", searchname);
		model.addAttribute("totalRecords", accountPage.getTotalRecord());
		model.addAttribute("currentpage", pageno);
		model.addAttribute("page", "user");
		model.addAttribute("model", "user");
		return "admin";
	}
	
	
	
	@RequestMapping(value= "user/add", method = RequestMethod.GET)
	public String add(Model model) 
	{
		List<Role> roles = roleService.list();
		model.addAttribute("account", new Account());
		model.addAttribute("page", "add");
		model.addAttribute("model", "user");
		model.addAttribute("roles", roles);
		return "admin";
		
	}
	
	@RequestMapping(value = "user/user-post", method = RequestMethod.POST)
	public String insert(@ModelAttribute("account") @Validated(AddUserValidation.class) Account account,BindingResult errors, Model model
			,@RequestParam("file") MultipartFile file,
			HttpServletRequest req, @RequestParam(name= "role[]", required = false) int[] roleId
			)
	{

		List<Role> roles = roleService.list();

		if(errors.hasErrors()) {
			
			
			model.addAttribute("acd", account);
			model.addAttribute("page", "add");
			model.addAttribute("model", "user");
			model.addAttribute("roles", roles);
	
			return "admin";
		}
		
	
	if(!file.getOriginalFilename().equals("")) {
		
		String uploadRootPath = req.getServletContext().getRealPath("resources/images");
		File destination = new File(uploadRootPath+"/"+file.getOriginalFilename());

		try {
			file.transferTo(destination);

		} catch (IllegalStateException | IOException e) {

			e.printStackTrace();

		}
		account.setPicture("images/"+file.getOriginalFilename());
		try {
			boolean save = accountService.insert(account, roleId);
			
			if(save) {
				
				return "redirect:/admin1/user";
			}
			else {
				model.addAttribute("error", "Thêm mới không thành công");
				model.addAttribute("acd", account);
				model.addAttribute("page", "add");
				model.addAttribute("model", "user");
				model.addAttribute("roles", roles);
		
				return "admin";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			model.addAttribute("acd", account);
			model.addAttribute("page", "add");
			model.addAttribute("model", "user");
			model.addAttribute("roles", roles);
	
			return "admin";
			
		}
	
		

	}else {
		try {
			
			account.setPicture(null);
			boolean save = accountService.insert(account, roleId);
			if(save) {
				
				return "redirect:/admin1/user";
			}
			else {
				model.addAttribute("error", "Thêm mới không thành công");
				model.addAttribute("acd", account);
				model.addAttribute("page", "add");
				model.addAttribute("model", "user");
				model.addAttribute("roles", roles);

				return "admin";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			model.addAttribute("acd", account);
			model.addAttribute("page", "add");
			model.addAttribute("model", "user");
			model.addAttribute("roles", roles);
	
			return "admin";
			
		}
		
		
		
	}		
	
	}
//	
//	
	@RequestMapping(value ="user/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id,Model model)
	{
		List<Role> roles = roleService.list();
		Account account = accountService.getById(id);
		System.out.println(account.getEmail());

	     
		try {
		    Set<AccountRole> rolesUser = account.getAccountroles();
		    
			 Set<Integer> roleId = new HashSet<>();
				if(rolesUser.size()>0) {
					for(AccountRole accountRole: rolesUser)
					{
						int roleid = accountRole.getRole().getRoleId();
						roleId.add(roleid);
						
					}
				
				}
			
				model.addAttribute("accoun", account);
				model.addAttribute("page", "edit");
				model.addAttribute("model", "user");
				model.addAttribute("rolesUser", roleId);
				model.addAttribute("roles", roles);
				return "admin";
				
		    // Your code to handle rolesUser
		} catch (Exception e) {
		    // Handle the exception here, e.g., log it or perform some fallback action
		    e.printStackTrace(); // This prints the stack trace of the exception
		}
	
		
		return "redirect:/admin1/user";
		
	}
	
	
	@RequestMapping(value= "user/update-post", method = RequestMethod.POST)
	public String update(@ModelAttribute("account") @Validated(AddUserValidation.class) Account account,BindingResult errors, Model model
			,@RequestParam("file") MultipartFile file,@RequestParam("id") String id,@RequestParam("oldPicture")  String oldPicture,
			HttpServletRequest req, @RequestParam(name= "role[]", required = false) int[] roleId)
	{
		
		//lấy thông tin vào update
		if(errors.hasErrors()) {
			return "redirect:/admin1/user/edit/"+id;
		}

		if(!file.getOriginalFilename().equals("")) {
			String uploadRootPath = req.getServletContext().getRealPath("resources/images");
			File destination = new File(uploadRootPath+"/"+file.getOriginalFilename());

			try {
				file.transferTo(destination);

			} catch (IllegalStateException | IOException e) {

			e.printStackTrace();

			}
			account.setPicture("images/"+file.getOriginalFilename());
			boolean save = accountService.update(account, roleId);
			
			if(save) {
				
				return "redirect:/admin1/user";
			}
			else {
				model.addAttribute("error", "Cập nhật không thành công");
					
				return "redirect:/admin1/user/edit/"+id;
			}
		
			

		}else {

			account.setPicture(oldPicture);
			boolean save = accountService.update(account, roleId);
			
			if(save) {
				
				return "redirect:/admin1/user";
			}
			else {
				model.addAttribute("error", "Cập nhật không thành công");
				
				return "redirect:/admin1/user/edit/"+id;
			}
		
		}		
		
		
		
	}
	

	
	@RequestMapping(value = "user/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id, Model model) {
		
		
		boolean delete = accountService.delete(id);
		if(delete) {
			return "redirect:/admin1/user";
		}
		model.addAttribute("msg", "xóa thất bại");
		return "redirect:/admin1/user";
	}

	
	
	

}
