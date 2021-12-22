package DEV2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer {
	public static int port = 9090;
	public static void main(String[] args){
		try {
			ServerSocket serversocket = new ServerSocket(port);
			while(true) {
				Socket client = serversocket.accept();
				ThreadTask thTask = new ThreadTask(client);
				new Thread(thTask).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
