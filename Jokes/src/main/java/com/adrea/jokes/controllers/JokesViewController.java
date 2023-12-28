package com.adrea.jokes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adrea.jokes.models.entity.Jokes;
import com.adrea.jokes.models.services.CategoriesServiceImpl;
import com.adrea.jokes.models.services.FlagsServiceImpl;
import com.adrea.jokes.models.services.JokesServiceImpl;
import com.adrea.jokes.models.services.LanguageServiceImpl;
import com.adrea.jokes.models.services.TypesServiceImpl;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/jokes")
public class JokesViewController {
	@Autowired
	JokesServiceImpl jokesService = new JokesServiceImpl();
	
	@Autowired
	CategoriesServiceImpl catService = new CategoriesServiceImpl();
	
	@Autowired
	LanguageServiceImpl langService = new LanguageServiceImpl();
	
	@Autowired
	TypesServiceImpl typeService = new TypesServiceImpl();
	
	@Autowired
	FlagsServiceImpl flagService = new FlagsServiceImpl();
	
	@GetMapping("")
	public String indexJokes(Model model) {
		model.addAttribute("titulo", "Jokes");
		return "index";
	}
	
	@GetMapping("/listar")
	public String listarJokes(Model model) {
		model.addAttribute("titulo","Listar jokes");
		model.addAttribute("jokes", jokesService.findAll());
		return "jokes/listar";
	}
	@GetMapping("/anyadir")
	public String addJokes(Model model) {

		model.addAttribute("titulo","Añadir jokes");
		model.addAttribute("categories", catService.findAll());
		model.addAttribute("language",langService.findAll());
		model.addAttribute("types",typeService.findAll());
		model.addAttribute("text1","Text1:");
		model.addAttribute("text2","Text2:");
		model.addAttribute("flags",flagService.findAll());
		model.addAttribute("jokes", new Jokes());
		return "jokes/anyadir";
	}
	
	@RequestMapping("/create")
	public ModelAndView createJokes(@Valid Jokes joke, BindingResult result,Model mod) {
		ModelAndView model = new ModelAndView();
		boolean exists = false;
		model.addObject("jokes", joke);
			
		if(!result.hasErrors()) {

			mod.addAttribute("resultado", "Joke creado");
			jokesService.save(joke);
			model.setViewName("ready");
				
		}else {
			model.setViewName("jokes/anyadir");
		}
		mod.addAttribute("titulo","Añadir jokes");
		mod.addAttribute("categories", catService.findAll());
		mod.addAttribute("language",langService.findAll());
		mod.addAttribute("types",typeService.findAll());
		mod.addAttribute("text1","Text1:");
		mod.addAttribute("text2","Text2:");
		mod.addAttribute("flags",flagService.findAll());		
		return model;
	}
	@GetMapping("/edit/{id}")
	public String editJokes(Model model, @PathVariable("id") int id) {

		Jokes joke=jokesService.findById(id);
		model.addAttribute("titulo","Añadir jokes");
		model.addAttribute("categories", catService.findAll());
		model.addAttribute("language",langService.findAll());
		model.addAttribute("types",typeService.findAll());
		model.addAttribute("text1","Text1:");
		model.addAttribute("text2","Text2:");
		model.addAttribute("flags",flagService.findAll());
		model.addAttribute("joke", joke);
		return "jokes/editar";
	}
	
	@PostMapping("/editar")
	public ModelAndView updateJokes(@Valid Jokes joke, BindingResult result,Model mod) {
		ModelAndView model = new ModelAndView();
		boolean exists = false;
		model.addObject("jokes", joke);
		
		if(!result.hasErrors()) {
			
			model.setViewName("ready");
			mod.addAttribute("resultado", "Joke actualizado");
			jokesService.save(joke);
			
		}else {
			model.setViewName("jokes/edit/{joke.getId()}");
		}
		mod.addAttribute("titulo","Añadir jokes");
		mod.addAttribute("categories", catService.findAll());
		mod.addAttribute("language",langService.findAll());
		mod.addAttribute("types",typeService.findAll());
		mod.addAttribute("text1","Text1:");
		mod.addAttribute("text2","Text2:");
		mod.addAttribute("flags",flagService.findAll());
		mod.addAttribute("joke",joke);
		return model;
	}
	

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id) {
		
		jokesService.delete(id);
		return "redirect:/jokes/listar";
	}
	
}
