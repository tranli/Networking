package Server_UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoClient {

	public final static String SERVER_IP = "127.0.0.1";
	public final static int SERVER_PORT = 7; //Cổng mặc định của Echo Server
	public final  static byte[] BUFFER = new byte[4096];//Vùng đệm chứa dữ liệu gói tin nhận
	
	public static void main(String[] args) {
		DatagramSocket datagramSocket = null;
		try {
			datagramSocket = new DatagramSocket(); 
			System.out.println("Client started");
			
			InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
			while (true) {
				System.out.println("Enter your message: ");
				InputStreamReader inputStreamReader = new InputStreamReader(System.in);//Nhập
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //một chuỗi
				String theString = bufferedReader.readLine();//từ bàn phím
				byte[] data = theString.getBytes();//Đổi chuỗi ra mảng byte
				
				//Tạo gói tin gửi
				DatagramPacket datagramPacket = new DatagramPacket(data, data.length, serverAddress, SERVER_PORT);
				datagramSocket.send(datagramPacket);//Send gois tin sang cho echo server
				
				//Gói tin nhận
				DatagramPacket incomingDatagramPacket = new DatagramPacket(BUFFER, BUFFER.length);
				datagramSocket.receive(incomingDatagramPacket); //Chờ nhận dữ liệu từ echo server 
				
				//Đổi dữ liệu nhận được dạng mảng bytes ra chuỗi và in ra màn hình
				System.out.println("Received: " + new String(incomingDatagramPacket.getData(), 0, incomingDatagramPacket.getLength()));
				
			}
		} catch (IOException e) {
			// TODO: handle exception
			System.err.print(e);
		} finally {
			if(datagramSocket!=null) {
				datagramSocket.close();
			}
		}
	}
}
