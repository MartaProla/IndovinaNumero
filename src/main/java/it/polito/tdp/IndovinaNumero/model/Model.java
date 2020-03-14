package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	
	private final int NMAX=100; // VARIABILE DEL GIOCO
	private final int TMAX=8;	// VARIABILE DEL GIOCO
	private int segreto;		// VARIABILE DEL GIOCO
	private int numTentativi;	// VARIABILE DEL GIOCO
	private boolean inGioco;//VARIABILE CHE IMPATTA SUL GIOCO, MA ANCHE SULL'INTERFACCIA UTENTE
	
	private Set<Integer>tentativi;
	
	public Model() {
		this.inGioco=false;
		this.numTentativi=0;
	}
	
	public void nuovaPartita() {
		//premuto nuovaPartita
		this.segreto=(int)(Math.random()*NMAX) +1 ;
    	this.numTentativi=0;
    	this.inGioco=true;
    	this.tentativi=new HashSet<Integer>();
	}
	public int tentativo(int tentativo) {
		// controllo se la partita è in corso 
		if(!inGioco) {
			throw new IllegalStateException("La partita risulta essere terminata+\n");
		}
		//controllo input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero che non hai ancora utilizzato tra 1 e "+NMAX+"\n");
		}
		// tentativo risulta valido--> possiamo provarlo
		this.numTentativi++;
		this.tentativi.add(tentativo);
		
		if(this.numTentativi==this.TMAX) {
			this.inGioco=false;
			return 6;
		}
		if(tentativo ==this.segreto) {
			this.inGioco=false;
			return 0;
		}
		if(tentativo <this.segreto) {
			return -1;
		}else {
			//tentativo maggiore
			return 1;
		}
		
	}
	public boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo> this.NMAX)
			return false;
		else {
			if(this.tentativi.contains(tentativo))
				return false;
			else
				return true;
		}
			
		
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getNumTentativi() {
		return numTentativi;
	}
	
	
}
