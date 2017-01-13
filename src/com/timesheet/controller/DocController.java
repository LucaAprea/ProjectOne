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

import com.timesheet.dao.DaoDocumentoImpl;
import com.timesheet.model.Dipendente;
import com.timesheet.model.Documento;

@org.springframework.stereotype.Controller
public class DocController {
	@Autowired
	DaoDocumentoImpl daoDoc;

	@Autowired
	Documento doc;

	

	// INSERT DOCUMENTO(UPLOAD)

	
			// qui creo l'associazione tra il pojo (Dipendente.java) e il form nella
			// pagina dipendenteIns.jsp (che a sua volta è settato con
			// modelAttribute="formDipendente")
			// nota bene, se avessi scritto "comand" (nome comando di default) al
			// posto di "formStudente", non avrei avuto bisogno di settarlo
			// (modelAttribute="formDipendente" oppure(equivalente)
			// commandName="formDipedente")
	@RequestMapping(value = "/insertDocumento", method = RequestMethod.GET)
	public ModelAndView documentoIns() {
		return new ModelAndView("insertDocumento", "formDoc", null);
		

	}

	@RequestMapping(value = "/insertDocumento", method = RequestMethod.POST)
	public ModelAndView insDocumento(ModelMap model,HttpSession session, @RequestParam("file") MultipartFile file,
			@ModelAttribute("formDoc") Documento docIns) {
		try {
			Dipendente dip_s = new Dipendente();
			dip_s.setId((int) session.getAttribute("IDDIPENDENTE"));

			java.util.Date utilDate = new java.util.Date();

			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			docIns.setData(sqlDate);
			File mioFile = new File(file.getOriginalFilename());
			docIns.setNome(mioFile.getName());
			docIns.setDipendente(dip_s);
			Blob blob = new javax.sql.rowset.serial.SerialBlob(file.getBytes());
			docIns.setDl(blob);

			System.out.println(docIns);

			daoDoc.insertDocumento(docIns);

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
		model.addAttribute("listaDoc", daoDoc.findAllDocumento());
	    return new ModelAndView("gestioneDoc", "formDoc", new Documento());

	}
	
	//DOWNLOAD DOCUMENTO

	@RequestMapping(value = "/downloadDoc/{id}", method = RequestMethod.GET)
	 private void downloadFile(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response)
	   throws Exception {

	  Documento documento = new Documento();
	  documento.setId(id);
	  
	  Documento doc = daoDoc.findByIdDocumento(documento);
	  
	  System.out.println(doc.getNome() + " BLOB: " + doc.getDl());
	  ServletContext context = request.getServletContext();
	  String mimeType = context.getMimeType(doc.getNome());
	  if (mimeType == null) {
	   // set to binary type if MIME mapping not found
	   mimeType = "application/octet-stream";
	  }
	  // log.warn("MIME type: " + mimeType);

	  // set content attributes for the response
	  response.setContentType(mimeType);
	  response.setContentLength((int) doc.getDl().length());

	  // set headers for the response
	  String headerKey = "Content-Disposition";
	  String headerValue = String.format("attachment; filename=\"%s\"", doc.getNome());
	  response.setHeader(headerKey, headerValue);

	  // get output stream of the response
	  BufferedInputStream is = new BufferedInputStream(doc.getDl().getBinaryStream());
	  OutputStream outStream = response.getOutputStream();

	  byte[] buffer = new byte[4096];
	  int r = 0;
	  while ((r = is.read(buffer)) != -1) {
	   outStream.write(buffer, 0, r);
	  }

	  is.close();
	  if (outStream != null)
	   outStream.close();
	 }
	
	
	// UPDATE DOCUMENTO
		@RequestMapping(value = "/updateDoc/{id}", method = RequestMethod.GET)
		public ModelAndView updateDocumento(@PathVariable int id) {
			try {

				doc.setId(id);
				doc = daoDoc.findByIdDocumento(doc);
				System.out.println(doc);
			} catch (HibernateException e) {

				e.printStackTrace();
			}
			return new ModelAndView("documentoUpd", "formDocumento", doc);
		}

		@RequestMapping(value = "/updateDoc", method = RequestMethod.POST)
		public ModelAndView updDocumento(ModelMap model,@ModelAttribute("formDocumento") Documento docUpd) {
			try {
				
				daoDoc.updateDocumento(docUpd);
			} catch (HibernateException e) {
				System.out.println("Rilevato Errore: " + e.getMessage());
				e.printStackTrace();
			}
			model.addAttribute("listaDoc", daoDoc.findAllDocumento());
		    return new ModelAndView("gestioneDoc", "formDoc", new Documento());

		}
		
		//DELETE DOCUMENTO
		
		 @RequestMapping(value = "/deleteDoc/{id}", method = RequestMethod.GET)
		 public ModelAndView deleteDipendente(ModelMap model, @PathVariable int id)
		 { 
		  try {
		   doc.setId(id);
		   daoDoc.deleteDocumento(doc);
		  } catch (HibernateException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  
		    model.addAttribute("listaDoc", daoDoc.findAllDocumento());
		    return new ModelAndView("gestioneDoc", "formDoc", new Documento());
		  //return "/studenteList"; // KO perchè resta nel path della delete
		 }

	

}
