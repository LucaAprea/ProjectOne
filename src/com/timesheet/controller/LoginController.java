package com.timesheet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.timesheet.dao.DaoDipendenteImpl;
import com.timesheet.dao.DaoDocumentoImpl;
import com.timesheet.dao.DaoNotificaImpl;
import com.timesheet.model.Dipendente;
import com.timesheet.model.Documento;
import com.timesheet.model.Notifica;

@Controller
public class LoginController {

	@Autowired
	DaoDipendenteImpl dao;

	@Autowired
	DaoDocumentoImpl daoDoc;
	
	@Autowired
	DaoNotificaImpl daoNot;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView verifyLogin(Dipendente dip, ModelMap model, HttpServletRequest req) {
		Dipendente d = dao.cercaDip(dip);
		System.out.println("login"+d);
		if (d == null) {
			model.addAttribute("errore", "Nome utente e/o password errati.");
			return new ModelAndView("error", "formDipendente", null);
		}

		if (d.getAdmin() == 0) {
			req.getSession().setAttribute("username", d.getUsername());
			req.getSession().setAttribute("IDDIPENDENTE", d.getId());
			model.addAttribute("listaDoc", daoDoc.findAllDocumento());
			return new ModelAndView("gestioneDoc", "formDoc", new Documento());
		}

//		req.getSession().setAttribute("username", d.getUsername());
//		req.getSession().setAttribute("IDDIPENDENTE", d.getId());
//		req.getSession().setAttribute("admin", "admin");
//		model.addAttribute("list", dao.findAllDipendente());
//		return new ModelAndView("dipendenteLista", "formDipendente", new Dipendente());
		req.getSession().setAttribute("username", d.getUsername());
		req.getSession().setAttribute("IDDIPENDENTE", d.getId());
		req.getSession().setAttribute("admin", "admin");
		model.addAttribute("listaNot", daoNot.findAllNotifica());
		return new ModelAndView("gestioneNot", "formNot", new Notifica());
	}

}
