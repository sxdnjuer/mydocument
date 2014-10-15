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
		System.out.println("����������������");
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
		    System.out.println("������Ϣ�������");
			throw e;
		}
		try{
			String savePath="F:\\movies\\";
			int bufSize=1024;
			byte[] buf=new byte[bufSize];
			int passedlen=0;
			long len=0;
			//String passFileName=inputStream.readUTF();   //��ȡ�ͻ���д����ļ���
			//int lastIndex = passFileName.lastIndexOf(".");
			//String exName = passFileName.substring(lastIndex,passFileName.length());
			
			File thePath=new File(savePath+"\\"+thisDateStr); //�򿪷���˽���·��
			if(!thePath.exists()) thePath.mkdir();
			
			//savePath+=thisDateStr+"\\"+fileName+exName; //����д����ļ����������װ����������д��
			System.out.println("savePath��"+savePath);
			DataOutputStream fileOut=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(thePath)));
			//len=inputStream.readLong(); //��ȡ�ͻ���д����ļ�����
			System.out.println("���ܵ��ļ�����Ϊ��"+len);
			System.out.println("��ʼ�����ļ�������"); 
			while(true){
			   int read=0;
			   if(inputStream!=null) 
			      read=inputStream.read(buf);
				passedlen+=read;
				if(read==-1) break;
				System.out.println("�ļ�������"+((passedlen*100)/len)+"%");
				fileOut.write(buf,0,read);
			}
			    System.out.println("������ɣ��ļ������ڣ�"+thePath);
				fileOut.close();
				inputStream.close();  
		}catch(Exception e){
				System.out.println("������Ϣ����");
				System.out.println(e.getMessage());
				return;
		}
  }
}
