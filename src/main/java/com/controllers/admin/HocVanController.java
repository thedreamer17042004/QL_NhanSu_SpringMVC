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
import com.entities.hocvan.HocVanPage;
import com.entities.hocvan.HocVanView;
import com.services.dao.IChucVu;
import com.services.dao.IHocVan;

@Controller
@RequestMapping("admin1")
public class HocVanController {
	@Autowired
	IHocVan hocVanService;
	
	@RequestMapping(value = "hocvan", method =  RequestMethod.GET)
	public String index(String searchname,Integer pageno,Model model) {
		
	
		pageno = pageno == null ? 1 : pageno;
		searchname = searchname == null ? "" : searchname;
	
		HocVanPage hocVanPage = hocVanService.paginate(searchname, pageno, 3);
	
	
		model.addAttribute("hocvans", hocVanPage.getHocvans());
		model.addAttribute("totalpage", hocVanPage.getTotalPages());
		model.addAttribute("searchname", searchname);
		model.addAttribute("totalRecords", hocVanPage.getTotalRecord());
		model.addAttribute("currentpage", pageno);
		model.addAttribute("page", "index");
		model.addAttribute("model", "hocvan");
		
		return "admin";
	}
		
	
	
	@RequestMapping(value= "hocvan/add", method = RequestMethod.GET)
	public String add(Model model) 
	{
		model.addAttribute("page", "add");
		model.addAttribute("model", "hocvan");
		return "admin";
		
	}
	
	@RequestMapping(value = "hocvan/hocvan-post", method = RequestMethod.POST)
	public String insert(Model model,HttpServletRequest req, @RequestParam(name= "name") String name)
	{
		
		if(name.equals("")) {
			model.addAttribute("error", "Name không được để trống");
			
			model.addAttribute("page", "add");
			model.addAttribute("model", "hocvan");
			return "admin";
		}

		
		try {
			boolean save = hocVanService.insert(name);
			
			if(save) {
				
				return "redirect:/admin1/hocvan";
			}
			else {
				model.addAttribute("error", "Thêm mới không thành công");
				
				model.addAttribute("page", "add");
				model.addAttribute("model", "hocvan");
				return "admin";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			System.out.println("Loi đay"+e.getMessage());
			
			model.addAttribute("page", "add");
			model.addAttribute("model", "hocvan");
			return "admin";
			
		}
	
	}
	
	@RequestMapping(value ="hocvan/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id,Model model)
	{
		
		HocVanView hvv = hocVanService.edit(Integer.parseInt(id));

	     //lấy category name from categorylanguage and picture from category

		model.addAttribute("hocvan", hvv);
		model.addAttribute("page", "edit");
		model.addAttribute("model", "hocvan");
		return "admin";
		
		
		
	}
	
	
	@RequestMapping(value= "hocvan/update-post", method = RequestMethod.POST)
	public String update(Model model
			,@RequestParam("id") String id,
			@RequestParam("name") String name,	HttpServletRequest req)
	{
		
		//lấy thông tin vào update

		
		if(name.equals("")||name.equals(null)) {
			return "redirect:/admin1/hocvan/edit/" + id;
		}
		
		boolean save = hocVanService.update(Integer.parseInt(id), name);
		
		if(save) {
			
			return "redirect:/admin1/hocvan";
		}
		else {
			model.addAttribute("error", "Cập nhật không thành công");
				
			return "redirect:/admin1/hocvan/edit/"+id;
		}	

	}

	
	@RequestMapping(value = "hocvan/delete/{id}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable("id") String id, Model model) {
		

		boolean delete = hocVanService.delete(Integer.parseInt(id));
		if(delete) {
			return "redirect:/admin1/hocvan";
		}
		model.addAttribute("msg", "xóa thất bại");
		return "redirect:/admin1/hocvan";
	}
	
	
}
