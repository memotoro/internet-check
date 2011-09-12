package com.memotoro.main;

/**
 * Clase que permite inicia el proceso de verificacion de conexion a internet
 * 
 * @author memo
 * 
 */
public class InternetCheck {
	/**
	 * Metodo principal de la aplicacion Recibe los parametros de ejecucion del
	 * aplicativo
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Creacion de variable Runnable con parametros de invocacion
		CheckProcess checkProcess = new CheckProcess(args);
		// Creacion de thread de ejecucion
		Thread revisionInternet = new Thread(checkProcess);
		// Invocacion del metodo de inicializacion del thread
		revisionInternet.start();
	}
}