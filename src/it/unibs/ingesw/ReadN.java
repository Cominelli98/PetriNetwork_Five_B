package it.unibs.ingesw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

public final class ReadN {
	
	private static final int ID_INIZIALE = -1;
	private static final String FILE_NET = "n_data.txt";//file in cui vengono salvate le reti base
	private static final String FILE_PNET = "pn_data.txt";//file in cui vengono salvate le reti di petri
	private static final String FILE_PNP = "pnp_data.txt";//file in cui vengono salvare le reti pnp

	/**
	 * prende in ingresso una stringa e la converte in un oggetto della classe c
	 * @param s string: stringa da convertire
	 * @param c Class: classe in cui viene convertita
	 * @return oggetto della classe c
	 */
	public static Object jsonToObject(String s, Class c ) {
		Gson gson = new Gson();
		return gson.fromJson(s,c);
	}
	
	public static Object loadFromSource(Class c, String source ) throws FileNotFoundException, IOException {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(source));
			String toLoad = reader.readLine();
			Gson gson = new Gson();
			return gson.fromJson(toLoad,c);
		} catch (FileNotFoundException e) {
			throw e;
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	/**
	 * legge l'intero file e riìestituisce un arrayList in cui ogni stringa rappresenta una riga nel file, e quindi un oggetto 
	 * @param c classe, serve per scegliere quale file andare a leggere
	 * @return ArrayList<String>
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException: classe non valida
	 */
	public static ArrayList<String> readFile(Class c)throws FileNotFoundException, IllegalArgumentException {
		String data;
		if (c == Network.class) 
			data = FILE_NET;
		else if (c == Petri_network.class)
			data = FILE_PNET;
		else if (c == Priority_network.class)
			data = FILE_PNP;
		else 
			throw new IllegalArgumentException("tipo non valido");
		
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
	 * legge e retituisce tutti gli id delle reti salvate sul file
	 * @param c :classe per scegliere quale file leggere
	 * @return
	 */
	public static ArrayList<Integer> getNetIDsFromFile(Class c) {
		ArrayList<Integer> IDs = new ArrayList<>();
		try {
			ArrayList<String> nets = readFile(c);
			Gson gson = new Gson();
			for(String s : nets) {
				IDNameGiver net = (IDNameGiver) gson.fromJson(s, c);
				IDs.add(net.getId());
			}
		} catch (FileNotFoundException e) {
			IDs.add(ID_INIZIALE);
		}
		return IDs;
	}
	/**
	 * controlla se l'id della rete e già presente nel file, quindi se la rete è già salvata
	 * @param id della rete da controllare 	
	 * @param c classe per scegliere il file da controllare
	 * @return
	 */
	public static boolean checkIdExistence(int id, Class c) {
		for (Integer intero : getNetIDsFromFile(c))
			if(intero == id)
				return true;
		return false;
	}
	
	/**
	 * legge e restituisce una lista di tutti i nome delle reti salvare su file
	 * @param c :classe per scegliere quale file leggere 
	 * @return StringBuffer
	 */
	public static StringBuffer getNetNamesList(Class c) {
		StringBuffer names = new StringBuffer();
		try {
			ArrayList<String> nets = readFile(c);
			int i = 0;
			for(String s : nets) {
				IDNameGiver net = (IDNameGiver) jsonToObject(s,c);
				names.append(i+")"+net.getName()+"\n");
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return names;
	}
	
	/**
	 * restituisce un arrayList con tutti i nomi delle reti sul file
	 * @param c classe per scegliere quale file controllare 
	 * @return Array di nomi
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 */
	public static ArrayList<String> getNames(Class c) throws FileNotFoundException, IllegalArgumentException {
		ArrayList<String> nets = readFile(c);
		ArrayList<String> names = new ArrayList<>();
		for(String s : nets) {
			IDNameGiver n = (IDNameGiver) jsonToObject(s, c);
			names.add(n.getName());
		}
		return names;
	}
	
	/**
	 * controlla se il nome appartiene già ad un altra rete
	 * @param name nome da controllare
	 * @param c classe per scegliere il file
	 * @return vero se una rete ha già quel nome, falso altrimenti
	 * @throws FileNotFoundException
	 */
	public static boolean checkNetNameExistence(String name, Class c) throws FileNotFoundException{
		for (String string : getNames(c)) {
			if (name.toUpperCase().equals(string.toUpperCase()))
				return true;
		}
		return false;
	}
	

	
}
