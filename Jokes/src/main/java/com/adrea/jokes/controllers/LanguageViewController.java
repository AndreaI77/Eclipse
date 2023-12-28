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

import com.adrea.jokes.models.entity.Flags;
import com.adrea.jokes.models.entity.Language;
import com.adrea.jokes.models.services.FlagsServiceImpl;
import com.adrea.jokes.models.services.LanguageServiceImpl;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/language")
public class LanguageViewController {
	@Autowired
	LanguageServiceImpl langService = new LanguageServiceImpl();
	
	@GetMapping("")
	public String indexJokes(Model model) {
		model.addAttribute("titulo", "Jokes");
		return "index";
	}
	
	@GetMapping("/listar")
	public String listarLang(Model model) {
		model.addAttribute("titulo","Listar idiomas");
		model.addAttribute("languages", langService.findAll());
		return "languages/listar";
	}
	@GetMapping("/anyadir")
	public String addLanguages(Model model) {
		model.addAttribute("titulo","Añadir idiomas");
		model.addAttribute("lang", new Language());
		return "languages/anyadir";
	}
	
	@RequestMapping("/create")
	public ModelAndView createLanguage(@Valid Language lang, BindingResult result,Model mod) {
		ModelAndView model = new ModelAndView();
		boolean exists = false;
		model.addObject("languages", lang);
//		
//		List<Language>lista = langService.findAll();
//		lista.sort((c1,c2) -> c1.getId() - c2.getId());
//		int lastId= lista.get(lista.size()-1).getId();
//		lang.setId(lastId+1);
		if(!result.hasErrors()) {
//			for(Language c: langService.findAll()) {
//				if(c.getId()== lang.getId()) {
//					exists=true;
//					break;
//				}
//			}
			model.setViewName("ready");
//			if(!exists) {
				mod.addAttribute("resultado", "Idioma creado");
				langService.save(lang);
//			}else {
//				mod.addAttribute("resultado", "El id ya existe");
//			}
		}else {
			model.setViewName("languages/anyadir");
		}
		mod.addAttribute("titulo","Añadir idioma");
		mod.addAttribute("lang",lang);		
		return model;
	}
	
	@GetMapping("/edit/{id}")
	public String editLanguage(Model model, @PathVariable("id") int id) {
		Language lang = langService.findById(id);
		model.addAttribute("titulo","Editar idioma");
		model.addAttribute("lang", lang);
		return "languages/editar";
	}
	
	@RequestMapping("/editar")
	public ModelAndView updateFlags(@Valid Language lang, BindingResult result,Model mod) {
		ModelAndView model = new ModelAndView();
		boolean exists = false;
		model.addObject("language", lang);
		
		if(!result.hasErrors()) {
			mod.addAttribute("resultado", "Idioma actualizado");
			langService.save(lang);
			model.setViewName("ready");
			
		}else {
			model.setViewName("languages/editar");
		}
		mod.addAttribute("titulo","Editar idioma");
		mod.addAttribute("lang",lang);		
		return model;
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes redirAttrs) {
		Language lang = langService.findById(id);
		
		if(lang.getJokeses().size() > 0) {
			redirAttrs.addFlashAttribute("message", "No se puede eliminar el registro.");
		}else {
			langService.delete(id);
		}

		return "redirect:/language/listar";
	}

}
