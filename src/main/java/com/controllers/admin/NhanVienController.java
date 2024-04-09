package com.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.entities.chucvu.ChucVu;
import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.entities.hocvan.HocVanPage;
import com.entities.nhanvien.NhanVien;
import com.entities.nhanvien.NhanVienForm;
import com.entities.nhanvien.NhanVienPage;
import com.entities.room.RoomPage;
import com.entities.user.Account;
import com.entities.user.AccountDetails;
import com.entities.user.AccountPage;
import com.entities.user.AccountRole;
import com.entities.user.Role;
import com.services.dao.IAccount;
import com.services.dao.IChucVu;
import com.services.dao.IHocVan;
import com.services.dao.INhanVien;
import com.services.dao.IRole;
import com.services.dao.IRoom;
import com.validation.AddUserValidation;

@Controller
@RequestMapping("admin1")
public class NhanVienController {
	
	@Autowired
	INhanVien nhanVienService;
	
	@Autowired
	IChucVu chucVuService;
	
	@Autowired
	IHocVan hocVanService;
	
	@Autowired
	IRoom roomService;

	@RequestMapping(value= {"nhansu"})
	public String user(Integer maCapBac,
			String maPhong, 
			Integer trinhDoHocVan,
			String searchname,
			Integer pageno,
			Model model) 
	{
			
		maCapBac  = maCapBac == null?-1: maCapBac;
		trinhDoHocVan = trinhDoHocVan ==null ? -1: trinhDoHocVan;
		maPhong =  maPhong == null ?"": maPhong;
		pageno = pageno == null ? 1 : pageno;
		searchname = searchname == null ? "" : searchname;

		NhanVienPage nhanVienPage = nhanVienService.paginate(maCapBac,maPhong,trinhDoHocVan,searchname, pageno, 10);
		
		
		ChucVuPage chucvus = chucVuService.paginate("", 1, 100);
		HocVanPage hocvans = hocVanService.paginate("", 1, 100);
		RoomPage rooms = roomService.paginate("", 1, 100);
		
		model.addAttribute("chucVus", chucvus.getChucvus());
		model.addAttribute("hocVans", hocvans.getHocvans());
		model.addAttribute("rooms", rooms.getRooms());
		
		
		model.addAttribute("nhansus", nhanVienPage.getNhanViens());
		
		model.addAttribute("totalpage", nhanVienPage.getTotalPages());
		model.addAttribute("searchname", searchname);
		model.addAttribute("chucv", maCapBac);
		model.addAttribute("hocv", trinhDoHocVan);
		model.addAttribute("roomv", maPhong);
		
		model.addAttribute("totalRecords", nhanVienPage.getTotalRecord());
		model.addAttribute("currentpage", pageno);
		model.addAttribute("page", "index");
		model.addAttribute("model", "nhanvien");
		return "admin";
	}
	
	
	
	@RequestMapping(value= "nhansu/add", method = RequestMethod.GET)
	public String add(Model model) 
	{
		ChucVuPage chucvus = chucVuService.paginate("", 1, 100);
		HocVanPage hocvans = hocVanService.paginate("", 1, 100);
		RoomPage rooms = roomService.paginate("", 1, 100);
		
		model.addAttribute("nhanVienForm", new NhanVienForm());
		model.addAttribute("page", "add");
		model.addAttribute("model", "nhanvien");
		model.addAttribute("chucVus", chucvus.getChucvus());
		model.addAttribute("hocVans", hocvans.getHocvans());
		model.addAttribute("rooms", rooms.getRooms());
		
		return "admin";
		
	}
	
