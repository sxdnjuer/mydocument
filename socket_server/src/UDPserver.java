
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;




public class UDPserver {
	InetAddress inetaddress=null;	
	DatagramSocket socket=null;
	DatagramSocket socketsend=null;
	Date nowtime = new Date();
	private static String VIDEOFILEName = ".h264";
	private FileChannel fcudp;
	private FileChannel recvudp;
	private static SimpleDateFormat videofileName = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
	String needWriteFiel = videofileName.format(nowtime);
	public void go(){
		try {
			recvudp=new FileOutputStream(needWriteFiel+"recv_udp"+VIDEOFILEName).getChannel();
			fcudp = new FileOutputStream(needWriteFiel+"_udp"+VIDEOFILEName).getChannel();
			socket=new DatagramSocket(1557);
		    socketsend = new DatagramSocket();
			inetaddress=InetAddress.getByName("192.168.0.103");
			while(true){
				byte datasend[]=new byte[1024];
				byte data[]=new byte[1024];
				DatagramPacket packet=new DatagramPacket(data,data.length);
				 //receive packet
				socket.receive(packet);
//				String result=new String(packet.getData(),0,packet.getLength());
				recvudp.write(ByteBuffer.wrap(packet.getData(),0,packet.getLength()));
//				System.out.println("result-->"+result);
//				System.out.println("result-->"+packet.getData());
				//send packet
				datasend=packet.getData();							
				DatagramPacket packetsend= new DatagramPacket(datasend,packet.getLength(), inetaddress, 1556); 
				System.out.println("datasend="+datasend);
				socketsend.send(packetsend);
				fcudp.write(ByteBuffer.wrap(packetsend.getData(),0,packetsend.getLength()));
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			try{
			socket.close();
			socketsend.close();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args){
		UDPserver client=new UDPserver();
		client.go();
	}
}
