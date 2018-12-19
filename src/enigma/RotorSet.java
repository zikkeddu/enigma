package enigma;

import java.util.*;

public class RotorSet
{
    // Definisco un arraylist come rotore singolo da esportare

    private ArrayList<ArrayList<Character>> rotorBox = new ArrayList<ArrayList<Character>>();
    
    
    public void setRotor (int rotorNumber , char firstLeft , char firstRight)
    {
    	/* Con questo metodo creo un oggetto rotore con cui costruire la mia macchina. Il rotore avrà una sezione di ingresso ed una di uscita del medesimo array che saranno sfalsate seguendo la lettera iniziale definita 
    	 *  dall'utente. Tale differenza costituirà la permutazione della lettera da criptare al passaggio della stessa per il rotore */
    	
    	// Definisco un set di combinazioni da usare nella costruzione dei rotori 
    	
        ArrayList<Character> rotorSet1 = new ArrayList<Character>(Arrays.asList('w','e','r','t','z','u','i','o','a','s','d','f','g','h','j','k','p','y','x','c','v','b','n','m','l','q'));
        ArrayList<Character> rotorSet2 = new ArrayList<Character>(Arrays.asList('d','e','b','x','u','r','c','g','m','q','s','y','n','p','f','l','o','i','v','j','t','z','w','h','k','a'));
        ArrayList<Character> rotorSet3 = new ArrayList<Character>(Arrays.asList('o','f','y','r','q','z','g','m','h','j','w','e','a','u','v','l','k','i','t','p','n','c','b','s','d','x'));
        ArrayList<Character> rotorSet4 = new ArrayList<Character>(Arrays.asList('i','h','u','g','t','e','v','j','c','l','x','z','r','a','q','n','b','f','s','o','y','p','w','d','k','m'));
        ArrayList<Character> rotorSet5 = new ArrayList<Character>(Arrays.asList('b','q','f','p','w','h','n','m','e','o','c','a','v','j','x','l','r','y','d','u','i','g','z','s','k','t'));
        ArrayList<Character> rotorSet6 = new ArrayList<Character>(Arrays.asList('a','b','y','q','m','h','e','w','l','v','n','x','z','i','f','u','k','t','c','j','g','d','s','o','p','r'));
        ArrayList<Character> rotorSet7 = new ArrayList<Character>(Arrays.asList('e','r','t','z','u','i','o','a','s','d','f','g','h','j','k','p','y','x','c','v','b','n','m','l','q','w'));
        
        // Per comodità di gestione raggruppo i modelli dei rotori in un arraylist
        
        ArrayList<ArrayList<Character>> rotorMatrix = new ArrayList<ArrayList<Character>>(Arrays.asList(rotorSet1,rotorSet2,rotorSet3,rotorSet4,rotorSet5,rotorSet6,rotorSet7));
        
        // Trovo l'indice dell'array matrice scelto sulla base della lettera indicata dall'utente come prima lettera dei semirotori per creare le due parti del rotore finale
        
    	int indexL = rotorMatrix.get(rotorNumber).indexOf(firstLeft) ;
    	int indexR = rotorMatrix.get(rotorNumber).indexOf(firstRight) ;
    	
    	// Definisco dei contatori per continuare a scorrere l'array
    	
    	int i = 0 , j = 0 ;
		
    	// Creo i due arraylist che serviranno a costruire il rotore
    	
    	ArrayList<Character> rotorL = new ArrayList<Character>();
    	ArrayList<Character> rotorR = new ArrayList<Character>();
	
    	// Popolo il sotto array1 di destinazione partendo dalla lettera all'indice dell'array matrice ricavato sopra scorrendo l'array matrice dall'indice alla fine.
    	
    	while (indexL < rotorMatrix.get(rotorNumber).size())
    	{
            rotorL.add(rotorMatrix.get(rotorNumber).get(indexL)) ;
            indexL++;
    	}
	
    	// Proseguo popolando il sotto array1 di destinazione con i valori restanti dal primo valore dell'array matrice al valore precedente quello trovato dall'indice.
    	
    	indexL = rotorMatrix.get(rotorNumber).indexOf(firstLeft) ;
		
    	while (i < indexL)
    	{
            rotorL.add(rotorMatrix.get(rotorNumber).get(i)) ;
            i++;
    	}
		
    	// Ripeto per il sotto array2 con gli opportuni valori
    	
    	while (indexR < rotorMatrix.get(rotorNumber).size())
    	{
            rotorR.add(rotorMatrix.get(rotorNumber).get(indexR)) ;
            indexR++;
    	}
		
    	indexR = rotorMatrix.get(rotorNumber).indexOf(firstRight) ;
		
    	while (j < indexR)
    	{
            rotorR.add(rotorMatrix.get(rotorNumber).get(j)) ;
            j++;
    	}
    	
    	// Unisco le due parti nell'array rotore finale
    	// Se il rotore è vuoto, aggiungo le due parti.
    	// Se già pieno, lo resetto prima.
    	
    	if (this.rotorBox.isEmpty())
    	{
    		this.rotorBox.add(rotorL);
    		this.rotorBox.add(rotorR);
    	}
    	else
    	{
    		this.rotorBox.clear();
    		this.rotorBox.add(rotorL);
    		this.rotorBox.add(rotorR);
    	}
    }
    
