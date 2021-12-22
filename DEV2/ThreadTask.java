package DEV2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadTask implements Runnable {
	private final Socket clientSocket;
	
	public ThreadTask(Socket socket) {
		this.clientSocket = socket;
	}
	
	public void run() {
		try {
			PrintWriter output = new PrintWriter(this.clientSocket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
	        output.println("Server Listening on Port " + this.clientSocket.getPort());
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
