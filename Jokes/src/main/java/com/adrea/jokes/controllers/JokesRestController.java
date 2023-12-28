package com.adrea.jokes.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adrea.jokes.models.entity.Categories;
import com.adrea.jokes.models.entity.Flags;
import com.adrea.jokes.models.entity.Jokes;
import com.adrea.jokes.models.entity.Language;
import com.adrea.jokes.models.entity.Types;
import com.adrea.jokes.models.services.ICategoriesService;
import com.adrea.jokes.models.services.IFlagsService;
import com.adrea.jokes.models.services.IJokesService;
import com.adrea.jokes.models.services.ILanguageService;
import com.adrea.jokes.models.services.ITypesService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class JokesRestController {
	
	@Autowired
	private IJokesService jokesService;
	
	@Autowired
	private IFlagsService flagService;
	
	@Autowired
	private ILanguageService langService;
	
	@Autowired
	private ICategoriesService catService;
	
	@Autowired
	private ITypesService typeService;
	
	@GetMapping("/jokes")
	public List<Jokes> index(){
		return jokesService.findAll();
	}
	
	@GetMapping("/flags")
	public List<Flags> indexF(){
		return flagService.findAll();
	}
	
	@GetMapping("/languages")
	public List<Language> indexL(){
		return langService.findAll();
	}
	@GetMapping("/categories")
	public List<Categories> indexC(){
		return catService.findAll();
	}
	@GetMapping("/types")
	public List<Types> indexT(){
		return typeService.findAll();
	}
	
	@GetMapping("/jokes/{id}")
	public ResponseEntity<?> show(@PathVariable int id){
		Jokes joke = null;
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			joke = jokesService.findById(id);
			
		}catch(DataAccessException e){
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(joke==null) {
			response.put("mensaje", "El joke con ID: ".concat(id+"").concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Jokes>(joke, HttpStatus.OK);
	}
	
	@GetMapping("/flags/{id}")
	public ResponseEntity<?> showF(@PathVariable int id){
		Flags flag = null;
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			flag = flagService.findById(id);
			
		}catch(DataAccessException e){
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(flag==null) {
			response.put("mensaje", "El flag con ID: ".concat(id+"").concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Flags>(flag, HttpStatus.OK);
	}
	@GetMapping("/languages/{id}")
	public ResponseEntity<?> showL(@PathVariable int id){
		Language lang = null;
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			lang = langService.findById(id);
			
		}catch(DataAccessException e){
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(lang == null) {
			response.put("mensaje", "El lenguaje con ID: ".concat(id+"").concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Language>(lang, HttpStatus.OK);
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<?> showC(@PathVariable int id){
		Categories cat = null;
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			cat = catService.findById(id);
			
		}catch(DataAccessException e){
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(cat == null) {
			response.put("mensaje", "La categoría con ID: ".concat(id+"").concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Categories>(cat, HttpStatus.OK);
	}
	
	@GetMapping("/types/{id}")
	public ResponseEntity<?> showT(@PathVariable int id){
		Types type = null;
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			type = typeService.findById(id);
			
		}catch(DataAccessException e){
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(type == null) {
			response.put("mensaje", "El tipo con ID: ".concat(id+"").concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Types>(type, HttpStatus.OK);
	}
	@PostMapping("/jokes")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Jokes joke, BindingResult result){
		Jokes newJoke = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			newJoke = jokesService.save(joke);	
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al insertar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el joke ha sido creado con éxito");
		response.put("joke",newJoke);
		return new ResponseEntity<Jokes>(joke, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/flags")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Flags flag, BindingResult result){
		Flags newFlag = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			newFlag = flagService.save(flag);	
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al insertar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el flag ha sido creado con éxito");
		response.put("flag",newFlag);
		return new ResponseEntity<Flags>(flag, HttpStatus.CREATED);	
	}
	@PostMapping("/languages")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Language lang, BindingResult result){
		Language newLang = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			newLang = langService.save(lang);	
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al insertar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el lenguaje ha sido creado con éxito");
		response.put("language",newLang);
		return new ResponseEntity<Language>(lang, HttpStatus.CREATED);
		
	}
	@PostMapping("/categories")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createC(@Valid @RequestBody Categories cat, BindingResult result){
		Categories newCat = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			newCat = catService.save(cat);	
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al insertar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La categoría ha sido creada con éxito");
		response.put("cat",newCat);
		return new ResponseEntity<Categories>(cat, HttpStatus.CREATED);
		
	}

	@PutMapping("/jokes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Jokes joke,BindingResult result, @PathVariable int id){
		Jokes cJoke = jokesService.findById(id);
		Jokes updateJoke = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(joke == null) {
			response.put("mensaje", "Error: El joke con ID: ".concat(id+"").concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try{
			cJoke.setCategories(joke.getCategories());
			cJoke.setLanguage(joke.getLanguage());
			cJoke.setTypes(joke.getTypes());
			cJoke.setText1(joke.getText1());
			cJoke.setText2(joke.getText2());
			cJoke.setFlagses(joke.getFlagses());
			
			updateJoke = jokesService.save(cJoke);
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar el joke en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el joke ha sido actualizado con éxito");
		response.put("joke",updateJoke);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	@PutMapping("/flags/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Flags flag,BindingResult result, @PathVariable int id){
		Flags cFlag = flagService.findById(id);
		Flags updateFlag = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(flag == null) {
			response.put("mensaje", "Error: El flag con ID: ".concat(id+"").concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try{
			cFlag.setFlag(flag.getFlag());
			
			updateFlag = flagService.save(cFlag);
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar el flag en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el marcador ha sido actualizado con éxito");
		response.put("flag",updateFlag);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/languages/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateL(@Valid @RequestBody Language lang,BindingResult result, @PathVariable int id){
		Language cLang = langService.findById(id);
		Language updateLang = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(lang == null) {
			response.put("mensaje", "Error: El lenguaje con ID: ".concat(id+"").concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try{
			cLang.setLanguage(lang.getLanguage());
			cLang.setCode(lang.getCode());
			updateLang = langService.save(cLang);
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar el lenguaje en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el lenguaje ha sido actualizado con éxito");
		response.put("lang",updateLang);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	@PutMapping("/categories/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateC(@Valid @RequestBody Categories cat,BindingResult result, @PathVariable int id){
		Categories cCat = catService.findById(id);
		Categories updateCat = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage()).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(cat == null) {
			response.put("mensaje", "Error: La categoría con ID: ".concat(id+"").concat(" no existe en la base de datos!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try{
			cCat.setCategory(cat.getCategory());
			updateCat = catService.save(cCat);
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar la categoría en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La categoría ha sido actualizada con éxito");
		response.put("categories",updateCat);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}


	@DeleteMapping("/jokes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable int id){
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			jokesService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el joke de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el joke eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/flags/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteF(@PathVariable int id){
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			flagService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el flag de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el flag eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/languages/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteL(@PathVariable int id){
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			langService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el lenguaje de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el lenguaje eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/categories/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteC(@PathVariable int id){
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			catService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la categoría de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "la categoría se ha eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
