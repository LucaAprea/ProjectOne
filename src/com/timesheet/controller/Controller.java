package com.timesheet.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.timesheet.dao.DaoDipendenteImpl;
import com.timesheet.model.Dipendente;

@org.springframework.stereotype.Controller
public class Controller {
	private Logger log = Logger.getLogger(Controller.class);
	@Autowired
	DaoDipendenteImpl dao;

	@Autowired
	Dipendente dip;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Index() {
		return "login";
	}
	// INSERT DIPENDENTE

	@RequestMapping(value = "/dipendenteIns", method = RequestMethod.GET)
	public ModelAndView dipendenteIns() {
		return new ModelAndView("/dipendenteIns", "formDipendente", dip);
		// qui creo l'associazione tra il pojo (Dipendente.java) e il form nella
		// pagina dipendenteIns.jsp (che a sua volta è settato con
		// modelAttribute="formDipendente")
		// nota bene, se avessi scritto "command" (nome comando di default) al
		// posto di "formDipendente", non avrei avuto bisogno di settarlo
		// (modelAttribute="formDipendente" oppure(equivalente)
		// commandName="formDipedente")
	}

	@RequestMapping(value = "/insDipendente", method = RequestMethod.POST)
	public String insDipendente(@ModelAttribute("formDipendente") Dipendente dipIns) {
		try {

			dao.insertDipendente(dipIns);

		} catch (HibernateException e) {
			System.out.println("Rilevato Errore: " + e.getMessage());
			log.error(e.getStackTrace());
		}
		return "index";

	}

	// LISTA DIPENDENTI
	@RequestMapping(value = "/dipendenteLista", method = RequestMethod.GET)
	public ModelAndView listaDipendente() {
		List<Dipendente> lst = null;

		try {
			lst = dao.findAllDipendente();
		} catch (HibernateException e) {

			log.error(e.getStackTrace());
		}
		return new ModelAndView("dipendenteLista", "list", lst);
	}

	// UPDATE DIPENDENTE
	@RequestMapping(value = "/updDipendente/{id}", method = RequestMethod.GET)
	public ModelAndView updateDipendente(@PathVariable int id) {
		try {

			dip.setId(id);
			dip = dao.findByIdDipendente(dip);
			
		} catch (HibernateException e) {

			log.error(e.getStackTrace());
		}
		return new ModelAndView("dipendenteUpd", "formDipendente", dip);
	}

	@RequestMapping(value = "/updDipendente", method = RequestMethod.POST)
	public String updDipendente(@ModelAttribute("formDipendente") Dipendente dipUpd) {
		try {
			dao.updateDipendente(dipUpd);
		} catch (HibernateException e) {
			System.out.println("Rilevato Errore: " + e.getMessage());
			log.error(e.getStackTrace());
		}
		return "redirect:/dipendenteLista";

	}

	// DELETE STUDENTE
	@RequestMapping(value = "/deleteDipendente/{id}", method = RequestMethod.GET)
	public String deleteDipendente(@PathVariable int id) {
		try {
			dip.setId(id);
			dao.deleteDipendente(dip);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			log.error(e.getStackTrace());
		}
		return "redirect:/dipendenteLista";
		// return "/studenteList"; // KO perchè resta nel path della delete
	}

	public void setDao(DaoDipendenteImpl dao2) {
		this.dao = dao2;
	}

	public void setDip(Dipendente dip) {
		this.dip = dip;
	}
	
	
	

}