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
	private static Boolean VidioFile_SWITCH=true; // 视频文件总开关
	private static final long MAX_VIDEOSD_SIZE = 128*1024*1024;//视频文件存在于SD卡上不大于512M
	private static String VIDEOFILE_PATH_DIR;// 日志文件在sdcard中的路径
	private static String VIDEOFILEName = ".h264";// 本类输出的日志文件名称
	private static SimpleDateFormat videofileName = new SimpleDateFormat("yyyy-MM-dd HHmmss");// 视频文件格式
	public static File vidioFile;
	
	private static RandomAccessFile raf = null;
	
	public SavingVideoFile(){
		//初始化目录名称
		VIDEOFILE_PATH_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator   
                + "VideoRecordFile";//create Dir Path
		//创建文件
		createVidoFile();
		removeVideoFile(VIDEOFILE_PATH_DIR);
	}
	
	/**
	 * 创建视频文件
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
	 * 保存视频文件
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
	 * 关闭视频文件RECLOG_SWITCH开关
	 */
	public boolean turnOffRECLOG_SWITCH(){
		VidioFile_SWITCH = false;
		return true;
	}
	/**
	 * 打开视频文RECLOG_SWITCH开关
	 */
	public boolean turnOnRECLOG_SWITCH(){
		VidioFile_SWITCH = true;
		return true;
	}
	
	
	 /**
     *计算存储目录下的视频文件大小，当文件总大小大于规定的MAX_VIDEOSD_SIZE的规定
     * 那么删除40%最近没有被使用的文件
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
     * TODO 根据文件的最后修改时间进行排序 *
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
