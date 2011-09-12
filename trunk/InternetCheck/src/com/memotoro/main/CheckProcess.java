package com.memotoro.main;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Clase que implementa runnable y permite revisar la conexion a internet
 * 
 * @author memo
 * 
 */
public class CheckProcess implements Runnable {
	/**
	 * String con la URL a conectarse
	 */
	private String url;
	/**
	 * String con el nombre de la conexion
	 */
	private String connectionName;
	/**
	 * Stirng con el usuario de conexion ADSL
	 */
	private String user;
	/**
	 * Stirng con el password de conexion ADSL
	 */
	private String password;
	/**
	 * Integer con el tiempo de revision del thread
	 */
	private Integer tiempoRevision;

	/**
	 * Constructor de la clase e inicializacion de variables
	 * 
	 * @param args
	 */
	public CheckProcess(String[] args) {
		this.url = args[0];
		this.connectionName = args[1];
		this.user = args[2];
		this.password = args[3];
		this.tiempoRevision = Integer.valueOf(Integer.parseInt(args[4]) * 1000);
	}

	/**
	 * Metodo RUN de Runnable
	 */
	public void run() {
		// Bucle ifinito
		while (true)
			try {
				// Llamado al metodo de verificaicon de conexion
				isInternetReachable();
				// Durmiendo el thread hasta el tiempo de revision
				Thread.sleep(this.tiempoRevision.intValue());
				continue;
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
	}

	/**
	 * Metodo que permite verificar si la conexion a internet esta disponible
	 */
	public void isInternetReachable() {
		try {
			// Crea la URL
			URL url = new URL(this.url);
			// Crea la conexion a la URL dada
			HttpURLConnection urlConnect = (HttpURLConnection) url
					.openConnection();
			// Lee un stream de datos de la conexion
			Object objData = urlConnect.getContent();
		} catch (UnknownHostException e) {
			Object objData;
			createConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para crear la conexion en case de falla
	 */
	public void createConnection() {
		try {
			// Ejecucion de comando de desconexion
			Runtime.getRuntime().exec(
					"rasdial " + this.connectionName + " /disconnect");
			// Ejecucion de comando de conexion
			Runtime.getRuntime().exec(
					"rasdial " + this.connectionName + " " + this.user + " "
							+ this.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}