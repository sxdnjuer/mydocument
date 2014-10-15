package com.example.phonecapture;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class serverStore {
  public static void main(String[] args){
   try{
	   ServerSocket ss=new ServerSocket(1557);
	   while(true){
		System.out.println("服务器开启。。。");
		Socket re_socket=ss.accept();
		handleConnection(re_socket);
	   }
	  }catch(Exception e){
		 e.printStackTrace(); 
	  }
}
  static void handleConnection(Socket s) throws Exception{
	    String thisDateStr=null;
	    Date thisDate=new Date(0);
		SimpleDateFormat dayFormat=new SimpleDateFormat("MMDD");
		thisDateStr=dayFormat.format(thisDate);
		
	  BufferedInputStream inputStream=null;
		try{
			inputStream=new BufferedInputStream(s.getInputStream());
		}catch(Exception e){
			e.printStackTrace();
		   if(inputStream!=null)
		    inputStream.close();
		    System.out.println("接受消息缓存错误");
			throw e;
		}
		try{
			String savePath="F:\\movies\\";
			int bufSize=1024;
			byte[] buf=new byte[bufSize];
			int passedlen=0;
			long len=0;
			//String passFileName=inputStream.readUTF();   //读取客户端写入的文件名
			//int lastIndex = passFileName.lastIndexOf(".");
			//String exName = passFileName.substring(lastIndex,passFileName.length());
			
			File thePath=new File(savePath+"\\"+thisDateStr); //打开服务端接收路径
			if(!thePath.exists()) thePath.mkdir();
			
			//savePath+=thisDateStr+"\\"+fileName+exName; //声明写入的文件名，后面封装成流，进行写入
			System.out.println("savePath："+savePath);
			DataOutputStream fileOut=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(thePath)));
			//len=inputStream.readLong(); //读取客户端写入的文件长度
			System.out.println("接受的文件长度为："+len);
			System.out.println("开始接受文件。。。"); 
			while(true){
			   int read=0;
			   if(inputStream!=null) 
			      read=inputStream.read(buf);
				passedlen+=read;
				if(read==-1) break;
				System.out.println("文件接受了"+((passedlen*100)/len)+"%");
				fileOut.write(buf,0,read);
			}
			    System.out.println("接受完成，文件保存在："+thePath);
				fileOut.close();
				inputStream.close();  
		}catch(Exception e){
				System.out.println("接收消息错误！");
				System.out.println(e.getMessage());
				return;
		}
  }
}
