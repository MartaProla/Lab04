package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
				return s;
			}else {
				conn.close();
				return null;
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
