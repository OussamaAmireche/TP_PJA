package DEV2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	final static int port = 9090;
	public static void main(String[] args) throws Exception {
		InetAddress server = InetAddress.getByName("localhost");
		Socket socket = new Socket(server, port);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream out = new PrintStream(socket.getOutputStream());
		String str = in.readLine();
		System.out.println(str);
		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();
		out.println(command);
		String message = in.readLine();
		System.out.println(message);
	}

}
