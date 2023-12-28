package com.adrea.jokes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adrea.jokes.models.entity.Categories;
import com.adrea.jokes.models.entity.Flags;
import com.adrea.jokes.models.services.CategoriesServiceImpl;
import com.adrea.jokes.models.services.FlagsServiceImpl;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoriesViewController {
	@Autowired
	CategoriesServiceImpl catService = new CategoriesServiceImpl();
	
	@GetMapping("")
	public String indexCat(Model model) {
		model.addAttribute("titulo", "Jokes");
		return "index";
	}
	
	@GetMapping("/listar")
	public String listarCategories(Model model) {
		model.addAttribute("titulo","Listar categoría");
		model.addAttribute("categories", catService.findAll());
		return "categories/listar";
	}
	@GetMapping("/anyadir")
	public String addCat(Model model) {
		model.addAttribute("titulo","Añadir categoría");
		model.addAttribute("categories", new Categories());
		return "categories/anyadir";
	}
	
	@RequestMapping("/create")
	public ModelAndView createCategories(@Valid Categories cat, BindingResult result,Model mod) {
		ModelAndView model = new ModelAndView();
		boolean exists = false;
		model.addObject("cat", cat);
		
		List<Categories>lista = catService.findAll();
		lista.sort((c1,c2) -> c1.getId() - c2.getId());
		int lastId= lista.get(lista.size()-1).getId();
		cat.setId(lastId+1);
		if(!result.hasErrors()) {
			
			mod.addAttribute("resultado", "Categoría creada");
			catService.save(cat);
			model.setViewName("ready");	
		}else {
			model.setViewName("categories/anyadir");
		}
		mod.addAttribute("categories",cat);		
		return model;
	}
	@GetMapping("/edit/{id}")
	public String editCat(Model model, @PathVariable("id") int id) {
		Categories cat = catService.findById(id);
		model.addAttribute("titulo","Editar categoría");
		model.addAttribute("categories", cat);
		return "categories/editar";
	}
	@RequestMapping("/editar")
	public ModelAndView updateCat(@Valid Categories cat, BindingResult result,Model mod) {
		ModelAndView model = new ModelAndView();
		boolean exists = false;
		model.addObject("categories", cat);
		
		if(!result.hasErrors()) {
			mod.addAttribute("resultado", "Categoría actualizada");
			catService.save(cat);
			model.setViewName("ready");
			
		}else {
			model.setViewName("categories/editar");
		}
		mod.addAttribute("titulo","Editar categoría");
		mod.addAttribute("categories",cat);		
		return model;
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes redirAttrs) {
		Categories cat = catService.findById(id);
		
		if(cat.getJokeses().size() > 0) {
			redirAttrs.addFlashAttribute("message", "No se puede eliminar el registro.");
		}else {
			catService.delete(id);
		}

		return "redirect:/categories/listar";
	}


}
