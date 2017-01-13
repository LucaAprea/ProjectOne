package com.timesheet.test.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.timesheet.controller.Controller;
import com.timesheet.dao.DaoDipendenteImpl;
import com.timesheet.model.Dipendente;

import junit.framework.TestCase;

public class ControllerTest extends TestCase {

	Controller tester = new Controller();
	Dipendente dNull = null;
	Dipendente d = null;

	@Mock
	DaoDipendenteImpl dao;

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
	 }

	@Test
	public void testIndex() {
		fail("Not yet implemented");
	}

	@Test
	public void testDipendenteIns() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsDipendente() {
		fail("Not yet implemented");
	}

	@Test
	public void testListaDipendente() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteDipendente() {
		System.out.println(dao);
		doNothing().when(dao).deleteDipendente(Matchers.any(Dipendente.class));
		assertEquals("redirect:/load", tester.deleteDipendente(1));
		verify(dao, times(1)).deleteDipendente(Matchers.any(Dipendente.class));
	}

	@Test
	public void testUpdateDipendente() throws CloneNotSupportedException {

	}

	@Test
	public void testUpdStudente() {
		fail("Not yet implemented");
	}

	

}
