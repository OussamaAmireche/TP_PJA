package DEV2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static int port = 9090;
	public static void main(String[] args) {
		try {
			ServerSocket serversocket = new ServerSocket(port);
			Socket socket = serversocket.accept();
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
	        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        output.println("Server Listening on Port " + port);
	        String commande = input.readLine();
	        String tab [] = new String [2];
	        tab = commande.split(" ");
	        if(tab[0].equals("List") && tab.length == 2) {
	        	File file = new File(tab[1]);
	        	if(file.exists()) {
	        		if(file.isDirectory()) {
	        			String msg = "la liste des fichiers contenus dans le répertoire est " + tab[1] + " : ";
	        			for(int i = 0; i < file.list().length; i++) {
	        				msg = msg + " " + (i+1) + "- " +  file.list()[i] + " ";
	        			}
	        			output.println(msg);
	        		}else {
	        			String msg = tab[1] + " is not a directory";
	        			output.println(msg);
	        		}
	        	} else {
	        		output.println("ERROR : File/directory does not exist");
	        	}
	        	
	        }
	        else if(tab[0].equals("Get") && tab.length == 2) {
	        	File file = new File(tab[1]);
	        	int character = 0;
	        	if(file.exists()) {
	        		if(file.isFile()) {
	        			FileReader fileReader = new FileReader(file);
	        			String msg = "Le contenu du fichier est: ";
	        			while(true) {
	        				character = fileReader.read();
	        				if(character == -1) {
	        					break;
	        				}else {
	        					msg = msg + Character.toString(character);
	        				}
	        			}
	        			output.println(msg);
	        		} else {
	        			output.println(tab[1] + " is not a file");
	        		}
	        	} else {
	        		output.println("ERROR : File/directory does not exist");
	        	}
	        }
	        else if(tab[0].equals("Quit") && tab.length == 1) {
	        	output.println("session terminée");
	        }
	        else {
	        	output.println("Commande non reconnue");
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
