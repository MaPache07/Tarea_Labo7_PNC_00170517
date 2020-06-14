package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.service.EstudianteService;

@Controller
public class MainController {
	
	@Autowired
	private EstudianteService es;
	
	@RequestMapping("/inicio")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("estudiante", new Estudiante());
		return mav;
	}
	
	@RequestMapping("/insert")
	public ModelAndView insertEstudiante(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		if(!result.hasErrors()) {
			mav.addObject("estudiante", new Estudiante());
			try {
				es.save(estudiante);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mav;
	}
	
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try {
			estudiantes = es.findAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("listado");
		mav.addObject("estudiantes", estudiantes);
		return mav;
	}
	
	@PostMapping(value = "/codEstudiante", params="action=Borrar")
	public ModelAndView eliminar(@RequestParam(value="cusuario") int id) {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try {
			es.delete(id);
			estudiantes = es.findAll();
		} catch(Exception e){
			e.printStackTrace();
		}
		mav.setViewName("listado");
		mav.addObject("estudiantes", estudiantes);
		return mav;
	}
	
	@PostMapping(value = "/codEstudiante", params="action=Editar")
	public ModelAndView editar(@RequestParam(value="cusuario") int id) {
		ModelAndView mav = new ModelAndView();
		Estudiante editEs = new Estudiante();
		try {
			editEs = es.findOne(id);
		} catch(Exception e){
			e.printStackTrace();
		}
		mav.setViewName("update");
		mav.addObject("estudiante", editEs);
		return mav;
	}
	
	@RequestMapping("/edit")
	public ModelAndView listado(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(!result.hasErrors()) {
			mav.setViewName("index");
			mav.addObject("estudiante", new Estudiante());
			try {
				es.save(estudiante);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			mav.setViewName("update");
		}
		return mav;
	}
}
