package com.memotoro.main;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Clase que permite verificar el estado de la conexion y reconectar si esta
 * caida
 * 
 * @author MEMO
 * 
 */
public class InternetCheck {
	/**
	 * Metodo principal de ejecucion, recibe los parametro en el siguiente orden
	 * 0: Direccion a conectar 1: Nombre de la conexion en el HOST 2: usuario de
	 * la conexion 3: password de la conexion
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Verificar la conexion
		isInternetReachable(args);
	}

	/**
	 * Metodo que verifica si la conexion a internet es posible
	 * 
	 * @param args
	 */
	public static void isInternetReachable(String[] args) {
		try {
			// Creacion de la URL
			URL url = new URL(args[0]);
			// Creacion de la conexion a internet
			HttpURLConnection urlConnect = (HttpURLConnection) url
					.openConnection();
			// Obteniendo stream del sitio
			Object objData = urlConnect.getContent();
		} catch (UnknownHostException e) {
			// Si falla llama al metodo de creacion de conexion
			createConnection(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que crea la conexion con nombre de conexion, usuario y password
	 * 
	 * @param args
	 */
	public static void createConnection(String[] args) {
		try {
			// Comando de consola para cancelar la conexion
			Runtime.getRuntime().exec("rasdial " + args[1] + " /disconnect");
			// Comando de consola para crear la conexion
			Runtime.getRuntime().exec(
					"rasdial " + args[1] + " " + args[2] + " " + args[3]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}