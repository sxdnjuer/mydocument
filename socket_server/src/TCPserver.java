import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class TCPserver {
	private static SimpleDateFormat videofileName = new SimpleDateFormat(
			"yyyy-MM-dd_HHmmss");
	private static String VIDEOFILEName = ".jpg";

	@SuppressWarnings("resource")
	public void go() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(1558);
		} catch (IOException e1) {
			// TODO Auto-generated actch block
			e1.printStackTrace();
		}
		while (true) {
			try {
				Date nowtime = new Date();
				String needWriteFiel = videofileName.format(nowtime);
				String path = "D://aspÐéÄâÄ¿Â¼//picture//" + needWriteFiel
						+ VIDEOFILEName;
				System.out.println("¿ªÊ¼¼àÌý¡£¡£¡£");
				Socket socket = serverSocket.accept();
				BufferedInputStream inputStream = new BufferedInputStream(
						socket.getInputStream());
				BufferedOutputStream fout = new BufferedOutputStream(
						new FileOutputStream(path));
				byte[] buffer = new byte[1024];
				int temp = 0;
				int count = 0;
				while ((temp = inputStream.read(buffer)) != -1) {
					// temp = inputStream.read(buffer);
					String end = new String(buffer, "utf-8");
					if (end.contains("send is over!")) {
						byte[] str = Arrays.copyOf(buffer, temp - 13);
						fout.write(str, 0, str.length);
						System.out.println("temp=" + str.length);
						break;
					}
					fout.write(buffer, 0, temp);
					System.out.println("temp=" + temp);
					// System.out.println(new String(buffer,0,temp));
					fout.flush();
				}
				
				DataOutputStream outStream = new DataOutputStream(
						socket.getOutputStream());
				outStream.writeUTF(needWriteFiel+VIDEOFILEName);
				System.out.println("path=" + path);
				outStream.flush();				
				
				System.out.println("count=" + count);
				// outStream.flush();
				inputStream.close();
				// System.out.println("path="+path);
				// fout.flush();
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
//			try {
//				serverSocket.close();
//				System.out.println("¼àÌý½áÊø¡£¡£¡£");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

	public static void main(String[] args) {
		TCPserver server = new TCPserver();
		server.go();
		// Thread thread = new sendthread();
		// thread.start();
	}
}
