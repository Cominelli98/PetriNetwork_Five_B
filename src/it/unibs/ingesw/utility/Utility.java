package it.unibs.ingesw.utility;

import it.unibs.ingesw.*;

import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public final class Utility {
	
	
	private static Scanner scanner = creaScanner();
	private final static String ERRORE_INSERIMENTO = "Errore inserimento, per favore inserisci un valore appropriato";
	
	private static Scanner creaScanner() {
		Scanner creato = new Scanner(System.in);
		return creato;
	}
	
	public static int readInt() {
		int i = 42;
		boolean finito = false;
		
		do {
			try {
				i = scanner.nextInt();
				finito = true;
				var throwable = scanner.nextLine();
			}catch (InputMismatchException e) {
				System.out.println(ERRORE_INSERIMENTO);
				var throwable = scanner.nextLine();
			}
		}while (!finito);
		return i;
	}
	
	public static int readLimitedInt(int min, int max) { //legge interi con estremi compresi
		int i = min - 1;
		boolean finito = false;
		do {
			if(max >= min) {
				i = readInt();
				if(i>=min && i<= max)
					finito = true;
				else System.out.println("Valore non compreso tra gli estremi, reinserire:");
			}else {
				System.out.println("Valori di max e min inseriti errati, chiudi il programma e inserisci i dati in modo corretto");
				finito = true;
			}
		}while(!finito);
		return i;
		
	}
	
	public static int readLowLimitInt(int min) { //legge interi con estremo inferiore compreso
		int i = min - 1;
		boolean finito = false;
		do {
			i = readInt();
			if(i>=min)
				finito = true;
			else System.out.println("Valore inferiore al minimo, reinserire");
			
		}while(!finito);
		return i;
		
	}
	
	public static String readString() {
		String name = scanner.nextLine();
		return name;
	}
	
	public static void close() {
		scanner.close();
	}

	public static String readCheckedName(ArrayList<? extends IDNameGiver> toCheck, String RICHIESTA, String ERRORE){
		
		String name = "";
		boolean finito;
		do {
			finito = true;
			System.out.println(RICHIESTA);
			name = readString();
			if(toCheck.size()>0) {
				for (IDNameGiver n : toCheck) {
					if (nameCheck(n, name)) {
						finito = false;
						System.out.println(ERRORE);
						var throwable = scanner.nextLine();
					}
				}
			}
		}while(!finito);
		return name;
	}
	
	public static boolean nameCheck(IDNameGiver n, String s) {
		if (n.getName().toUpperCase().equals(s.toUpperCase()))
			return true;
		return false;
	}
	
	
	public static int getMax(ArrayList<Integer> integer) {
	 int max = 0;
	 for(Integer n : integer) {
		 if(n>max) max = n;
	 }
	 return max;
	}

	
}
