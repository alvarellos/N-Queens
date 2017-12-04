package reinas;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Diego Díaz
 * Clase que implementa el algoritmo de Vuelta Atrás (Backtracking)
 * Incluye los métodos necesarios para imprimir las soluciones
 * según los argumentos de entrada
 */
public class Algoritmo {


	static int contador =1; // Para la cuenta de soluciones
	String fichero = null;
	int[] t;
	    

	// Constructor
	public Algoritmo(int N, String nombreFichero) {
	t = new int[N];
	fichero = new String(nombreFichero);        
	}
	    
    // Métodos

    /**
     * @param f Filas del tablero
     * @param c Columnas del tablero
     * @return Cierto si se puede colocar Reina(fila, columna)
     * t[] es un array con los valores tablero[pos] 
     * ya establecidos 
     */
    public boolean reinaNoAmenaza(int f, int c) {

    	for (int i = 0; i < f; i++) {
    		// Comprobar Columna, fila, diagonal
    		if (t[i] == c || (i - f) == (t[i] - c) || (i - f) == (c - t[i])) {
                return false;
            }
	    }
	        return true;
    }



    /**
     * Método que se encarga de escribir el Tablero gráfico
     */
    public static Integer escribeTablero(int[] x, String fichero) throws IOException {
	int N = x.length;
			
	// Sin fichero
	if ( fichero == null ){
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (x[i] == j) {
	           		System.out.print("R ");               		              	
	           	} else {
	           		System.out.print("* ");               		
	           	}
			}
			System.out.println();               		
		}
	    System.out.println();   
	    			
