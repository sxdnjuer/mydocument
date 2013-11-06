import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCP {

	private static SimpleDateFormat videofileName = new SimpleDateFormat(
			"yyyy-MM-dd_HHmmss");
	private static String VIDEOFILEName = ".jpg";

	@SuppressWarnings("resource")
	public void go() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(1558);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
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
				while ((count != 1) &&(temp = inputStream.read(buffer)) != -1) {
					// temp = inputStream.read(buffer);
					fout.write(buffer, 0, temp);
					System.out.println("temp=" + temp);
					// System.out.println(new String(buffer,0,temp));
					if (temp < 1) {
						count = 1;
						System.out.println("count=" + count);
					}
					fout.flush();
				}
				System.out.println("count=" + count);

				// DataOutputStream outStream = new DataOutputStream(
				// socket.getOutputStream());
				// outStream.writeUTF(path + "\n");
				// System.out.println("path=" + path);
				// outStream.flush();
				inputStream.close();
				// System.out.println("path="+path);
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// try {
		// serverSocket.close();
		//
		// }
		// catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public static void main(String[] args) {
		TCP server = new TCP();
		server.go();
	}
}
