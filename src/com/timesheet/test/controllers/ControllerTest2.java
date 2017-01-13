package com.timesheet.test.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.timesheet.controller.Controller;
import com.timesheet.dao.DaoDipendenteImpl;
import com.timesheet.model.Dipendente;

public class ControllerTest2 {

	Controller tester = new Controller();
	Dipendente dNull = null;
	Dipendente d = null;

	@Mock
	DaoDipendenteImpl dao;
	
	@Mock
	Dipendente dip;

	@Before
	 public void setup() {
	   MockitoAnnotations.initMocks(this);
	    d = new Dipendente();
	    d.setId(1);
	    d.setNome("Mario");
	    d.setCognome("Rossi");
	    d.setUsername("mario.rossi");
	    d.setPassword("pippo");
	    d.setCf("MRR2938129387");
	    d.setAdmin(0);
	    tester.setDao(dao);
	    tester.setDip(dip);
	 }


	
	//TESTS INSERT DIPENDENTE

	@Test
	public void testInserisciDipendenteValorizzato() {
		assertEquals("index", tester.insDipendente(d));
		verify(dao, times(1)).insertDipendente(d);
	}

	@Test(expected = NullPointerException.class)
	public void inserisciDipNullLanciaNullPointerException() {
		doThrow(new NullPointerException()).when(dao).insertDipendente(dNull);
		tester.insDipendente(dNull);
	}

		//TESTS LISTA DIPENDENTI
	
	@Test
	public void listaDipNonPuoEssereNullSeDipSuDB() {
		List<Dipendente> dips = new ArrayList<>();
		dips.add(new Dipendente());
		when(dao.findAllDipendente()).thenReturn(dips);

		ModelAndView mav = tester.listaDipendente();

		assertNotNull(mav.getModel().get("list"));
		verify(dao, times(1)).findAllDipendente();
	}

	
	//TESTS DELETE DIPENDENTE
	@Test
	public void testDeleteDipendente() {
		System.out.println(dao);
		assertEquals("redirect:/dipendenteLista", tester.deleteDipendente(1));
		verify(dao, times(1)).deleteDipendente(Matchers.any(Dipendente.class));
	}
	
	@Test
	public void eliminaDipendenteConIdScorrettoTerminaConSuccesso() {
		doThrow(new HibernateException("Failed!")).when(dao).deleteDipendente(d);
		//anche se lancia un Hibernate exception ritorna alla pagina "dipendenteLista"
		assertEquals("redirect:/dipendenteLista", tester.deleteDipendente(1));
		verify(dao, times(1)).deleteDipendente(Matchers.any(Dipendente.class));
	}
	
	//TESTS UPDATE DIPENDENTE

	@Test
	public void modificaDipendenteConIdInesistenteTerminaConSuccesso() {
		when (dao.findByIdDipendente(Matchers.any(Dipendente.class))).thenReturn(null);
		ModelAndView mav = new ModelAndView("redirect:/dipendenteLista");
		assertEquals(mav.getViewName(), tester.updDipendente(dNull));
	}

	@Test
	 public void testUpdateDipendente() throws CloneNotSupportedException{
	  
	    Dipendente dipClone = (Dipendente) d.clone();
	    when(dao.findByIdDipendente(Matchers.any(Dipendente.class))).thenReturn(dipClone);
	    ModelAndView mav = new ModelAndView("dipendenteUpd");
	    assertEquals(mav.getViewName(), tester.updateDipendente(1).getViewName());
	    verify(dao, times(1)).findByIdDipendente(Matchers.any(Dipendente.class));

	     
	 }
	
	

}
