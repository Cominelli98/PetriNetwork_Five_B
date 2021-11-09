package it.unibs.ingesw.ioGson;


import com.google.gson.Gson;

import it.unibs.ingesw.IDNameGiver;
import it.unibs.ingesw.Network;
import it.unibs.ingesw.Petri_network;
import it.unibs.ingesw.Priority_network;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public final class WriteN {
	
	
	public static String SAVE_ERROR = "errore nel salvataggio";
	private static final String FILES[] = {"n_data.txt",
											"pn_data.txt",
											"pnp_data.txt"};
	private static final Class CLASSES[] = {Network.class, Petri_network.class, Priority_network.class};
	
	/**
	 * prende in ingresso un oggetto IDNameGiver e si occupa di salvarlo nel corretto file in base alla sua natura
	 * @param net
	 */
	public static void save(IDNameGiver net) {	//metodo di selezione del file
		Gson gson = new Gson();
		String data = fileSelection(net.getClass());
		File file = new File(data);
		boolean exist = file.exists();
		try (FileWriter f = new FileWriter(data, exist)){
			f.append(gson.toJson(net, net.getClass())+"\n");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(SAVE_ERROR);
		}
	}
	
	/**
	 * metodo per la selezione del file corretto per il salvataggioi
	 * @param c classe del file da salvare
	 * @return indirizzo del file in cui il file va salvato
	 */
	private static String fileSelection(Class c) {
		for(int i = 0; i < FILES.length ; i++) {
			if (c == CLASSES[i]) 
				return FILES[i];
			}
		throw new IllegalArgumentException("tipo non valido per lettura file");
	}
	
	/**
	 * controlla che i file dove salvare le reti esistano, in caso negativo procede alla creazione 
	 */
	public static void fileCreation() {
		for(String s : FILES) {
			File f = new File(s);
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					System.out.println("errore creazione file: "+ s);
					e.printStackTrace();
				}
			}
		}
	}
}
	