	@RequestMapping(value = "nhansu/nhansu-post", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("nhanVienForm")  NhanVienForm nvForm,BindingResult errors, Model model
			,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest req
			)
	{


		if(errors.hasErrors()) {
			
			ChucVuPage chucvus = chucVuService.paginate("", 1, 100);
			HocVanPage hocvans = hocVanService.paginate("", 1, 100);
			RoomPage rooms = roomService.paginate("", 1, 100);
			
			model.addAttribute("nhanVienForm", nvForm);
			model.addAttribute("page", "add");
			model.addAttribute("model", "nhanvien");
			model.addAttribute("chucVus", chucvus.getChucvus());
			model.addAttribute("hocVans", hocvans.getHocvans());
			model.addAttribute("rooms", rooms.getRooms());
			
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
		
		try {
			nvForm.setHinhAnh("images/"+file.getOriginalFilename());
		
			boolean save = nhanVienService.insert(nvForm);
			
			if(save) {
				
				return "redirect:/admin1/nhansu";
			}
			else {
				model.addAttribute("error", "Thêm mới không thành công");
				ChucVuPage chucvus = chucVuService.paginate("", 1, 100);
				HocVanPage hocvans = hocVanService.paginate("", 1, 100);
				RoomPage rooms = roomService.paginate("", 1, 100);
				
				model.addAttribute("nhanVienForm", nvForm);
				model.addAttribute("page", "add");
				model.addAttribute("model", "nhanvien");
				model.addAttribute("chucVus", chucvus.getChucvus());
				model.addAttribute("hocVans", hocvans.getHocvans());
				model.addAttribute("rooms", rooms.getRooms());
				
				return "admin";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			ChucVuPage chucvus = chucVuService.paginate("", 1, 100);
			HocVanPage hocvans = hocVanService.paginate("", 1, 100);
			RoomPage rooms = roomService.paginate("", 1, 100);
			
			model.addAttribute("nhanVienForm", nvForm);
			model.addAttribute("page", "add");
			model.addAttribute("model", "nhanvien");
			model.addAttribute("chucVus", chucvus.getChucvus());
			model.addAttribute("hocVans", hocvans.getHocvans());
			model.addAttribute("rooms", rooms.getRooms());
			
			return "admin";
			
		}
	
		

	}else {
		try {
		

			nvForm.setHinhAnh(null);
			boolean save = nhanVienService.insert(nvForm);
			if(save) {
				
				return "redirect:/admin1/nhansu";
			}
			else {
				model.addAttribute("error", "Thêm mới không thành công");
				ChucVuPage chucvus = chucVuService.paginate("", 1, 100);
				HocVanPage hocvans = hocVanService.paginate("", 1, 100);
				RoomPage rooms = roomService.paginate("", 1, 100);
				
				model.addAttribute("nhanVienForm", nvForm);
				model.addAttribute("page", "add");
				model.addAttribute("model", "nhanvien");
				model.addAttribute("chucVus", chucvus.getChucvus());
				model.addAttribute("hocVans", hocvans.getHocvans());
				model.addAttribute("rooms", rooms.getRooms());
				
				return "admin";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			ChucVuPage chucvus = chucVuService.paginate("", 1, 100);
			HocVanPage hocvans = hocVanService.paginate("", 1, 100);
			RoomPage rooms = roomService.paginate("", 1, 100);
			
			model.addAttribute("nhanVienForm", nvForm);
			model.addAttribute("page", "add");
			model.addAttribute("model", "nhanvien");
			model.addAttribute("chucVus", chucvus.getChucvus());
			model.addAttribute("hocVans", hocvans.getHocvans());
			model.addAttribute("rooms", rooms.getRooms());
			
			return "admin";
			
		}
		
		
		
	}		
	
	}

	
	@RequestMapping(value ="nhansu/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id,Model model)
	{	
		ChucVuPage chucvus = chucVuService.paginate("", 1, 100);
		HocVanPage hocvans = hocVanService.paginate("", 1, 100);
		RoomPage rooms = roomService.paginate("", 1, 100);

		NhanVien acc = nhanVienService.getById(Integer.parseInt(id));

	
		NhanVienForm nv = new NhanVienForm();
		nv.setMaNhanSu(acc.getMaNhanSu());
		nv.setTen(acc.getTen());
		nv.setTuoi(acc.getTuoi());
		nv.setGioiTinh(acc.getGioiTinh());
		nv.setSdt(acc.getSdt());
		nv.setHinhAnh(acc.getHinhAnh());
		nv.setQueQuan(acc.getQueQuan());
		nv.setNgaySinh(acc.getNgaySinh());
		nv.setMachucVu(acc.getChucVu().getMaChucVuNV());
		nv.setMaHocVan(String.valueOf(acc.getHocVan().getMaTrinhDo()));
		nv.setRoomId(String.valueOf(acc.getRoom().getId()));
		
		model.addAttribute("nhanVienForm", nv);
		model.addAttribute("page", "edit");
		model.addAttribute("model", "nhanvien");
		model.addAttribute("chucVus", chucvus.getChucvus());
		model.addAttribute("hocVans", hocvans.getHocvans());
		model.addAttribute("rooms", rooms.getRooms());
		
		return "admin";

	}
	
	
	@RequestMapping(value= "nhansu/update-post", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("nhanVienForm") NhanVienForm account,
			BindingResult errors,
			Model model
			,@RequestParam("file") MultipartFile file,
			@RequestParam("id") String id,
			@RequestParam("oldPicture") String oldPicture,
			HttpServletRequest req
			)
	{
		
		//lấy thông tin vào update
		if(errors.hasErrors()) {
			return "redirect:/admin1/nhansu/edit/"+id;
		}

		if(!file.getOriginalFilename().equals("")) {
			String uploadRootPath = req.getServletContext().getRealPath("resources/images");
			File destination = new File(uploadRootPath+"/"+file.getOriginalFilename());

			try {
				file.transferTo(destination);

			} catch (IllegalStateException | IOException e) {

			e.printStackTrace();

			}
			account.setHinhAnh("images/"+file.getOriginalFilename());
			boolean save = nhanVienService.update(account,Integer.parseInt(id));
			
			if(save) {
				
				return "redirect:/admin1/nhansu";
			}
			else {
				model.addAttribute("error", "Cập nhật không thành công");
					
				return "redirect:/admin1/nhansu/edit/"+id;
			}
		
			

		}else {

			account.setHinhAnh(oldPicture);
			boolean save = nhanVienService.update(account,Integer.parseInt(id));
			
			if(save) {
				
				return "redirect:/admin1/nhansu";
			}
			else {
				model.addAttribute("error", "Cập nhật không thành công");
				
				return "redirect:/admin1/nhansu/edit/"+id;
			}
		
		}		
		
		
		
	}
	

	
	@RequestMapping(value = "nhansu/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id, Model model) {
		
		
		boolean delete = nhanVienService.delete(Integer.parseInt(id));
		if(delete) {
			return "redirect:/admin1/nhansu";
		}
		model.addAttribute("msg", "xóa thất bại");
		return "redirect:/admin1/nhansu";
	}

	
}