	// Con fichero
	}else{	
	    try{
	            	
	    	File ficheroSalida = new File(fichero);
	        // Si no existe crea el fichero
	        if(!ficheroSalida.exists())
	        	ficheroSalida.createNewFile();

         	FileWriter writer = new FileWriter(fichero, true);
	           	
        	writer.append(System.getProperty("line.separator"));
           	for (int i = 0; i < N; i++) {
           		for (int j = 0; j < N; j++) {
           			if (x[i] == j) {
           					writer.append("R ");               		              	
           			} else {
           					writer.append("* ");               		
           			}
           		}
           	writer.append(System.getProperty("line.separator"));              		
           	}
        writer.append(System.getProperty("line.separator"));
	    writer.close();
	    		
	    } catch (Exception e) {
	    	System.err.println("Ha fallado el proceso de escritura en el fichero " + fichero);
	   	}	             		
	} // Fin con fichero
				
	contador++;
	return contador;
    }
	    
    
    /**
     * Método que se encarga de escribir la lista de soluciones
     */
    public static Integer escribeLista(int[] x, String fichero) throws  IOException {
	int N = x.length;
	ArrayList<String> list = new ArrayList<String>();
	String listString = "";
			
	// Sin fichero
	if ( fichero == null) {
	          	
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (x[i] == j) {
					Caracter columna = new Caracter();
	            	String col = columna.columnaTexto(j);
	            	list.add(col + (N-i));
	            } 
			}
	        // Ordena la lista en orden alfabético
	        Collections.sort(list, String.CASE_INSENSITIVE_ORDER); 
		}
	    System.out.print(contador + " : ");
	    System.out.print(list);
	    System.out.println();
	}
			
	// Con fichero
	else{
		try{
			File ficheroSalida = new File(fichero);
			// Si no existe crea el fichero
			if(!ficheroSalida.exists())
				ficheroSalida.createNewFile();

			FileWriter writer = new FileWriter(fichero, true);
    	
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (x[i] == j) {
						Caracter columna = new Caracter();
						String col = columna.columnaTexto(j);
						list.add(col + (N-i));
					} 
				}
				// Ordena la lista en orden alfabético
				Collections.sort(list, String.CASE_INSENSITIVE_ORDER); 
			}
			// Cast 'ArrayList<String> to 'String'
			for (String s : list){
				listString += s + "\t";
			}
			writer.append(System.getProperty("line.separator"));
			writer.append(contador + " : ");
			writer.append(listString);
			writer.append(System.getProperty("line.separator"));
			writer.close();
		} catch (Exception e) {
			System.err.println("Ha fallado el proceso de escritura en el fichero " + fichero);
		} 
	}
	contador++;
	return contador;
    }


	    /**
	     * Método que implementa el algoritmo de vuelta atrás
	     * debe de tener en cuenta los argumentos de entrada para seleccionar
	     * la salida correcta
	     */
    public void colocaNreinas(int r, int n, Boolean traza, Boolean tablero, String fichero) throws FileNotFoundException, UnsupportedEncodingException {
    	for (int columna = 0; columna < n; columna++) {
    		
    		if (reinaNoAmenaza(r, columna)) {
    			
    			t[r] = columna;
    			
    			// Escribe posiciones descartadas con Traza activada
    			if ( traza == true ){
    				
    				// Salida estándar
    				if ( fichero == null){
    					int fila = r +1;
    					Caracter Ccolumna = new Caracter();
    					String col = Ccolumna.columnaTexto(columna);
    					System.out.print(" " + col + fila + " ");
	                	
    				// Salida a fichero
    				} else{
    					try{
    						File ficheroSalida = new File(fichero);
    						// Si no existe crea el fichero
    						if(!ficheroSalida.exists())
    							ficheroSalida.createNewFile();

    						FileWriter writer = new FileWriter(fichero, true);
    						int fila = r +1;
    						Caracter Ccolumna = new Caracter();
    						String col = Ccolumna.columnaTexto(columna);
    						writer.append(" " + col + fila + " ");
    						writer.close();
    					}catch (Exception e) {
    						System.err.println("Ha fallado el proceso de escritura en el fichero " + fichero);
    					}
    				}
    			}
    			
    			// Coloca una solución
    			if (r == n - 1) {
	                	
    					// Salida estándar
	                	if ( fichero == null) {
	                		// Escribe Tablero
	                		if ( tablero == true) {
	                			System.out.println();
	                			try {
									escribeTablero(t, fichero);
								} catch (IOException e) {
									e.printStackTrace();
								}
	                		// Escribe lista
	                		}else{
	                			System.out.println();
	                			try {
									escribeLista(t, fichero);
								} catch (IOException e) {
									e.printStackTrace();
								}
	                		}
	                	}
	                	
	                	// Salida con fichero
	                	
	                	else{
	                		// Escribe Tablero
	                		if ( tablero == true) {
	                			try {
									escribeTablero(t, fichero);
								} catch (IOException e) {
									e.printStackTrace();
								}
	                		}else{
	                			try {
									escribeLista(t, fichero);
								} catch (IOException e) {
									e.printStackTrace();
								}
	                		}
	                	}

	            // Llamada siguiente
    			} else {
    				// Sin fichero
    				if ( fichero == null ){
    					if (( traza ==  true) && ( tablero == true)) {
    						colocaNreinas(r + 1, n, true, true, fichero);
    					} 
    					else if (( traza == true) && ( tablero == false)){
    						colocaNreinas(r + 1, n, true, false, fichero);
    					}
    					else if (( traza == false) && ( tablero == true)){
    						colocaNreinas(r + 1, n, false, true, fichero);
    					}
    					else{
    						colocaNreinas(r + 1, n, false, false, fichero);
    					}
    				}
    				// Con fichero
    				else{
    					if (( traza ==  true) && ( tablero == true)) {
    						colocaNreinas(r + 1, n, true, true, fichero);
    					} 
    					else if (( traza == true) && ( tablero == false)){
    						colocaNreinas(r + 1, n, true, false, fichero);
    					}
    					else if (( traza == false) && ( tablero == true)){
    						colocaNreinas(r + 1, n, false, true, fichero);
    					}
    					else{
    						colocaNreinas(r + 1, n, false, false, fichero);
    					}
    				}
    			} // fin llamada siguiente
    		} // fin Reina no amenaza
    	}
    }
	    
    // Métodos de llamada con los diferentes parámetros (*, tamaño, traza, tablero, fichero)
    // -------------------------------------------------------------------------------------

    public void Reinas() throws IOException {
    	try{
	        colocaNreinas(0, t.length, false, false, null);
	        System.out.println("");
	        System.out.print("Numero de soluciones : ");
	        System.out.print(contador-1);
	        System.out.println();
    	}catch (Exception e) {
    		System.err.println("Ha fallado el proceso de escritura en el fichero " + fichero);
    	}
    }
	    
    public void ReinasTraza() throws IOException {
    	try{
	        colocaNreinas(0, t.length, true, false, null);
	        System.out.println("");
	        System.out.print("Numero de soluciones : ");
	        System.out.print(contador-1);
	        System.out.println();
    	}catch (Exception e) {
    		System.err.println("Ha fallado el proceso de escritura en el fichero " + fichero);
    	}
    }
	    
    public void ReinasTablero() throws IOException {
    	try{
	        colocaNreinas(0, t.length, false, true, null);
	        System.out.println();
	        System.out.print("Numero de soluciones : ");
	        System.out.print(contador-1);
	        System.out.println();
    	}catch (Exception e) {
			System.err.println("Ha fallado el proceso de escritura en el fichero " + fichero);
    	}
    }
	    
    public void ReinasTrazaTablero() throws IOException {
	   	try{
	        colocaNreinas(0, t.length, true, true, null);
	        System.out.println();
	        System.out.print("Numero de soluciones : ");
	        System.out.print(contador-1);
	        System.out.println();
	   	}catch (Exception e) {
			System.err.println("Ha fallado el proceso de escritura en el fichero " + fichero);
	    }
    }
	    
    public void ReinasFichero() throws IOException {
    	try{
	    	// (0, tamaño, traza, tablero, fichero )
	        colocaNreinas(0, t.length, false, false, fichero);
	        System.out.println();
	        System.out.print("Numero de soluciones : ");
	        System.out.println(contador-1);
	        System.out.println("Se ha escrito correctamente en el fichero " + fichero);
    	}catch (Exception e) {
    		System.err.println("Ha fallado el proceso de escritura en el fichero " + fichero);
    	}
    }
	    
    public void ReinasTrazaFichero() throws IOException {
    	try{
	        colocaNreinas(0, t.length, true, false, fichero);
	        System.out.println();
	        System.out.print("Numero de soluciones : ");
	        System.out.println(contador-1);
	        System.out.println("Se ha escrito correctamente en el fichero " + fichero);
    	}catch (Exception e) {
    		System.err.println("Ha fallado el proceso de escritura en el fichero " + fichero);
    	}
    }
	    
    public void ReinasTableroFichero() throws IOException {
    	try{
	        colocaNreinas(0, t.length, false, true, fichero);
	        System.out.println();
	        System.out.print("Numero de soluciones : ");
	        System.out.println(contador-1);
	        System.out.println("Se ha escrito correctamente en el fichero " + fichero);
    	}catch (Exception e) {
    		System.err.println("Ha fallado el proceso de escritura en el fichero " + fichero);
    	}
    }
	    
    public void ReinasTrazaTableroFichero() throws IOException {
    	try{
	        colocaNreinas(0, t.length, true, true, fichero);
	        System.out.println();
	        System.out.print("Numero de soluciones : ");
	        System.out.println(contador-1);
	        System.out.println("Se ha escrito correctamente en el fichero " + fichero);
    	}catch (Exception e) {
    		System.err.println("Ha fallado el proceso de escritura en el fichero " + fichero);
    	}
    }
	    
	 // Otra manera de imprimir que no se utiliza
//	    public void escribeCoordenadas(int[] x) {
//	        int N = x.length;
//	        
//	        for (int i = 0; i < N; i++) {
//	            for (int j = 0; j < N; j++) {
//	                if (x[i] == j) {
//	                	Caracter columna = new Caracter();
//	                	String col = columna.columnaTexto(j);
//	                	
//	                    System.out.print(col + (N-i) + " ");
//
//	                } else {
//	                    System.out.print("*  ");
//	                }
//	            }
//	            System.out.println();
//	        }
//	        System.out.println();
//	    }
	    
}
