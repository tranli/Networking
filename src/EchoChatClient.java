import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoChatClient {
	
	/*Một số phương thức cần thiết để xây dựng các chương trình client sử dụng socket ở chế độ có nối kết:

public Socket(String HostName, int PortNumber) : Phương thức này dùng để nối kết đến một server có tên là HostName, cổng là PortNumber. Nếu nối kết thành công, một kênh ảo sẽ được hình thành giữa Client và Server.
HostName: Địa chỉ IP hoặc tên logic theo dạng tên miền.
PortNumber: có giả trị từ 0 ..65535
public InputStream getInputStream() : Phương thức này trả về InputStream nối với Socket. Chương trình Client dùng InputStream này để nhận dữ liệu từ Server gởi về.
public OutputStream getOutputStream() :  Phương thức này trả về OutputStream nối với Socket. Chương trình Client dùng OutputStream này để gởi dữ liệu cho Server.
public close() :  Phương thức này sẽ đóng Socket lại, giải phóng kênh ảo, xóa nối kết giữa Client và
Server.*/

	public final static String SERVER_IP = "127.0.0.1";
	public final static int SERVER_PORT = 7;
	
	public static void main(String[] args) throws IOException, InterruptedException {
	Socket socket = null;
	try {
		socket = new Socket(SERVER_IP, SERVER_PORT); //connect to server
		System.out.println("Connected: "+socket);
		
		InputStream inputStream = socket.getInputStream();
		OutputStream outputStream = socket.getOutputStream();
		for(int i = '0'; i<='9'; i++) {
			outputStream.write(i);//send each number to server
			int ch = inputStream.read(); //waiting for results from server
			System.out.println((char) ch + " "); //Display the results
			Thread.sleep(200);
		}
		
	}catch (IOException e) {
		System.out.println("Can't connect to server");// TODO: handle exception
	} finally {
		if (socket !=null) {
			socket.close();
		}
	}
	}
}
/*Các bước tổng quát xây dựng một chương trình Client – Server ở chế độ có nối kết như sau:

Mở một socket nối kết đến server đã biết địa chỉ IP (hay tên miền) và số hiệu cổng.
Lấy InputStream và OutputStream gán với Socket.
Tham khảo Protocol của dịch vụ để định dạng đúng dữ liệu trao đổi với Server.
Trao đổi dữ liệu với Server nhờ vào các InputStream và OutputStream.
Đóng Socket trước khi kết thúc chương trình.*/
