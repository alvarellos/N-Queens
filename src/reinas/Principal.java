package reinas;

import java.io.IOException;

/**
 * @author Diego Diaz
 * Clase principal de las N-Reinas
 * con las métodos de entrada y los 
 * posibles argumentos
 */
public class Principal {

	static int contador = 1;
	
	public static void ayuda(){
		 System.out.println("SINTAXIS:");
		 System.out.println("reinas   [-t][-g][-h] N [fichero_salida]");
		 System.out.println("-t                      Traza");
		 System.out.println("-g                      Muestra solución en modo gráfico");
		 System.out.println("-h                      Muestra esta ayuda");
		 System.out.println(" N                      Tamaño tablero / Nº de reinas");
		 System.out.println(" fichero_salida         Nombre fichero de salida");
	}
	
	public static int EnteroEntrada(String argv) throws IOException{
		
		int Entrada = 0;
		
		try{
			// Cast String -> Entero
			Entrada = Integer.parseInt(argv);

			if (Entrada > 27 | Entrada < 4){
		          System.err.println("ERROR: El digito " + Entrada + " no se permite");
		          System.out.println("Introduzca un valor entre 4 y 27");
		          System.exit(1);
			}
		}
	
		catch (Exception e){
			System.err.println("ERROR: El argumento introducido \"" + argv + "\" no es válido");
			System.exit(1);
		}
		return Entrada;
	}
	

   	// ***************     METODO PRINCIPAL        ********************
   	// ----------------------------------------------------------------
   	
