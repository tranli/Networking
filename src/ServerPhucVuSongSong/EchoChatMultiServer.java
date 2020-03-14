package ServerPhucVuSongSong;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class EchoChatMultiServer {

	public static final int NUM_OF_THREAD = 4;
	public final static int SERVER_PORT =7;
	
	public static void main(String[] args) throws IOException {
		ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREAD);
		ServerSocket serverSocket = null;
		try {
			System.out.println("Binding to port "+ SERVER_PORT + ", please wait");
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server started: "+ serverSocket);
			System.out.println("Waiting for a client ...");
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					System.out.println("Client accepted: "+ socket);
					
					WorkerThread handler = new WorkerThread(socket);
					executor.execute(handler);
							
				} catch (IOException e) {
					// TODO: handle exception
					System.out.println("Connection Error: " +e);
				}
			}

		}catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
		}finally {
			if(serverSocket !=null) {
				serverSocket.close();
			}
		}
	}
}
