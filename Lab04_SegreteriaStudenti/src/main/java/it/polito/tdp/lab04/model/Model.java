package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO dao;
	
	

	public Model() {
		corsoDao=new CorsoDAO();
		dao=new StudenteDAO();
	}



	public List<Corso> getTuttiICorsi() {
		
		return corsoDao.getTuttiICorsi();
	}
	public Studente getIscrittoByMatricola(Integer matricola) {
		return dao.getIscrittoByMatricola(matricola);
	}
	
	

}
