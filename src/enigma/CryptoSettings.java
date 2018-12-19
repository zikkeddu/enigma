package enigma;

import java.util.* ;

// Costruisco la macchina dai parametri acquisiti dall'utente

public class CryptoSettings 
{
	private String[] rotorNumTmp = new String[3] ; // Istanza Array di stringhe di supporto alla conversione in array di interi
	private int[] rotorNum = new int[3] ; // Instanza array di interi relativo alla selezione fatta dall'utente
	private char[] association = new char[7]  ; // Instanza array di caratteri relativo alla selezione fatta dall'utente
	
	private RotorSet rotor1 = new RotorSet(); // Istanza del primo rotore
	private RotorSet rotor2 = new RotorSet(); // Istanza del secondo rotore
	private RotorSet rotor3 = new RotorSet(); // Istanza del terzo rotore
	private RotorSet reflector = new RotorSet(); // Istanza del riflettore
	
	private ArrayList<RotorSet> machineSet = new ArrayList<RotorSet>(Arrays.asList(rotor1,rotor2,rotor3,reflector)); // arraylist contentitore degli elementi della macchina
	
	// Il metodo costruisce i singoli rotori ed il reflector. Consente anche il reset della stessa per un nuovo lavoro coi medesimi parametri (di fatto ripristino la posizione dei rotori).
	
	public void buildResetEnigma (String rotorAssociation , String rotorOrder)
	{
		this.rotorNumTmp = rotorOrder.split("") ; // Estraggo i singoli valori della stringa nell'array di stringhe.
		for (int i = 0 ; i < this.rotorNum.length ; i++) // cast dell'array di stringhe in array di interi
		{
			this.rotorNum[i] = Integer.parseInt(this.rotorNumTmp[i]) ;
		}
		
		this.association = rotorAssociation.replaceAll("[.]", "").trim().toLowerCase().toCharArray() ; // Elimino i punti dalla stringa, elimino eventuali spazi, converto tutto in minuscolo e la riverso in un array di caratteri
		
		this.rotor1.setRotor(this.rotorNum[0], this.association[0], this.association[1]); // Creo il primo rotore 
		this.rotor2.setRotor(this.rotorNum[1], this.association[2], this.association[3]); // Creo il secondo rotore
		this.rotor3.setRotor(this.rotorNum[2], this.association[4], this.association[5]); // Creo il terzo rotore
		this.reflector.setReflector(this.association[6]); // Creo il riflettore
	}
	
	// Rendo visibili all'esterno gli elementi della macchina. Il quarto elemento è il riflettore, a dispetto del nome.
	
	public RotorSet getRotor(int rotorNumber)
	{
		return this.machineSet.get(rotorNumber) ;
	}
	
}
