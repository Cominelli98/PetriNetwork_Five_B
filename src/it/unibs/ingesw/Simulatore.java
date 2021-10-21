package it.unibs.ingesw;

import java.util.ArrayList;

public class Simulatore {

	private Petri_network rete;
	
	public Simulatore (Petri_network rete) {
		this.rete = rete;
	}
	
	public Simulatore (Priority_network pnp) {
		this.rete = pnp;
	}
	
	/**
	 * Metodo fa visualizzare al fruitore lo step successivo di una marcatura di una rete di petri o di una rete di petri con priorità
	 */
	public void nextStep(){
		ArrayList<Petri_transition> transizioniAttivabili = topPriorityTransitions();
		int possibili = transizioniAttivabili.size();
		if (possibili == 0) 
			System.out.println("BLOCCO CRITICO");
		else if (possibili == 1) {
			stampAttivabili();
			System.out.println("C'è un'unica transizione attivabile, prossimo step:");
			modificaToken(transizioniAttivabili.get(0));
			Menu_Visua.printPetriNet(rete);
			}
		else {
			//stampa a video, richiede la scelta, esegue
			System.out.println("È possibile attivare le seguenti transizioni: \n Quale vuoi attivare?");
			stampAttivabili();
			int scelta = Utility.readLimitedInt(0, transizioniAttivabili.size() -1);
			modificaToken(transizioniAttivabili.get(scelta));
			Menu_Visua.printPetriNet(rete);
		}
	}
	
	/**
	 * Stampa tutte le transizioni attivabili in una data marcatura
	 */
	private void stampAttivabili() {
		ArrayList<Petri_transition> transizioni = topPriorityTransitions();
		System.out.println("Lista transizioni attivabili:");
		for (int i=0; i<transizioni.size(); i++) {
			System.out.println(i + ") " + transizioni.get(i).getName());
		}
	}
	
	/**
	 * Ritorna un arrayList contenente tutte le transizioni attivabili con priorità maggiore
	 * @return ArrayList<Petri_transition> risultato
	 */
	private ArrayList<Petri_transition> topPriorityTransitions(){
		ArrayList<Petri_transition> risultato = new ArrayList<>();
		int topP = -1;
		for (Petri_transition pt : transAttivabili()) {
			if(pt.getPriority() > topP) {
				topP = pt.getPriority();
				risultato.clear();
				risultato.add(pt);
			}
			else if(pt.getPriority() == topP)
				risultato.add(pt);
		}
		return risultato;
	}
	
	
	/**
	 * Ritorna un arrayList contenente tutte le transazioni attivabili 
	 * @return
	 */
	private ArrayList<Petri_transition> transAttivabili(){
		ArrayList<Petri_transition> risultato = new ArrayList<>();
		
		for (Petri_transition pt : rete.getTransitions()) {
			if(attivabile(pt))
				risultato.add(pt);
		}
		return risultato;
	}
	
	/**
	 * Valuta se una data transition è attivabile
	 * @param pt
	 * @return boolean
	 */
	private boolean attivabile (Petri_transition pt) {
		boolean exist = checkIfOneLinkExistWithTrans(pt);
		int x;
		//prima di tutto controlliamo se almeno un link ha come destinazione la transizione
		if(!exist)
			return false;
		for (int i = 0; i< rete.getLinks().size(); i++) {
			if (rete.getLinks().get(i).getDestination() == pt.getId()) {
				x = getIndexOfLocation(rete.getLinks().get(i).getOrigin());
				if(rete.getLocations().get(x).getValue() < pt.getValue())
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Controlla che la transizione in ingresso sia una destinazione in un link
	 * @param pt
	 * @return boolean
	 */
	private boolean checkIfOneLinkExistWithTrans(Petri_transition pt) {
		for(int i = 0; i< rete.getLinks().size(); i++) {
			if(rete.getLinks().get(i).getDestination() == pt.getId())
				return true;
		}
		return false;
	}

	private void modificaToken(Petri_transition pt) {
		rete.reduceToken(pt.getId(), pt.getValue());
		rete.addToken(pt.getId(), 1);
	}
	
	private int getIndexOfLocation(int toFind) {
		for (int i = 0; i < rete.getLocations().size(); i++) {
			if(rete.getLocations().get(i).getId() == toFind)
				return i;
		}
		return 0;
	}
}
