import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;





public class tcpserve {
	
	private static SimpleDateFormat videofileName = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
    private static String VIDEOFILEName = ".jpg";
  
	@SuppressWarnings("resource")
	public void go(){
		while(true){
		    ServerSocket serverSocket=null;
			try {
				serverSocket=new ServerSocket(1558);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
      		
			try{ 
				Date nowtime = new Date();
				String needWriteFiel = videofileName.format(nowtime);		
				String path="D://aspÐéÄâÄ¿Â¼//picture//"+needWriteFiel+VIDEOFILEName; 
				System.out.println("¿ªÊ¼¼àÌý¡£¡£¡£");
				Socket socket=serverSocket.accept(); 
				BufferedInputStream inputStream=new BufferedInputStream(socket.getInputStream());
				BufferedOutputStream fout=new BufferedOutputStream(new FileOutputStream(path));
				byte[] buffer=new byte[1024];
				int temp=0;

				while((temp=inputStream.read(buffer))!=-1){
				    fout.write(buffer,0,temp);
					System.out.println("temp="+temp);
					fout.flush();	
				}
				
				
				System.out.println("path="+path);
				
				inputStream.close();
				fout.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}	
			try {
				serverSocket.close();
				
			}
				catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		
	}
	public static void main(String[] args){
		tcpserve server=new tcpserve();
		server.go();
	}
}


