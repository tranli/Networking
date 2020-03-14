package Server_UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*Chuowng trinhf EchoServer cài đặt Echo Server ở chế độ không nối kết, cổng mặc định alf 7. Chương trình chờ nhận từng gói tin, lấy dữ liệu ra khỏi gói tin nhận được và gửi dữ liệu đó về Client*/

public class EchoServer {
	public final static int SERVER_PORT = 7; //CỔNG MẶC ĐỊNH CỦA echo server 
	public final static byte[] BUFFER = new byte[4096];//vùng đẹm chứa dữ liệu cho gói tin nhận
	
	public static void main(String[] args) {
		DatagramSocket datagramSocket = null;
		try {
			System.out.println("Binding to port: "+ SERVER_PORT + ", PLEASE WAIT...");
			datagramSocket = new DatagramSocket(SERVER_PORT); //Tạo socket với cổng là 7
			System.out.println("Server started ");
			System.out.println("Waiting for message from client ...");
			
			while (true) {
				//Tạo gói tin nhận
				DatagramPacket incomingDatagramPacket = new DatagramPacket(BUFFER, BUFFER.length);
				datagramSocket.receive(incomingDatagramPacket); //Chờ nhận gói tin gửi đến
				
				//Lấy dữ liệu khỏi gói tin nhận
				String mesageString = new String(incomingDatagramPacket.getData(), 0, incomingDatagramPacket.getLength());
				System.out.println("Reseived: "+ mesageString);
				
				//Tạo gói tin gửi chứa dữ liệu vừa nhận được
				DatagramPacket outsendingDatagramPacket = new DatagramPacket(mesageString.getBytes(),  incomingDatagramPacket.getLength(), incomingDatagramPacket.getAddress(), incomingDatagramPacket.getPort());
				datagramSocket.send(outsendingDatagramPacket);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(datagramSocket !=null) {
				datagramSocket.close();
			}
		}
	}

}
/*
 * Cổng trong giao thức TCP và UDP có thể trùng nhau. Trên cùng một máy tính, bạn có thể gán cổng 20 cho socket dùng giao thức TCP và cổng 20 cho socket sử dụng giao thức UDP.

Một số phương thức cần thiết để xây dựng các chương trình Client-Server sử dụng socket ở chế độ không nối kết:

public DatagramPacket(byte[] b, int n, InternetAddress ia, int port) :  Phương thức này cho phép tạo một DatagramPacket chứa dữ liệu và cả địa chỉ của máy nhận dữ liệu. Phương thức trả về một đối tượng thuộc lớp DatagramePacket.
public DatagramSocket( int port) :  Tạo Socket kiểu không nối kết cho Client với số hiệu cổng được xác định trong tham số (port). Nếu không xác định port, hệ thống tự động gán số hiệu cổng chưa sử dụng cho socket.
public void send(DatagramPacket dp) : Dùng để gởi một DatagramPacket đi.
public synchronized void receive(Datagrampacket dp) : Chờ nhận một DatagramPacket. Quá trình sẽ bị nghẽn cho đến khi có dữ liệu đến.
Các phương thức lấy thông tin trên một DatagramPacket nhận được:

Khi nhận được một DatagramPacket từ một quá trình khác gởi đến, ta có thể lấy thông tin trên DatagramPacket này bằng các phương thức sau:

public synchronized() InternetAddress getAddress() : Địa chỉ máy gởi.
public synchronized() int getPort() : Cổng của quá trình gởi.
public synchronized() byte[] getData() : Dữ liệu từ gói tin.
public synchronized() int getLength() : Chiều dài của dữ liệu trong gói tin.
Các phương thức đặt thông tin cho gói tin gởi:

Trước khi gởi một DatagramPacket đi, ta có thể đặt thông tin trên DatagramPacket này bằng các phương thức sau:

public synchronized() void setAddress(IntermetAddress address) : Đặt địa chỉ máy nhận.
public synchronized() void setPort(int port) : Đặt cổng quá trình nhận.
public synchronized() void setData(byte buffer[]) : Đặt dữ liệu gởi.
public synchronized() void setLength(int len) : Đặt chiều dài dữ liệu gởi.
 * */
