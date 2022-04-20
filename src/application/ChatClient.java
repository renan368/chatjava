package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_ADDRESS = "127.0.0.1";
	private Socket clientSocket;
	private Scanner sc;
	private PrintWriter out; 
	
	public ChatClient() {
		sc = new Scanner(System.in);
	}
	
	public void start() throws IOException {
		clientSocket = new Socket(SERVER_ADDRESS, ChatServer.PORT);
		
		//Stream -> fluxo de dados
		System.out.println("Cliente conectado ao servidor em " + SERVER_ADDRESS + ":" + ChatServer.PORT);
		messageLoop();
	}
	
	private void messageLoop() throws IOException {
		String msg;
		do {
			System.out.print("Digite uma mensagem (ou sair para finalizar): ");
			msg = sc.nextLine();
			out.println(msg);
		}while(!msg.equalsIgnoreCase("sair"));
	}
	
	
	public static void main(String[] args) {
		try {
			ChatClient client = new ChatClient();
			client.start();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		System.out.println("Cliente finalizado");
	}

}
