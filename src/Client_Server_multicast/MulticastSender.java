package Client_Server_multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * Cài đặt một dịch vụ trên cổng 8888, tại địa chỉ 224.0.0.1.Khi client muốn nhận tin nhắn thì Join Group tại địa chỉ và cổng trên*/

public class MulticastSender {
	
	public static final String GROUP_ADDRESS = "224.0.0.1";
	public static final int PORT = 8888;
	
	public static void main(String[] args) throws InterruptedException {
		DatagramSocket socket = null;
		try {
			//Get the address thet we are going to connect to.
			InetAddress address = InetAddress.getByName(GROUP_ADDRESS);
			
			//Create a new Multicast socket
			socket = new DatagramSocket();
			
			DatagramPacket outDatagramPacket =null;
			long counter = 0;
			while(true) {
				String msgString = "Sent message No. "+ counter;
				counter++;
				outDatagramPacket  = new DatagramPacket(msgString.getBytes(), msgString.getBytes().length, address, PORT);
				socket.send(outDatagramPacket);
				System.out.println("Server sent packet with msg: "+msgString);
				Thread.sleep(1000); //Sleep 1 second before sending the message
			}
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (socket!=null) {
				socket.close();
			}
		}
	}

}
/*
 * Multicast là việc gửi quảng bá (broadcast) nhưng đến một nhóm máy tính ở cùng một địa chỉ cho trước. Địa chỉ multicast là địa chỉ lớp D được xác định trong khoảng 244.0.0.0 đến 239.255.255.255. Địa chỉ 244.0.0.0 là địa chỉ riêng nên không sử dụng được.

Multicast được sử dụng trong game nhiều người chơi, trong những ứng dụng mà đối tượng là nhiều thiết bị hay nhiều máy tính cùng nhận một loại thông tin. Multicast cũng được sử dụng trong giải thuật vạch đường (Routing Protocol), khi các router muốn cập nhật thông tin với nhau.

Java hỗ trợ Multicast thông qua lớp java.net.MulticastSocket. Một Multicast Socket là 1 DatagramSocket (UDP) có khả năng gia nhập (joining) vào một nhóm các máy tính multicast trên mạng. Khi một máy tính nào gửi thông điệp đến nhóm thì tất cả các máy tính trong đó đều nhận được.*/

/*
 *public MulticastSocket(int port): Tạo Socket kiểu Multicast với số hiệu cổng được xác định trong tham số (port).
public void joinGroup(InetAddress group) : Tham gia nhóm Multicast tại địa chỉ xác định.
public void leaveGroup(InetAddress group) : Rời khỏi nhóm Multicast tại địa chỉ xác định.
public void send(DatagramPacket dp) : Dùng để gởi một DatagramPacket đi.
public synchronized void receive(Datagrampacket dp) : Chờ nhận một DatagramPacket. */
