package enigma;

import java.util.Scanner;

public class AlphaAndOmega
{	
	private CryptoSettings machine = new CryptoSettings() ;
	private Scanner input;
	private String rotorAssociation = new String() ;
	private String rotorOrder = new String() ;
	
	// Aquisisco i parametri di settaggio della macchina, la costruisco e la avvio.
	
	public void machineSet() 
	{
		this.input = new Scanner(System.in); // Creo un oggetto scanner per l'acquisizione da console
		
		// Acquisisco gli indici iniziali dei rotori e del riflettore
		
		System.out.print("Inserisci la relazione letterale interna dei 3 rotori e del riflettore (ne basta una), separata da punti (es. pq.ws.ed.r): \n") ;
		this.rotorAssociation = this.input.nextLine() ;

		// Se l'input non è conforme, genero un messaggio di errore fino al corretto inserimento
		
        while (this.rotorAssociation.toLowerCase().matches("^[a-z][a-z]\\.[a-z][a-z]\\.[a-z][a-z]\\.[a-z]$") != true) 
        {
    		System.out.print("Input errato! Sono accettate solo stringhe copie di caratteri dell'alfabeto divise da punti (es. pq.ws.ed.r)! Riprovate: \n") ;
    		this.rotorAssociation = this.input.nextLine() ;
        }
	    
        // Acquisisco l'ordine di assegnazione dei rotori matrice ai 3 rotori reali.
        
		System.out.print("Inserisci l'ordine dei tre rotori (es. 213 o 312): \n") ;
		this.rotorOrder = this.input.nextLine() ;
		
		// Se l'input non è conforme, genero un messaggio di errore fino al corretto inserimento
		
		while (this.rotorOrder.matches("\\b(?!(?:.\\B)*(.)(?:\\B.)*\\1)[123]+\\b") != true)
		{
			System.out.print("Input errato! Potete usare solo numeri dall'1 al 3 e solo una volta per numero (es. 213 o 312). Riprova: \n") ;
			this.rotorOrder = this.input.nextLine() ;
		}
		
		// Costruisco la macchina Enigma
		
		this.machine.buildResetEnigma(this.rotorAssociation, this.rotorOrder);
		
		// Ne lancio la reale esecuzione
		
		this.machineOn();
	}	
	
	// Metodo di elaborazione testi di Enigma. Ciclo while continuo fino a richiesta di interruzione da parte dell'utente
		
	private void machineOn()
	{
		
		String text = new String() ;
		CryptDecrypt algoritm = new CryptDecrypt() ;
		Boolean term = false ;
		Boolean checkText = false ;
				
		do
		{
			
			// Acquisisco il testo da elaborare
			
			System.out.print("Inserisci il testo da cryptare/decryptare (max 600 caratteri, spazi compresi, e solo lettere): \n") ;
			text = this.input.nextLine() ;
			
			// Se supero i 600 caratteri o inserisco punteggiatura o altro che non siano lettere dell'alfabeto, genero un errore
			
			do
			{
				if (text.length() > 600)
				{
					System.out.print("Testo inserito troppo lungo! Riprovare inserendo al massimo 600 caratteri: \n") ;
					text = this.input.nextLine() ;
					
				}
				else
				{
					checkText = true ;
				}
				
				if (text.toLowerCase().matches("[a-zA-Z ]*"))
				{
					checkText = true ;
				}
				else
				{
					System.out.print("Testo inserito con caratteri non consentiti! Riprovare inserendo solo caratteri consentiti: \n") ;
					text = this.input.nextLine() ;
				}
				
				
			} while (checkText == false) ;
			
			// Elaboro e stampo a video il testo
			
			System.out.print("Ecco il testo cryptato: \n" + algoritm.cryptDecrypt(text, machine) + "\n\n") ;
			
			// Chiedo all'utente se chiudere il programma o procedere ad un'altra elaborazione. Se l'utente vuole proseguire, resetto l'assetto della macchina.
			
			System.out.print("Vuoi cryptare/decryptare qualcos'altro? y/n; \n") ;
			String choose = this.input.nextLine() ;
			
			term = false ;
			
			if (choose.toLowerCase().charAt(0) == 'n')
			{
				term = true ;
			}
			else
			{
				this.machine.buildResetEnigma(this.rotorAssociation, this.rotorOrder);
			}
			
		} while (term == false) ;
		
		input.close() ;
	}
}
