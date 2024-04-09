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
import com.entities.chucvu.ChucVuPage;
import com.entities.hocvan.HocVanPage;
import com.entities.hocvan.HocVanView;
import com.entities.nhanvien.NhanVienPage;
import com.entities.room.RoomPage;
import com.services.dao.ICong;
import com.services.dao.IHocVan;
import com.services.dao.INhanVien;

@Controller
@RequestMapping("admin1")
public class CongController {
	@Autowired
	ICong congService;
	
	
	@Autowired
	INhanVien nhanVienService;
	
	@RequestMapping(value = "cong", method =  RequestMethod.GET)
	public String index(String searchname,Integer pageno,Model model) {
		
	
		pageno = pageno == null ? 1 : pageno;
		searchname = searchname == null ? "" : searchname;
	
		CongPage congPage = congService.paginate(searchname, pageno, 3);
	
		model.addAttribute("congs", congPage.getCongs());
		model.addAttribute("totalpage", congPage.getTotalPages());
		model.addAttribute("searchname", searchname);
		model.addAttribute("totalRecords", congPage.getTotalRecord());
		model.addAttribute("currentpage", pageno);
		model.addAttribute("page", "index");
		model.addAttribute("model", "chamcong");
		
		return "admin";
	}
		
	
	
	@RequestMapping(value= "cong/add", method = RequestMethod.GET)
	public String add(Model model) 
	{
		NhanVienPage nvp = nhanVienService.paginate(-1, "", -1, "", 1, 100);
		
		model.addAttribute("nhanviens", nvp.getNhanViens());
		model.addAttribute("cong", new Cong());
		model.addAttribute("page", "add");
		model.addAttribute("model", "chamcong");
		return "admin";
		
	}
	
	@RequestMapping(value = "cong/cong-post", method = RequestMethod.POST)
	public String insert(Model model,HttpServletRequest req,
			@Valid @ModelAttribute("cong") Cong cong,
			BindingResult result,
			@RequestParam(name= "nhanSu") String nhanSu
			)
	{
		NhanVienPage nvp = nhanVienService.paginate(-1, "", -1, "", 1, 100);
		
				if(result.hasErrors()) {

					model.addAttribute("nhanviens", nvp.getNhanViens());
					model.addAttribute("cong", cong);
					model.addAttribute("page", "add");
					model.addAttribute("model", "chamcong");
					return "admin";
			
			
		}
		
		if(nhanSu.equals("")) {
			model.addAttribute("error", "Nhân sự không được để trống");
			
			model.addAttribute("page", "add");
			model.addAttribute("model", "chamcong");
			return "admin";
		}

		
		try {
			boolean save = congService.insert(cong, nhanSu);
			
			if(save) {
				
				return "redirect:/admin1/cong";
			}
			else {
				model.addAttribute("error", "Thêm mới không thành công");
				
				model.addAttribute("page", "add");
				model.addAttribute("model", "chamcong");
				return "admin";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			System.out.println("Loi đay"+e.getMessage());
			
			model.addAttribute("page", "add");
			model.addAttribute("model", "chamcong");
			return "admin";
			
		}
	
	}
	
	@RequestMapping(value ="cong/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id,Model model)
	{
		NhanVienPage nvp = nhanVienService.paginate(-1, "", -1, "", 1, 100);
		Cong hvv = congService.edit(Integer.parseInt(id));
		model.addAttribute("nhanviens", nvp.getNhanViens());
		model.addAttribute("cong", hvv);
		model.addAttribute("id", id);
		model.addAttribute("page", "edit");
		model.addAttribute("model", "chamcong");
		return "admin";
		
		
		
	}
	
	
	@RequestMapping(value= "cong/update-post", method = RequestMethod.POST)
	public String update(Model model
			,@RequestParam("id") String id,
			@Valid @ModelAttribute("cong") Cong cong,
			BindingResult result,
			String maNhanVien,
				HttpServletRequest req)
	{
		
		//lấy thông tin vào update
		if(result.hasErrors()) {

			return "redirect:/admin1/cong/edit/" + id;
	
	
		}

		boolean save = congService.update(Integer.parseInt(id), cong, Integer.parseInt(maNhanVien));
		
		if(save) {
			
			return "redirect:/admin1/cong";
		}
		else {
			model.addAttribute("error", "Cập nhật không thành công");
				
			return "redirect:/admin1/cong/edit/"+id;
		}	

	}

	
	@RequestMapping(value = "cong/delete/{id}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable("id") String id, Model model) {
		

		boolean delete = congService.delete(Integer.parseInt(id));
		if(delete) {
			return "redirect:/admin1/cong";
		}
		model.addAttribute("msg", "xóa thất bại");
		return "redirect:/admin1/cong";
	}
	
}
