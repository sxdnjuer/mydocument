package com.example.phonecapture;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import android.os.Environment;
import android.util.Log;

public class SavingVideoFile {
	
	private static final String TAG = "SavingVideoFile";
	private static Boolean VidioFile_SWITCH=true; // ��Ƶ�ļ��ܿ���
	private static final long MAX_VIDEOSD_SIZE = 128*1024*1024;//��Ƶ�ļ�������SD���ϲ�����512M
	private static String VIDEOFILE_PATH_DIR;// ��־�ļ���sdcard�е�·��
	private static String VIDEOFILEName = ".h264";// �����������־�ļ�����
	private static SimpleDateFormat videofileName = new SimpleDateFormat("yyyy-MM-dd HHmmss");// ��Ƶ�ļ���ʽ
	public static File vidioFile;
	
	private static RandomAccessFile raf = null;
	
	public SavingVideoFile(){
		//��ʼ��Ŀ¼����
		VIDEOFILE_PATH_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator   
                + "VideoRecordFile";//create Dir Path
		//�����ļ�
		createVidoFile();
		removeVideoFile(VIDEOFILE_PATH_DIR);
	}
	
	/**
	 * ������Ƶ�ļ�
	 */
	private boolean createVidoFile() {
 		
		Date nowtime = new Date();
		String needWriteFiel = videofileName.format(nowtime);
		try{
			vidioFile = new File(VIDEOFILE_PATH_DIR, "SD"+needWriteFiel
				+ VIDEOFILEName);
			File parent = vidioFile.getParentFile();
			if (!parent.isDirectory()) {
				Log.d(TAG, "DIR is not exist!");
				if (!parent.mkdirs()) {
				parent.mkdirs();
				}
				Log.d(TAG, "create dir success!");
				}
			Log.d(TAG, vidioFile.getAbsolutePath());
			raf = new RandomAccessFile(vidioFile, "rw");
			}catch (Exception ex) {
				Log.v("System.out", ex.toString());
				}	
		return true;
		}
	/**
	 * ������Ƶ�ļ�
	 * @throws IOException 
	 */
	public  void write(byte [] buffer){
		if(VidioFile_SWITCH){
			try {
				raf.write(buffer);
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					Log.d(TAG,String.format("Thread breaking down os error!"+e1.getLocalizedMessage())); 
					}
			}
		}
	
	public  void write(byte [] buffer,int  byteOffset,int byteCount){
		if(VidioFile_SWITCH){
			try {
				raf.write(buffer, byteOffset, byteCount);
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				Log.d(TAG,String.format("Thread breaking down os error!"+e1.getLocalizedMessage())); 
				}
		}
	}
	/**
	 * �ر���Ƶ�ļ�RECLOG_SWITCH����
	 */
	public boolean turnOffRECLOG_SWITCH(){
		VidioFile_SWITCH = false;
		return true;
	}
	/**
	 * ����Ƶ��RECLOG_SWITCH����
	 */
	public boolean turnOnRECLOG_SWITCH(){
		VidioFile_SWITCH = true;
		return true;
	}
	
	
	 /**
     *����洢Ŀ¼�µ���Ƶ�ļ���С�����ļ��ܴ�С���ڹ涨��MAX_VIDEOSD_SIZE�Ĺ涨
     * ��ôɾ��40%���û�б�ʹ�õ��ļ�
     * @param dirPath
     */
    private void removeVideoFile(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        int dirSize = 0;
        for (int i = 0; i < files.length;i++) {
            if(files[i].getName().contains(VIDEOFILEName)) {
                dirSize += files[i].length();
            }
        }
        if (dirSize > MAX_VIDEOSD_SIZE ) {
            int removeFactor = (int) ((0.4 *files.length) + 1);

            Arrays.sort(files, new FileLastModifSort());

            Log.i(TAG, "Clear some expiredcache files ");

            for (int i = 0; i <removeFactor; i++) {

                if(files[i].getName().contains(VIDEOFILEName)) {

                    files[i].delete();             

                }

            }

        }

    }

   
    /**
     * TODO �����ļ�������޸�ʱ��������� *
     */
    class FileLastModifSort implements Comparator<File>{
        public int compare(File arg0, File arg1) {
            if (arg0.lastModified() >arg1.lastModified()) {
                return 1;
            } else if (arg0.lastModified() == arg1.lastModified()) {
                return 0;
            } else {
                return -1;
            }
        }
    }

}
