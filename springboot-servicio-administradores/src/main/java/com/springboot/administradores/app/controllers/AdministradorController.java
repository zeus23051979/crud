package com.springboot.administradores.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.administradores.app.entity.Administrador;
import com.springboot.administradores.app.service.IAdministradorService;

@Controller
@RequestMapping("/administrador")
@SessionAttributes("administrador")
public class AdministradorController {

	
	@Autowired
	@Qualifier("administradorService")
	private IAdministradorService administradorService;

	@GetMapping("/listar")
	public String listar(Model model) {		

		model.addAttribute("listAdmin", administradorService.findAll());

		return "administradores/listar";
	}

	@GetMapping("/form")
	public String crear(Model model) {
		model.addAttribute("titulo", "Alta de administrador");
		model.addAttribute("administrador", new Administrador());

		return "administradores/form";
	}

	@PostMapping("/form")
	public String guardar(@Valid Administrador administrador, BindingResult resul, Model model,
			RedirectAttributes flash, SessionStatus status) {
		Administrador admin = null;
		if (resul.hasErrors()) {
			return "administradores/form";
		}

		admin = administradorService.save(administrador);
		status.isComplete();

		if (admin != null) {

			flash.addFlashAttribute("success", "Administrador dado de alta con exito.");
			return "redirect:/administrador/listar";
		}

		flash.addFlashAttribute("error", "Error: Intente dar de alta nuevamente.");
		return "redirect:/administrador/listar";
	}

	@GetMapping("/edit/{administradorId}")
	public String edit(@PathVariable(name = "administradorId") Long administradorId, RedirectAttributes flash,
			Model model) {
		Administrador admin = null;
		if (administradorId > 0) {
			admin = administradorService.findById(administradorId);
			if (admin != null) {
				model.addAttribute("titulo", "Editar administrador");
				model.addAttribute("administrador", admin);
				return "administradores/form";
			} else {
				flash.addFlashAttribute("error", "Error: El administrador no existe.");
				return "redirect:/administrador/listar";
			}
		}
		flash.addFlashAttribute("error", "Error: El Id es invalido.");
		return "redirect:/administrador/listar";

	}

	@GetMapping("/delete/{administradorId}")
	public String delete(@PathVariable(name = "administradorId") Long administradorId, RedirectAttributes flash,
			Model model) {
		Administrador admin = null;
		if (administradorId > 0) {
			admin = administradorService.findById(administradorId);

			if (admin != null) {
				administradorService.delete(admin);
				flash.addFlashAttribute("success", "Administrador eliminado con exito");
				return "redirect:/administrador/listar";
			} else {
				flash.addFlashAttribute("error", "El administrador no existe.");
				return "redirect:/administrador/listar";
			}
		}
		flash.addFlashAttribute("warning", "El Id no esta asociado.");
		return "redirect:/administrador/listar";
	}

}
