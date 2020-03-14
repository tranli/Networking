package ServerPhucVuSongSong;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WorkerThread extends Thread {
private Socket socket;


public WorkerThread(Socket socket2) {
	// TODO Auto-generated constructor stub
	this.socket = socket2;
}


@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Processing: "+ socket);
		try {
			OutputStream outputStream = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();
			while (true) {
				int ch = inputStream.read(); //Receive data from client
				if(ch == -1) {
					break;
				}
				outputStream.write(ch);//Send the results to client
			}
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("Request Processing Error: "+ e);
		}
		System.out.println("Complete processing: "+ socket);
	}
}
