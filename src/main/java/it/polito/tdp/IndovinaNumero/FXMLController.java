package it.polito.tdp.IndovinaNumero;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController{
	
	private final int NMAX=100; // VARIABILE DEL GIOCO
	private final int TMAX=8;	// VARIABILE DEL GIOCO
	private int segreto;		// VARIABILE DEL GIOCO
	private int numTentativi;	// VARIABILE DEL GIOCO
	private boolean inGioco=false;//VARIABILE CHE IMPATTA SUL GIOCO, MA ANCHE SULL'INTERFACCIA UTENTE
	
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
    	// sto cliccando nuova partita 
    	//Genero un nuovo segreto
    	this.segreto=(int)(Math.random()*NMAX) +1 ;
    	this.numTentativi=0;
    	this.inGioco=true;
    	
    	layoutTentativo.setDisable(false);
    	txtRisultato.clear();
    	txtRimasti.setText(Integer.toString(TMAX));
    	

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
	    	
    	this.numTentativi++;
    	
    	if( ten==this.segreto) {
    		txtRisultato.appendText("Complimenti! Hai vinto! Hai utilizzato :"+this.numTentativi+" tentativi"+"\n");
    		layoutTentativo.setDisable(true);
    		this.inGioco=false;
    		return;
    	}
    	if(numTentativi==TMAX) {
    		txtRisultato.appendText("Ci dispiace, hai perso. Il numero segreto era: "+this.segreto+"\n");
    		layoutTentativo.setDisable(true);
    		this.inGioco=false;
    		return;
    	}
    	// informo se il tentativo è troppo alto o troppo basso
    	if(ten< this.segreto) {
    		txtRisultato.appendText("Ritenta, tentativo troppo basso \n");
    	}else {
    		if(ten>this.segreto) {
    			txtRisultato.appendText("Ritenta, tentativo troppo alto \n");
    		}
    	}
    	txtRimasti.setText(Integer.toString(TMAX-numTentativi));

    }

    @FXML
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
