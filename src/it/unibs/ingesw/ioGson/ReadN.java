package it.unibs.ingesw.ioGson;

import it.unibs.ingesw.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

	//TODO:	creazione classe di utilit� per la semplificazione dei getters

public final class ReadN {
	
	private static final int ID_INIZIALE = -1;
	private static final String FILES[] = {"n_data.txt",
											"pn_data.txt",
											"pnp_data.txt"};
	private static final Class CLASSES[] = {Network.class, Petri_network.class, Priority_network.class};

	/**
	 * prende in ingresso una stringa e la converte in un oggetto della classe c
	 * @param s string: stringa da convertire
	 * @param c Class: classe in cui viene convertita
	 * @return oggetto della classe c
	 */
	
	public static Object loadFromSource(Class c, String source ) throws FileNotFoundException, IOException {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(source));
			String toLoad = reader.readLine();
			return FromJson.convert(toLoad, c);
		} catch (FileNotFoundException e) {
			throw e;
		}
		catch (IOException e) {
			throw e;
		}
	}
	
	/**
	 * legge l'intero file e restituisce un arrayList in cui ogni stringa rappresenta una riga nel file, e quindi un oggetto 
	 * @param c classe, serve per scegliere quale file andare a leggere
	 * @return ArrayList<String>
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException: classe non valida
	 */
	public static ArrayList<String> readFile(Class c)throws FileNotFoundException, IllegalArgumentException {	//metodo di selelzione file tipo writeN
		String data = fileSelection(c);
		String line;
		ArrayList<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(data))){
			line = reader.readLine();
			while(line != null) {
				lines.add(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
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
}
