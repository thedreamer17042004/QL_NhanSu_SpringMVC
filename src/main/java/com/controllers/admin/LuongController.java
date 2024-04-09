package com.controllers.admin;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.chamcong.Cong;
import com.entities.chamcong.CongPage;
import com.entities.luong.Luong;
import com.entities.luong.LuongForm;
import com.entities.luong.LuongPage;
import com.entities.nhanvien.NhanVienPage;
import com.services.dao.IChiTietLuong;
import com.services.dao.ICong;
import com.services.dao.ILuong;
import com.services.dao.INhanVien;

@Controller
@RequestMapping("admin1")
public class LuongController {
	@Autowired
	ILuong luongService;
	
	@Autowired
	IChiTietLuong chiTietLuongService;
	
	@Autowired
	INhanVien nhanVienService;
	
	@RequestMapping(value = "luong", method =  RequestMethod.GET)
	public String index(String searchname,Integer pageno,Model model) {
		
	
		pageno = pageno == null ? 1 : pageno;
		searchname = searchname == null ? "" : searchname;
	
		LuongPage luongPage = chiTietLuongService.paginate(searchname, pageno, 3);
	
		model.addAttribute("luongs", luongPage.getLuongs());
		model.addAttribute("totalpage", luongPage.getTotalPages());
		model.addAttribute("searchname", searchname);
		model.addAttribute("totalRecords", luongPage.getTotalRecord());
		model.addAttribute("currentpage", pageno);
		model.addAttribute("page", "index");
		model.addAttribute("model", "luong");
		
		return "admin";
	}
		
	
	
	@RequestMapping(value= "luong/add", method = RequestMethod.GET)
	public String add(Model model) 
	{
	
		NhanVienPage nvp = nhanVienService.paginate(-1, "", -1, "", 1, 100);
		
		model.addAttribute("nhanviens", nvp.getNhanViens());
		model.addAttribute("luong", new LuongForm());
		model.addAttribute("page", "add");
		model.addAttribute("model", "luong");
		return "admin";
		
	}
	
	@RequestMapping(value = "luong/luong-post", method = RequestMethod.POST)
	public String insert(Model model,HttpServletRequest req,
			@Valid @ModelAttribute("luong") LuongForm luong,
			@RequestParam("nhanSu") String ns,
			BindingResult result
			)
	{
		
				NhanVienPage nvp = nhanVienService.paginate(-1, "", -1, "", 1, 100);
		
				if(result.hasErrors()) {
					model.addAttribute("nhanviens", nvp.getNhanViens());
					model.addAttribute("luong", luong);
					model.addAttribute("page", "add");
					model.addAttribute("model", "luong");
					return "admin";
				}
		
		try {
			boolean save = luongService.insert(luong,ns);
			
			if(save) {
				
				return "redirect:/admin1/luong";
			}
			else {
				model.addAttribute("error", "Thêm mới không thành công");
				model.addAttribute("nhanviens", nvp.getNhanViens());
				model.addAttribute("page", "add");
				model.addAttribute("model", "luong");
				return "admin";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			System.out.println("Loi đay"+e.getMessage());
			model.addAttribute("nhanviens", nvp.getNhanViens());
			model.addAttribute("page", "add");
			model.addAttribute("model", "luong");
			return "admin";
			
		}
	
	}
	
	@RequestMapping(value ="luong/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id,Model model)
	{
		NhanVienPage nvp = nhanVienService.paginate(-1, "", -1, "", 1, 100);
		LuongForm lf = luongService.edit(Integer.parseInt(id));
		model.addAttribute("nhanviens", nvp.getNhanViens());
		model.addAttribute("luong", lf);
		model.addAttribute("id", id);
		model.addAttribute("page", "edit");
		model.addAttribute("model", "luong");
		return "admin";
		
		
		
	}

	@RequestMapping(value= "luong/update-post", method = RequestMethod.POST)
	public String update(Model model
			,@RequestParam("id") String id,
			@RequestParam("nhanSu") String nhanSu,
			@Valid @ModelAttribute("luong") LuongForm luong,
			BindingResult result,
				HttpServletRequest req)
	{
		
		//lấy thông tin vào update
		if(result.hasErrors()) {

			return "redirect:/admin1/luong/edit/" + id;

		}

		boolean save = luongService.update(Integer.parseInt(id), luong);
		
		if(save) {
			
			return "redirect:/admin1/cong";
		}
		else {
			model.addAttribute("error", "Cập nhật không thành công");
				
			return "redirect:/admin1/cong/edit/"+id;
		}	

	}

	@RequestMapping(value = "luong/delete/{id}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable("id") String id, Model model) {
		

		boolean delete = luongService.delete(Integer.parseInt(id));
		if(delete) {
			return "redirect:/admin1/luong";
		}
		model.addAttribute("msg", "xóa thất bại");
		return "redirect:/admin1/luong";
	}
}
