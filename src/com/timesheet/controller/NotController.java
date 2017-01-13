package com.timesheet.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.timesheet.dao.DaoDipendenteImpl;

import com.timesheet.dao.DaoNotificaImpl;
import com.timesheet.model.Dipendente;

import com.timesheet.model.Notifica;

@org.springframework.stereotype.Controller
public class NotController {

	final static Logger logger = Logger.getLogger(NotController.class);

	@Autowired
	DaoDipendenteImpl daoDip;

	@Autowired
	Notifica notifica;

	@Autowired
	DaoNotificaImpl daoNot;

	// INSERT NOTIFICA(UPLOAD)
	@RequestMapping(value = "/inserisciNot", method = RequestMethod.GET)
	public ModelAndView notificaIns() {
		return new ModelAndView("notificaIns", "formNot", notifica);

	}

	@RequestMapping(value = "/inserisciNot", method = RequestMethod.POST)
	public String insNotifica(HttpSession session, @RequestParam("file") MultipartFile file,
			@ModelAttribute("formNot") Notifica notIns) {
		try {
			Dipendente dip_s = new Dipendente();
			dip_s.setId((int) session.getAttribute("IDDIPENDENTE"));

			java.util.Date utilDate = new java.util.Date();

			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			notIns.setData(sqlDate);
			File mioFile = new File(file.getOriginalFilename());
			notIns.setNome(mioFile.getName());
			notIns.setDipendente(dip_s);
			Blob blob = new javax.sql.rowset.serial.SerialBlob(file.getBytes());
			notIns.setDl(blob);

			System.out.println(notIns);

			daoNot.insertNotifica(notIns);

		} catch (HibernateException e) {
			System.out.println("Rilevato Errore: " + e.getMessage());
			e.printStackTrace();
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";

	}

	// DOWNLOAD NOTIFICA
	@RequestMapping(value = "/downloadNot/{idNotifica}", method = RequestMethod.GET)
	private void downloadFile(@PathVariable("idNotifica") int idNotifica, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			Notifica notifica = new Notifica();
			notifica.setIdNotifica(idNotifica);

			Notifica not = daoNot.findByIdNotifica(notifica);

			System.out.println(not.getNome() + " BLOB: " + not.getDl());
			ServletContext context = request.getServletContext();
			String mimeType = context.getMimeType(not.getNome());
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/octet-stream";
			}
			// log.warn("MIME type: " + mimeType);

			// set content attributes for the response
			response.setContentType(mimeType);
			response.setContentLength((int) not.getDl().length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", not.getNome());
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			BufferedInputStream is = new BufferedInputStream(not.getDl().getBinaryStream());
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[4096];
			int r = 0;
			while ((r = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, r);
			}

			is.close();
			if (outStream != null) {
				outStream.close();
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// DELETE NOTIFICA
	@RequestMapping(value = "/deleteNot/{idNotifica}", method = RequestMethod.GET)
	public ModelAndView deleteNotifica(ModelMap model, @PathVariable int idNotifica) {
		try {
			notifica.setIdNotifica(idNotifica);
			daoNot.deleteNotifica(notifica);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("listaDip", daoNot.findAllNotifica());
		return new ModelAndView("gestioneNot", "formNot", new Notifica());
		// return "/studenteList"; // KO perchè resta nel path della delete
	}

	// UPDATE NOTIFICA
	@RequestMapping(value = "/updateNot/{idNotifica}", method = RequestMethod.GET)
	public ModelAndView updateNotifica(@PathVariable int idNotifica) {
		try {

			notifica.setIdNotifica(idNotifica);
			notifica = daoNot.findByIdNotifica(notifica);
			System.out.println(notifica);
		} catch (HibernateException e) {

			e.printStackTrace();
		}
		return new ModelAndView("notificaUpd", "formNot", notifica);
	}

	@RequestMapping(value = "/updateNot", method = RequestMethod.POST)
	public ModelAndView updNotifica(ModelMap model, @ModelAttribute("formNot") Notifica notUpd) {
		try {

			daoNot.updateNotifica(notUpd);
		} catch (HibernateException e) {
			System.out.println("Rilevato Errore: " + e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("listaNot", daoNot.findAllNotifica());
		return new ModelAndView("gestioneNot", "formNot", new Notifica());

	}

}