import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//Các bước thực hiện
//*Tạo socket và gán số hiệu cổng cho server
//Lắng nghe yêu cẩu nối kết
//Với một yêu cầu nối kết được chấp nhận thực hiện các bước sau:
//*Lấy InputStream và outputStream gawbs với socket của kênh ảo vừa được hình thành
/*Lặp lại công việc sau:
 * Chờ nhận các yêu cầu
 * Phân tích và thực hiện yêu cầu
 * Tạo thông điệp trẩ lời
 * Gửi thông điệp trả lời về client
 * Nếu không còn yêu cầu hoặc client kết thúc, đóng socket và quay lại bước 2
 * */
public class EchoChatSingleServer {

	public final static int SERVER_PORT = 7;
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			System.out.println("Binding to port" + SERVER_PORT + ", please wait...");
			serverSocket = new ServerSocket(SERVER_PORT);
			
			System.out.println("Sever started: "+ serverSocket);
			System.out.println("Waiting for a client ...");
			while (true) {
				try {
					Socket socket  = serverSocket.accept();
					System.out.println("Client accepted: "+ socket);
					
					OutputStream outputStream = socket.getOutputStream();
					InputStream imInputStream = socket.getInputStream();
					int ch = 0;
					while (true) {
						ch = imInputStream.read(); //Receive data from client
						if(ch== -1) {
							break;
						}
						outputStream.write(ch);//send the results to client
					}
					socket.close();
				} catch (IOException e) {
					// TODO: handle exception
					System.out.println("connection Error: "+ e);
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(serverSocket !=null) {
				serverSocket.close();
			}
		}
	}
	
}
