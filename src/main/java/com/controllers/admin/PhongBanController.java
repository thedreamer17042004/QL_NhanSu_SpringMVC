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
import com.entities.room.RoomPage;
import com.entities.room.RoomView;
import com.services.dao.IChucVu;
import com.services.dao.IRoom;

@Controller
@RequestMapping("admin1")
public class PhongBanController {
	@Autowired
	IRoom roomService;
	
	@RequestMapping(value = "room", method =  RequestMethod.GET)
	public String index(String searchname,Integer pageno,Model model) {
		
	
		pageno = pageno == null ? 1 : pageno;
		searchname = searchname == null ? "" : searchname;
	
		RoomPage chucVuPage = roomService.paginate(searchname, pageno, 3);
	
	
		model.addAttribute("rooms", chucVuPage.getRooms());
		model.addAttribute("totalpage", chucVuPage.getTotalPages());
		model.addAttribute("searchname", searchname);
		model.addAttribute("totalRecords", chucVuPage.getTotalRecord());
		model.addAttribute("currentpage", pageno);
		model.addAttribute("page", "room");
		model.addAttribute("model", "room");
		
		return "admin";
	}
		
	
	
	@RequestMapping(value= "room/add", method = RequestMethod.GET)
	public String add(Model model) 
	{

		
		model.addAttribute("page", "add");
		model.addAttribute("model", "room");
		return "admin";
		
	}
	
	@RequestMapping(value = "room/room-post", method = RequestMethod.POST)
	public String insert(Model model,HttpServletRequest req, 
			@RequestParam(name= "name") String name, 
			@RequestParam(name="phoneRoom") String phoneRoom,
			@RequestParam(name="address") String address)
	{
		
		if(name.equals("")) {
			model.addAttribute("error", "Name không được để trống");
			
			model.addAttribute("page", "add");
			model.addAttribute("model", "room");
			return "admin";
		}

		
		try {
			boolean save = roomService.insert(name, address, phoneRoom);
			
			if(save) {
				
				return "redirect:/admin1/room";
			}
			else {
				model.addAttribute("error", "Thêm mới không thành công");
				
				model.addAttribute("page", "add");
				model.addAttribute("model", "room");
				return "admin";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			model.addAttribute("page", "add");
			model.addAttribute("model", "room");
			return "admin";
			
		}
	
	}
	
	@RequestMapping(value ="room/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id,Model model)
	{
		
		RoomView rv = roomService.edit(id);

		System.out.println(rv.getRoomName());
	     //lấy category name from categorylanguage and picture from category

		model.addAttribute("room", rv);
		model.addAttribute("page", "edit");
		model.addAttribute("model", "room");
		return "admin";	
	}
	
	
	@RequestMapping(value= "room/update-post", method = RequestMethod.POST)
	public String update(Model model
			,@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam(name="phoneRoom") String phoneRoom,
			@RequestParam(name="address") String address,
			HttpServletRequest req)
	{
		
		//lấy thông tin vào update

		
		if(name.equals("")||name.equals(null)) {
			return "redirect:/admin1/room/edit/" + id;
		}
		
		boolean save = roomService.update(Integer.parseInt(id), name, address, phoneRoom);
		
		if(save) {
			
			return "redirect:/admin1/room";
		}
		else {
			System.out.println("Vào updated loii");
			model.addAttribute("error", "Cập nhật không thành công");
				
			return "redirect:/admin1/room/edit/"+id;
		}	

	}

	
	@RequestMapping(value = "room/delete/{id}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable("id") int id, Model model) {
		

		boolean delete = roomService.delete(id);
		if(delete) {
			return "redirect:/admin1/room";
		}
		model.addAttribute("msg", "xóa thất bại");
		return "redirect:/admin1/room";
	}
}
