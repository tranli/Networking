package Client_Server_multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver {

	public static final  byte[] BUFFER = new byte[4096];
	
	public static void main(String[] args) {
		MulticastSocket socket = null;
		DatagramPacket inPacket = null;
		
		try {
			//get the address that we are going to connect to.
			InetAddress address = InetAddress.getByName(MulticastSender.GROUP_ADDRESS);
			
			//Create a new Multicast socket
			socket  = new MulticastSocket(MulticastSender.PORT);
			
			//Join the multicast group
			socket.joinGroup(address);
			while (true) {
				//Receive the infor mation and print it.
				inPacket = new DatagramPacket(BUFFER, BUFFER.length);
				socket.receive(inPacket);
				String msgString = new String(BUFFER, 0, inPacket.getLength());
				System.out.println("From "+ inPacket.getAddress()+ "Msg: "+ msgString);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
