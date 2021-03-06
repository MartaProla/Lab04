/**
 /**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;	
	private List<Corso> corsi;
	private List<Studente>studenti;
		
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboCorso"
    private ComboBox<Corso> comboCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaNome"
    private Button btnCercaNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCerca"
    private Button btnCerca; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader
    @FXML
    private Button btnCercaCorrispondenza;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	
    	try {
    		Integer matricola= Integer.parseInt(txtMatricola.getText().trim());
    		Studente s= this.model.getIscrittoByMatricola(matricola);
    		if(s==null) {
    			txtResult.appendText("Inserire la matricola, grazie");
    			return;
    		}else {
    			this.corsi=new LinkedList<Corso>(this.model.getCorsiByMatricola(matricola));
    			for(int i=0;i<corsi.size();i++) {
    				Corso c= corsi.get(i);
    				txtResult.appendText(c.getCodins()+" "+c.getCrediti()+" "+c.getNome()+" "+ c.getPd()+"\n");
    			}
    		}
    		
    	}catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    	
    	
    	
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	
    	try {
    		Corso c=comboCorso.getValue();
    		
    		if(c==null) {
    			txtResult.appendText("Nome Selezionato Inesistente");
    			return;
    		}else {
    			studenti=new LinkedList<Studente>(this.model.getIscrittoAlCorso(c));
    			for(int i=0;i<studenti.size();i++) {
    				Studente s=studenti.get(i);
    				txtResult.appendText(s.getMatricola()+" "+s.getNome()+" "+s.getCognome()+" "+s.getCDS()+"\n");
    			}
    		}
    		
    	}catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    	
    	
    }

    @FXML
    void doCercaNome(ActionEvent event) {
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	
    	try {
    		int matricola=Integer.parseInt(txtMatricola.getText());
    		Studente s=this.model.getIscrittoByMatricola(matricola);
    		if(s==null) {
    			txtResult.appendText("Studente inesistente");
    			return;
    		}else {
    			txtNome.appendText(s.getNome());
    			txtCognome.appendText(s.getCognome());
    		}
    		
    	}catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    
		
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	comboCorso.getSelectionModel().clearSelection();
    }
    @FXML
    void doCerca(ActionEvent event) {
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	
    	try {
    		Corso c=comboCorso.getValue();
    		int matricola=Integer.parseInt(txtMatricola.getText());
    		Studente s=this.model.getIscrittoByMatricola(matricola);
    		if(c==null && s== null) {
    			txtResult.appendText("Nome Selezionato Inesistente");
    			return;
    		}else {
    			txtResult.appendText("Studente già iscritto a questo corso");
    			}
    		
    	}catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    	
    	
    }

    private void setComboItems() {
    	//ottieni tutti i corsi del model
    	corsi = model.getTuttiICorsi();
    	
    	//Aggiungi tutti i corsi alla ComboBox. 
    	Collections.sort(corsi); // per avere maggiore ordine li sorto alfabeticamente
    	comboCorso.getItems().addAll(corsi); // richiama il toString dell'oggetto Corso
    }
    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorrispondenza != null : "fx:id=\"btnCercaCorrispondenza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	setComboItems();
    }
    
}