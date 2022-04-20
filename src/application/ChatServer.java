package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	public static final int PORT = 7000;
	private ServerSocket serverSocket;
	
	//instaciar o server socket
	
	public void start() throws IOException {
		serverSocket = new ServerSocket(PORT);
		System.out.println("Servidor Iniciado na porta: " + PORT);

		clientConnectionLoop();
	}
	
	private void clientConnectionLoop() throws IOException {
		while(true) {
			// aguarda solicitação de um cliente
			ClientSocket clientSocket = new ClientSocket(serverSocket.accept());			
			
			new Thread(() -> clientMessageLoop(clientSocket)).start();
			
		}
	}
	
	public void clientMessageLoop(ClientSocket clientSocket) {
		String msg;
		try {
			while((msg = clientSocket.getMessage()) !=null) {
				if("sair".equalsIgnoreCase(msg))
					return;
				System.out.print(" Mensagem recebida do cleint" + clientSocket.getRemoteSocketAddress() + ": "+ msg);
			}
		}finally {
			clientSocket.close();
		}
	}
	
	public static void main(String[] args) {
		
		try {
			ChatServer server = new ChatServer();
			server.start();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		System.out.println("Servidor finalizado");

	}

}
