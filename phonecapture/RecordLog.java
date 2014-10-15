package com.example.phonecapture;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import android.os.Environment;
import android.util.Log;


/**
 * 带日志文件输入的，又可控开关的日志调试
 * 
 * @author cfl
 * @data 2012-12-25
 */
public class RecordLog {
	private static final String TAG = "RecordLog";
	private static final int  MAX_SD_SIZE = 1024*1024;			//SD card中日志文件保存最大为1M
	private static Boolean RECLOG_SWITCH=true; // 日志文件总开关
	private static Boolean RECLOG_WRITE_TO_FILE=true;// 日志写入文件开关
	private static char RECLOG_TYPE='v';// 输入日志类型，w代表只输出告警信息等，v代表输出所有信息
	private static String RECLOG_PATH_SDCARD_DIR;// 日志文件在sdcard中的路径
//	private static int SDCARD_LOG_FILE_SAVE_DAYS = 7;// sd卡中日志文件的最多保存天数
	private static String RECLOGFILEName = "Log.txt";// 本类输出的日志文件名称
	private static SimpleDateFormat recLogSdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");// 日志的输出格式
//	private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd");// 日志文件格式
	private static SimpleDateFormat logfileName = new SimpleDateFormat("yyyy-MM-dd HHmmss");// 日志文件格式
	public static File logFile;
	
	//构造函数
	public RecordLog(){
		//初始化目录名称
		RECLOG_PATH_SDCARD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator   
                + "videoRecord";//create Dir Path
		//创建文件
		
		createLogFile();
		removeLogFile(RECLOG_PATH_SDCARD_DIR);
		
	}

	public  void w(String tag, Object msg) { // 警告信息
		log(tag, msg.toString(), 'w');
	}

	public  void e(String tag, Object msg) { // 错误信息
		log(tag, msg.toString(), 'e');
	}

	public  void d(String tag, Object msg) {// 调试信息
		log(tag, msg.toString(), 'd');
	}

	public  void i(String tag, Object msg) {//
		log(tag, msg.toString(), 'i');
	}

	public  void v(String tag, Object msg) {
		log(tag, msg.toString(), 'v');
	}

	public static void w(String tag, String text) {
		log(tag, text, 'w');
	}

	public static void e(String tag, String text) {
		log(tag, text, 'e');
	}

	public  void d(String tag, String text) {
		log(tag, text, 'd');
	}

	public  void i(String tag, String text) {
		log(tag, text, 'i');
	}

	public  void v(String tag, String text) {
		log(tag, text, 'v');
	}
	
	/**
	 * 根据tag, msg和等级，输出日志
	 * 
	 * @param tag
	 * @param msg
	 * @param level
	 * @return void
	 */
	private static void log(String tag, String msg, char level) {
		if (RECLOG_SWITCH) {
			if (level == 'e' && (RECLOG_TYPE ==  'e'|| RECLOG_TYPE == 'v')) { // 输出错误信息
				Log.e(tag, msg);
			} else if (level == 'w' && (RECLOG_TYPE == 'w' || RECLOG_TYPE == 'v')) {
				Log.w(tag, msg);
			} else if (level == 'd' && (RECLOG_TYPE == 'd' || RECLOG_TYPE == 'v')) {
				Log.d(tag, msg);
			} else if (level == 'i' && (RECLOG_TYPE == 'd' || RECLOG_TYPE == 'v')) {
				Log.i(tag, msg);
			} else {
				Log.v(tag, msg);
			}
			if (RECLOG_WRITE_TO_FILE)
				writeLogtoFile(String.valueOf(level), tag, msg);
		}
	}

	/**
	 * 打开日志文件并写入日志
	 * 
	 * @return
	 * **/
	private static void writeLogtoFile(String reclogtype, String tag, String text) {// 新建或打开日志文件
		Date nowtime = new Date();
		String needWriteMessage = recLogSdf.format(nowtime) + "    " + reclogtype
				+ "    " + tag + "    " + text;
	
		try {
			FileWriter filerWriter = new FileWriter(logFile, true);//后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
//			Log.v(TAG,filerWriter.getEncoding());
			BufferedWriter bufWriter = new BufferedWriter(filerWriter);
			bufWriter.write(needWriteMessage + "\r\n");
//			bufWriter.newLine();
			bufWriter.close();
			filerWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭日志RECLOG_SWITCH开关
	 */
	public boolean turnOffRECLOG_SWITCH(){
		RECLOG_SWITCH = false;
		return true;
	}
	/**
	 * 打开日志RECLOG_WRITE_TO_FILE开关,将日志存档与SD卡上
	 */
	public boolean turnOnRECLOG_WRITE_TO_FILE(){
		RECLOG_WRITE_TO_FILE = true;
		return true;
	}
	/**
	 * 关闭日志RECLOG_WRITE_TO_FILE开关，不用在SD卡上存档
	 */
	public boolean turnOffRECLOG_WRITE_TO_FILE(){
		RECLOG_WRITE_TO_FILE = false;
		return true;
	}
	/**
	 * 打开日志RECLOG_SWITCH开关
	 */
	public boolean turnOnRECLOG_SWITCH(){
		RECLOG_SWITCH = true;
		return true;
	}
	
	/**
	 * 创建日志文件
	 */
	private boolean createLogFile() {
 		
		Date nowtime = new Date();
		String needWriteFiel = logfileName.format(nowtime);

		logFile = new File(RECLOG_PATH_SDCARD_DIR, needWriteFiel
				+ RECLOGFILEName);
		File parent = logFile.getParentFile();
		if (!parent.isDirectory()) {
			Log.d(TAG, "DIR is not exist!");
			if (!parent.mkdirs()) {
				parent.mkdirs();
			}
			Log.d(TAG, "create dir success!");
		}
		Log.d(TAG, logFile.getAbsolutePath());
		if(!logFile.exists()){
			try {
				logFile.createNewFile();
			}catch (IOException e) {
						e.printStackTrace();
					     Log.d(TAG,"创建文件" + RECLOG_PATH_SDCARD_DIR+needWriteFiel
									+ RECLOGFILEName +"失败！");
					}
		    }
		return true;
	}
	

	  /**
     *计算存储目录下的文件大小，当文件总大小大于规定的MAX_SD_SIZE的规定
     * 那么删除40%最近没有被使用的文件
     * @param dirPath
     * @param filename
     */
    private void removeLogFile(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        int dirSize = 0;
        for (int i = 0; i < files.length;i++) {
            if(files[i].getName().contains(RECLOGFILEName)) {
                dirSize += files[i].length();
            }
        }
        if (dirSize > MAX_SD_SIZE ) {
            int removeFactor = (int) ((0.4 *files.length) + 1);

            Arrays.sort(files, new FileLastModifSort());

            Log.i(TAG, "Clear some expiredcache files ");

            for (int i = 0; i <removeFactor; i++) {

                if(files[i].getName().contains(RECLOGFILEName)) {

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