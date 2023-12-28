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
import com.adrea.jokes.models.entity.Jokes;
import com.adrea.jokes.models.services.FlagsServiceImpl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern.Flag;

@Controller
@RequestMapping("/flags")
public class FlagsViewController {
	
	@Autowired
	FlagsServiceImpl flagService = new FlagsServiceImpl();
	
	@GetMapping("")
	public String indexJokes(Model model) {
		model.addAttribute("titulo", "Jokes");
		return "index";
	}
	
	@GetMapping("/listar")
	public String listarFlags(Model model) {
		model.addAttribute("titulo","Listar flags");
		model.addAttribute("flags", flagService.findAll());
		return "flags/listar";
	}
	@GetMapping("/anyadir")
	public String addFlags(Model model) {
		model.addAttribute("titulo","Añadir flags");
		model.addAttribute("flags", new Flags());
		return "flags/anyadir";
	}
	@GetMapping("/jokes/{id}")
	public String getFlag(Model model, @PathVariable("id") int id) {
		Flags flag = flagService.findById(id);
		model.addAttribute("titulo","Jokes asociados al flag '"+flag.getFlag()+"':");
		model.addAttribute("jokes", flag.getJokeses());
		return "flags/flags";
	}
	
	@RequestMapping("/create")
	public ModelAndView createFlags(@Valid Flags flag, BindingResult result,Model mod) {
		ModelAndView model = new ModelAndView();
		boolean exists = false;
		model.addObject("flags", flag);
		
		if(!result.hasErrors()) {
			mod.addAttribute("resultado", "Marcador creado");
			flagService.save(flag);
			model.setViewName("ready");
		}else {
			model.setViewName("flags/anyadir");
		}
		mod.addAttribute("titulo","Añadir flag");
		mod.addAttribute("flag",flag);		
		return model;
	}
	@GetMapping("/edit/{id}")
	public String editFlags(Model model, @PathVariable("id") int id) {
		Flags flag = flagService.findById(id);
		model.addAttribute("titulo","Editar marcador");
		model.addAttribute("flags", flag);
		return "flags/edit";
	}
	@RequestMapping("/editar")
	public ModelAndView updateFlags(@Valid Flags flag, BindingResult result,Model mod) {
		ModelAndView model = new ModelAndView();
		boolean exists = false;
		model.addObject("flags", flag);
		
		if(!result.hasErrors()) {
			mod.addAttribute("resultado", "Marcador actualizado");
			flagService.save(flag);
			model.setViewName("ready");
			
		}else {
			model.setViewName("flags/edit");
		}
		mod.addAttribute("titulo","editar flag");
		mod.addAttribute("flag",flag);		
		return model;
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes redirAttrs) {
		Flags flag = flagService.findById(id);
		
		if(flag.getJokeses().size() > 0) {
			redirAttrs.addFlashAttribute("message", "No se puede eliminar el registro.");
		}else {
			flagService.delete(id);
		}

		return "redirect:/flags/listar";
	}

}