     public static void main(String[] argv) throws IOException {
   	  
   	  
   	  // CON UN ARGUMENTO
   	  // ------------------------------------------------------------------
   	 if ( argv.length == 1 ) { 
   		 
   		 switch(argv[0]){

   		 	case "-h":{
   			 	ayuda();
   			 	break;
   		 	}
   		 	case "-g":{

   			    // Ejemplo con gráfico de tamaño 4
   		    	Algoritmo Q = new Algoritmo(4, "ninguno");
   		        Q.ReinasTablero();
   				break;
   		 	}
   		 
   		 	case "-t":{

			    // Ejemplo con traza de tamaño 4
		    	Algoritmo Q = new Algoritmo(4, "ninguno");
		        Q.ReinasTraza();
				break;
   		 	}
   		 
   		 	default:{
   			 	// N : tamaño del tablero / Nº de reinas
   			 	int tamanyoTablero = EnteroEntrada(argv[0]);
   		    	Algoritmo Q = new Algoritmo(tamanyoTablero, "ninguno");
   		        Q.Reinas();
   		        break;
   		 	}
   		 }
   	 }

   	  // CON DOS ARGUMENTOS
   	  // ------------------------------------------------------------------
   	 else if ( argv.length == 2 ) { 

   		 	if (argv[0].equals("-h") || argv[1].equals("-h")) {
   			// [-h] [*]  | [*][-h]
   		 		ayuda();
   		 	}
   		 		
   		 	else if (argv[0].equals("-t")){
   			// [-t] [N]
   	   		 	int tamanyoTablero = EnteroEntrada(argv[1]);
   	   		   	Algoritmo Q = new Algoritmo(tamanyoTablero, "ninguno");
   	   		    Q.ReinasTraza();
   		 	}
   		 		
   		 	else if (argv[0].equals("-g")){
   			// [-g] [N] 
   	   		 	int tamanyoTablero = EnteroEntrada(argv[1]);
   	   		   	Algoritmo Q = new Algoritmo(tamanyoTablero, "ninguno");
   	   		    Q.ReinasTablero();
   		 	}
   		 		
	 		
   		 	else {
   		 	// [N] [fichero_salida]
   	   		 	int tamanyoTablero = EnteroEntrada(argv[0]);
   	   		 	String nombreFichero = argv[1];
   	   		   	Algoritmo Q = new Algoritmo(tamanyoTablero, nombreFichero);
	 			Q.ReinasFichero();
	 		}
   	 } 

   	 // CON TRES ARGUMENTOS
   	 // ------------------------------------------------------------------	 
   	 else if ( argv.length == 3 ) {
   		 
   		 
   		 if (argv[0].equals("-h") || argv[1].equals("-h") || argv[2].equals("-h")) {
   		 // [-h][*][*] | [*][-h][*] | [*][*][-h]
   			 ayuda();
   		 }
   		 
   		 else if (argv[0].equals("-t") && argv[1].equals("-g")) {
   		 // [-t] [-g] [N]
   			 int tamanyoTablero = EnteroEntrada(argv[2]);
	   		 Algoritmo Q = new Algoritmo(tamanyoTablero, "ninguno");
	   		 Q.ReinasTrazaTablero();
   		 }
   		 
   		 else if (argv[0].equals("-g") && argv[1].equals("-t")) {
   		 // [-g] [-t] [N]
   		 	int tamanyoTablero = EnteroEntrada(argv[2]);
	   	    Algoritmo Q = new Algoritmo(tamanyoTablero, "ninguno");
	   	    Q.ReinasTrazaTablero();
   		 }
   		 
   		 else if (argv[0].equals("-t")) {
   		 // [-t] [N] [fichero_salida]
   		 	int tamanyoTablero = EnteroEntrada(argv[1]);
   		 	String nombreFichero = argv[2];
	   	    Algoritmo Q = new Algoritmo(tamanyoTablero, nombreFichero);
	   	    Q.ReinasTrazaFichero();
   		 }
   		 
   		 else if (argv[0].equals("-g")) {
   		 // [-g] [N] [fichero_salida]
   		 	int tamanyoTablero = EnteroEntrada(argv[1]);
   		 	String nombreFichero = argv[2];
	   	    Algoritmo Q = new Algoritmo(tamanyoTablero, nombreFichero);
	   	    Q.ReinasTableroFichero();
   		 }
   		   		 
   		 else {
   			 System.out.println("Los argumentos no son correctos");
   		 }
   	 }

   	 // CON 4 ARGUMENTOS
   	 // ------------------------------------------------------------------	 
   	 else if ( argv.length == 4 ) { 
   		 
   		 if (argv[0].equals("-h") || argv[1].equals("-h") || argv[2].equals("-h")|| argv[3].equals("-h")) {
   		 // [-h][*][*] | [*][-h][*] | [*][*][-h]
   			 ayuda();
   		 }
   		 
   		 else if (argv[0].equals("-t")){
   		// [-t] [-g] N [fichero_salida]
   		 	int tamanyoTablero = EnteroEntrada(argv[2]);
   		 	String nombreFichero = argv[3];
	   	    Algoritmo Q = new Algoritmo(tamanyoTablero, nombreFichero);
	   	    Q.ReinasTrazaTableroFichero();
   		 }
   		 
   		 else if (argv[0].equals("-g")){
   		 // [-g] [-t] N [fichero_salida]
   		 	int tamanyoTablero = EnteroEntrada(argv[2]);
   		 	String nombreFichero = argv[3];
	   	    Algoritmo Q = new Algoritmo(tamanyoTablero, nombreFichero);
	   	    Q.ReinasTrazaTableroFichero();
   		 }
   		   		 
   		 else {
   			 System.out.println("Los argumentos no son correctos");
   		 }
   	 }
   	 
   	 // CON MÁS DE 4 ARGUMENTOS
   	 // ------------------------------------------------------------------	 	 
   	 else if ( argv.length > 4 ) {
   		 System.out.println("No se ha introducido un número de argumentos válido");
   	 }

   	 // SIN ARGUMENTOS
   	 // ------------------------------------------------------------------	 
   	 else{
   		// Ejemplo de tablero de tamaño 4
   		 Algoritmo Q = new Algoritmo(4, "ninguno");
	     Q.Reinas();	 
   	 }
   	  	
     } // fin método Principal   
}
