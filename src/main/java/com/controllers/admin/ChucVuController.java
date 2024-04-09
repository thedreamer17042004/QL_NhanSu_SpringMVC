package com.controllers.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.services.dao.IChucVu;

@Controller
@RequestMapping("admin1")
public class ChucVuController {
	@Autowired
	IChucVu chucVuService;
	
	@RequestMapping(value = "chucvu", method =  RequestMethod.GET)
	public String index(String searchname,Integer pageno,Model model) {
		
	
		pageno = pageno == null ? 1 : pageno;
		searchname = searchname == null ? "" : searchname;
	
		ChucVuPage chucVuPage = chucVuService.paginate(searchname, pageno, 3);
	
	
		model.addAttribute("chucVus", chucVuPage.getChucvus());
		model.addAttribute("totalpage", chucVuPage.getTotalPages());
		model.addAttribute("searchname", searchname);
		model.addAttribute("totalRecords", chucVuPage.getTotalRecord());
		model.addAttribute("currentpage", pageno);
		model.addAttribute("page", "index");
		model.addAttribute("model", "chucvu");
		
		return "admin";
	}
		
	
	
	@RequestMapping(value= "chucvu/add", method = RequestMethod.GET)
	public String add(Model model) 
	{

		
		model.addAttribute("page", "add");
		model.addAttribute("model", "chucvu");
		return "admin";
		
	}
	
	@RequestMapping(value = "chucvu/chucvu-post", method = RequestMethod.POST)
	public String insert(Model model,HttpServletRequest req, @RequestParam(name= "name") String name)
	{
		
		if(name.equals("")) {
			model.addAttribute("error", "Name không được để trống");
			
			model.addAttribute("page", "add");
			model.addAttribute("model", "chucvu");
			return "admin";
		}

		
		try {
			boolean save = chucVuService.insert(name);
			
			if(save) {
				
				return "redirect:/admin1/chucvu";
			}
			else {
				model.addAttribute("error", "Thêm mới không thành công");
				
				model.addAttribute("page", "add");
				model.addAttribute("model", "chucvu");
				return "admin";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			System.out.println("Loi đay"+e.getMessage());
			
			model.addAttribute("page", "add");
			model.addAttribute("model", "chucvu");
			return "admin";
			
		}
	
	}
	
	@RequestMapping(value ="chucvu/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id,Model model)
	{
		
		ChucVuView cvv = chucVuService.edit(id);

	     //lấy category name from categorylanguage and picture from category

		model.addAttribute("chucvu", cvv);
		model.addAttribute("page", "edit");
		model.addAttribute("model", "chucvu");
		return "admin";
		
		
		
	}
	
	
	@RequestMapping(value= "chucvu/update-post", method = RequestMethod.POST)
	public String update(Model model
			,@RequestParam("id") String id,
			@RequestParam("name") String name,	HttpServletRequest req)
	{
		
		//lấy thông tin vào update

		
		if(name.equals("")||name.equals(null)) {
			return "redirect:/admin1/chucvu/edit/" + id;
		}
		
		boolean save = chucVuService.update(id, name);
		
		if(save) {
			
			return "redirect:/admin1/chucvu";
		}
		else {
			model.addAttribute("error", "Cập nhật không thành công");
				
			return "redirect:/admin1/chucvu/edit/"+id;
		}	

	}

	
	@RequestMapping(value = "chucvu/delete/{id}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable("id") String id, Model model) {
		

		boolean delete = chucVuService.delete(id);
		if(delete) {
			return "redirect:/admin1/chucvu";
		}
		model.addAttribute("msg", "xóa thất bại");
		return "redirect:/admin1/chucvu";
	}
	
	
	
	
}
