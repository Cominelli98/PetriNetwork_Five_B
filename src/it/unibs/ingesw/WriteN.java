package it.unibs.ingesw;


import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public final class WriteN {
	
	
	public static String SAVE_ERROR = "errore nel salvataggio";
	private static final String FILE_NET = "n_data.txt";
	private static final String FILE_PNET = "pn_data.txt";
	private static final String FILE_PNP = "pnp_data.txt";
	
	/**
	 * prende in ingresso un oggetto IDNameGiver e si occupa di salvarlo nel corretto file in base alla sua natura
	 * @param net
	 */
	public static void save(IDNameGiver net) {
		Gson gson = new Gson();
		String data;
		if (net.getClass() == Network.class) 
			data = FILE_NET;
		else if (net.getClass() == Petri_network.class)
			data = FILE_PNET;
		else if (net.getClass() == Priority_network.class)
			data = FILE_PNP;
		else 
			throw new IllegalArgumentException("tipo non valido");
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
	 * controlla che i file dove salvare le reti esistano, in caso negativo procede alla creazione 
	 */
	public static void fileCreation() {
		
		File f = new File("n_data.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File fp = new File("pn_data.txt");
		if (!fp.exists()) {
			try {
				fp.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File fpnp = new File("pnp_data.txt");
		if (!fpnp.exists()) {
			try {
				fpnp.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
	
