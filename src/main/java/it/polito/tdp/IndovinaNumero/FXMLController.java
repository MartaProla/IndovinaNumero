package it.polito.tdp.IndovinaNumero;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.IndovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController{
	
	private Model model;
	
	
	// VARIABILI DELL'INTERFACCIA GRAFICA
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNuovaPartita;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox layoutTentativo;

    @FXML
    private TextField txtTentativi;

    @FXML
    private Button btnProva;

    @FXML
    private TextArea txtRisultato;
     // METODI CHE HANNO ISTRUZIONI A LIVELLO GRAFICO,
    //E ALTRE ISTRUZIONI SULLA LOGICA DEL GICOCO
    @FXML
    void doNuova(ActionEvent event) {
    	this.model.nuovaPartita();
    	//controllo della vista
    	layoutTentativo.setDisable(false);
    	txtRisultato.clear();
    	txtRimasti.setText(Integer.toString(model.getTMAX()));
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//leggo input utente 
    	String tentativo= txtTentativi.getText();
    	// controllo dei dati! 
    	int ten;
    	try {
    		ten=Integer.parseInt(tentativo);
    	}catch(NumberFormatException e) {
    		txtRisultato.appendText("Devo inserire un numero");
    		return;
    	}
    	int risultato=-1;
    	try {
    	risultato=this.model.tentativo(ten);
    	}catch (IllegalStateException se) {
    		txtRisultato.appendText(se.getMessage());
    		return;
    	}catch (InvalidParameterException pe) {
    		txtRisultato.appendText(pe.getMessage());
    		return;
    	}
    	
    	if(risultato==0) {
    		txtRisultato.appendText("Complimenti, hai vinto con "+model.getNumTentativi()+" tentativi\n");
    		
    	}else {
    		if(risultato ==-1) {
    			txtRisultato.appendText("Tentativo, troppo basso\n");
    		}else {
    			if(risultato ==1) {
    				txtRisultato.appendText("Tentativo, troppo alto\n");
    				}else {
    					if(risultato==6)
    						txtRisultato.appendText("Ci dispiace, hai perso, il numero segreto era:"+model.getSegreto());
    				}
    			}
    	}
    	txtRimasti.setText(Integer.toString(this.model.getTMAX()-this.model.getNumTentativi()));
    	
    }

    @FXML
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        //this.model=new Model(); è rischioso, abbiamo relazione tra modello e controller. 
    }
    public void setModel(Model m) {
    	this.model=m;
    }
}
