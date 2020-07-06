package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;



public class StudenteDAO {
	public StudenteDAO() {
	}
	
	public Studente getIscrittoByMatricola(Integer matricola) {

		final String sql = "SELECT * " + 
				"FROM studente s " + 
				"WHERE s.matricola= ? ";
		Studente s=null;


		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				s=new Studente(matricola, rs.getString("nome"),rs.getString("cognome"),rs.getString("CDS"));
				conn.close();
				
			}else {
				conn.close();
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return s;
	}
	// data una matricola ottengo una lista di corsi
	public List<Corso> getCorsoByMatricola(Integer matricola) {

		final String sql = "SELECT c.codins,c.crediti, c.nome, c.pd "+
							"FROM corso c, iscrizione i, studente s " + 
							"WHERE c.codins= i.codins AND  s.matricola=i.matricola AND s.matricola= ? ";
		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,matricola);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String codins= rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodo = rs.getInt("pd");


				// Crea un nuovo JAVA Bean Corso
				// Aggiungi studente alla lista di studenti del corso 
				Corso c=new Corso(codins,nome,crediti,periodo);
				corsi.add(c);
			}
			conn.close();
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	
	}
	
	public boolean studenteRisultaIscritto(Corso c, Integer matricola) {

		final String sql = "SELECT * " + 
							"FROM iscrizione i, corso c " + 
							"WHERE i.codins=c.codins AND c.nome= ? AND i.matricola= ? ";
		boolean result=false;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,c.getNome());
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();
			
			
			if(rs.next()) {
				result =true;
				conn.close();
				
			}else {
				conn.close();
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return result;
	}

}
