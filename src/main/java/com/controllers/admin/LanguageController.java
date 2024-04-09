package com.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.language.Language;
import com.entities.language.LanguagePage;
import com.services.dao.ILanguage;

@Controller
@RequestMapping("admin1")
public class LanguageController {
	
	@Autowired
	ILanguage languageService;
	

	@RequestMapping(value = "language", method =  RequestMethod.GET)
	public String index(String searchname,Integer pageno,Model model) {
		
		
		pageno = pageno == null ? 1 : pageno;
		searchname = searchname == null ? "" : searchname;
	
		LanguagePage lgPage = languageService.paginate(searchname, pageno, 1);
	
	
		model.addAttribute("languages", lgPage.getLanguages());
		model.addAttribute("totalpage", lgPage.getTotalPages());
		model.addAttribute("searchname", searchname);
		model.addAttribute("totalRecords", lgPage.getTotalRecord());
		model.addAttribute("currentpage", pageno);
		model.addAttribute("page", "index");
		model.addAttribute("model", "language");
		
		return "admin";
	}
		
	@RequestMapping(value ="language/view/{id}", method = RequestMethod.GET)
	public String viewCategory(@PathVariable("id") int id, Model model) {
		
		Language brd = languageService.edit(id);

	     //lấy category name from categorylanguage and picture from category

		model.addAttribute("language", brd);
		model.addAttribute("page", "view");
		model.addAttribute("model", "language");
		return "admin";
		
	}
}
