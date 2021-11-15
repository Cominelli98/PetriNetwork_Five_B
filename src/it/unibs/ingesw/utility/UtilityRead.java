package it.unibs.ingesw.utility;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import it.unibs.ingesw.IDNameGiver;
import it.unibs.ingesw.ioGson.FromJson;
import it.unibs.ingesw.ioGson.ReadN;

public class UtilityRead {

	
	/**
	 * legge e retituisce tutti gli id delle reti salvate sul file
	 * @param c :classe per scegliere quale file leggere
	 * @return IDs
	 */
	public static ArrayList<Integer> getNetIDsFromFile(Class c) {
		ArrayList<Integer> IDs = new ArrayList<>();
		try {
			ArrayList<String> nets = ReadN.readFile(c);
			for(String s : nets) {
				IDNameGiver net = (IDNameGiver) FromJson.convert(s, c);
				IDs.add(net.getId());
			}
		} catch (FileNotFoundException e) {
			
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
			ArrayList<String> nets = ReadN.readFile(c);
			int i = 0;
			for(String s : nets) {
				IDNameGiver net = (IDNameGiver) FromJson.convert(s, c);
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
		ArrayList<String> nets = ReadN.readFile(c);
		ArrayList<String> names = new ArrayList<>();
		for(String s : nets) {
			IDNameGiver n = (IDNameGiver) FromJson.convert(s, c);
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
