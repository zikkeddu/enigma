package enigma;

public class CryptDecrypt {
	
	public StringBuilder cryptDecrypt (String text , CryptoSettings machine)
	{
		StringBuilder enigmaText = new StringBuilder() ; // Istanzio una stringa dinamica a cui poter aggiungere testo e spazi a piacimento.
		int rotor1Circle = 0 , rotor2Circle = 0 , rotor3Circle = 0 , index = 0 ; // Contatori dei giri dei rotori e indice per succesivo lavoro
		
		char[] textCharArray = text.toLowerCase().toCharArray() ; // Converto eventuale testo maiuscolo e riverso la stringa da elaborare in un array di caratteri
		
		for (char c : textCharArray) // Ciclo for migliorato che gestisce la conversione della stringa
		{
			if (c == ' ')  // se trovo uno spazio, trascrivo direttamente uno spazio, in caso contrario, elaboro il carattere.
			{
				enigmaText.append(" ") ;
			}
			else
			{
				index = machine.getRotor(0).getNextCharRotorIndex(c, 1) ; // Cerco l'indice della lettera nella seconda parte del primo rotore
				index = machine.getRotor(1).getNextCharRotorIndex(machine.getRotor(1).getRotor().get(0).get(index), 1) ; // Cerco nella seconda parte del secondo rotore l'indice della lettera dello stesso rotore parallela a quella presente nell'indice trovato al punto precedente
				index = machine.getRotor(2).getNextCharRotorIndex(machine.getRotor(2).getRotor().get(0).get(index), 1) ; // Cerco nella seconda parte del terzo rotore l'indice della lettera dello stesso rotore parallela a quella presente nell'indice trovato al punto precedente
				index = machine.getRotor(3).getNextCharRotorIndex(machine.getRotor(3).getRotor().get(1).get(index), 0) ; // Trovo l'indice della lettera riflessa sul riflettore accoppiata a quella presente sul medesimo all'indice trovato al passo precedente
				index = machine.getRotor(2).getNextCharRotorIndex(machine.getRotor(2).getRotor().get(1).get(index), 0) ; // Da qui ripercorro il percorso a ritroso con medesima logica invertita.
				index = machine.getRotor(1).getNextCharRotorIndex(machine.getRotor(1).getRotor().get(1).get(index), 0) ;
				index = machine.getRotor(0).getNextCharRotorIndex(machine.getRotor(0).getRotor().get(1).get(index), 0) ;
				
				enigmaText.append(machine.getRotor(0).getRotor().get(0).get(index)) ;
			    
				// Ad ogni carattere passato il primo rotore trasla di una posizione.
				// Se il primo rotore compie un giro completo, il secondo rotore trasla di una posizione
				// Se il secondo rotore compie un giro completo, il terzo rotore trasla di una posizione
				
				machine.getRotor(0).moveRotor();
				rotor1Circle++ ;
				if (rotor1Circle > 26)
				{
					rotor1Circle = 0 ;
					machine.getRotor(1).moveRotor();
					rotor2Circle++ ;
			
					if (rotor2Circle > 26)
					{
						rotor2Circle = 0 ;
						machine.getRotor(1).moveRotor();
						rotor3Circle++ ;
					
						if (rotor3Circle > 26)
						{
							rotor3Circle = 0 ;
						}
					}
				}
			}
			
		}
				
		return enigmaText ;
	}

}