    public void setReflector (char first)
    {
        // Definisco un set di refletor da usare nella creazione del reflector reale
    	
        ArrayList<Character> reflectorL = new ArrayList<Character>(Arrays.asList('i','h','u','g','t','e','v','j','c','l','x','z','r','a','q','n','b','f','s','o','y','p','w','d','k','m'));
        ArrayList<Character> reflectorR = new ArrayList<Character>(Arrays.asList('e','r','t','z','u','i','o','a','s','d','f','g','h','j','k','p','y','x','c','v','b','n','m','l','q','w'));
        
        // Lavoro in modo analogo a quanto già visto nella creazione dei rotori.
        // L'unica differenza risiede nel fatto che, per questo particolare rotore, l'indice della lettera indicata vale per entrambe le sottoparti del rotore
        
    	int index = reflectorL.indexOf(first) ;
    	int i = 0 ;
    	
    	ArrayList<Character> rotorL = new ArrayList<Character>();
    	ArrayList<Character> rotorR = new ArrayList<Character>();
		
    	while (index < reflectorL.size())
		{
            rotorL.add(reflectorL.get(index)) ;
            rotorR.add(reflectorR.get(index)) ;
            index++;
		}
		
    	index = reflectorL.indexOf(first) ;
		
    	while (i < index)
    	{
            rotorL.add(reflectorL.get(i)) ;
            rotorR.add(reflectorR.get(i)) ;
            i++;
    	}
    	if (this.rotorBox.isEmpty())
    	{
    		this.rotorBox.add(rotorL);
    		this.rotorBox.add(rotorR);
    	}
    	else
    	{
    		this.rotorBox.clear();
    		this.rotorBox.add(rotorL);
    		this.rotorBox.add(rotorR);
    	}
    }
    
    // Rendo disponibile il rotore creato verso l'esterno 
    
    public ArrayList<ArrayList<Character>> getRotor()
    {
    	return this.rotorBox ;
    }
    
    // Sposto il rotore avanti di una posizione, sottraendo una lettera.
    
    public void moveRotor()
	{
    	
		for (int i=0 ; i < this.rotorBox.get(0).size() ; i++)
		{
			// Se la lettera da spostare è 'a' , devo ottenere una 'z'. In caso contrario procedo regredendo di una.
			
			if (this.rotorBox.get(0).get(i) == 'a' )
			{
				this.rotorBox.get(0).set(i , (char)(this.rotorBox.get(0).get(i) + 25)) ;
			}
			else
			{
				this.rotorBox.get(0).set(i , (char)(this.rotorBox.get(0).get(i) - 1)) ;
			}
			
			if (this.rotorBox.get(1).get(i) == 'a' )
			{
				this.rotorBox.get(1).set(i , (char)(this.rotorBox.get(1).get(i) + 25)) ;
			}
			else
			{
				this.rotorBox.get(1).set(i , (char)(this.rotorBox.get(1).get(i) - 1)) ;
			}
		}
	}
    
    // Cerco l'indice della lettera in ingresso nella prima parte del vettore nel sua parte d'uscita
    
	public int getNextCharRotorIndex (char character , int side )
	{
		int rotorBoxIndex = this.rotorBox.get(side).indexOf(character) ;
		
		return rotorBoxIndex ;
	}
}

