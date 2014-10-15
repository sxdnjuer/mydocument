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
 * ����־�ļ�����ģ��ֿɿؿ��ص���־����
 * 
 * @author cfl
 * @data 2012-12-25
 */
public class RecordLog {
	private static final String TAG = "RecordLog";
	private static final int  MAX_SD_SIZE = 1024*1024;			//SD card����־�ļ��������Ϊ1M
	private static Boolean RECLOG_SWITCH=true; // ��־�ļ��ܿ���
	private static Boolean RECLOG_WRITE_TO_FILE=true;// ��־д���ļ�����
	private static char RECLOG_TYPE='v';// ������־���ͣ�w����ֻ����澯��Ϣ�ȣ�v�������������Ϣ
	private static String RECLOG_PATH_SDCARD_DIR;// ��־�ļ���sdcard�е�·��
//	private static int SDCARD_LOG_FILE_SAVE_DAYS = 7;// sd������־�ļ�����ౣ������
	private static String RECLOGFILEName = "Log.txt";// �����������־�ļ�����
	private static SimpleDateFormat recLogSdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");// ��־�������ʽ
//	private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd");// ��־�ļ���ʽ
	private static SimpleDateFormat logfileName = new SimpleDateFormat("yyyy-MM-dd HHmmss");// ��־�ļ���ʽ
	public static File logFile;
	
	//���캯��
	public RecordLog(){
		//��ʼ��Ŀ¼����
		RECLOG_PATH_SDCARD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator   
                + "videoRecord";//create Dir Path
		//�����ļ�
		
		createLogFile();
		removeLogFile(RECLOG_PATH_SDCARD_DIR);
		
	}

	public  void w(String tag, Object msg) { // ������Ϣ
		log(tag, msg.toString(), 'w');
	}

	public  void e(String tag, Object msg) { // ������Ϣ
		log(tag, msg.toString(), 'e');
	}

	public  void d(String tag, Object msg) {// ������Ϣ
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
	 * ����tag, msg�͵ȼ��������־
	 * 
	 * @param tag
	 * @param msg
	 * @param level
	 * @return void
	 */
	private static void log(String tag, String msg, char level) {
		if (RECLOG_SWITCH) {
			if (level == 'e' && (RECLOG_TYPE ==  'e'|| RECLOG_TYPE == 'v')) { // ���������Ϣ
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
	 * ����־�ļ���д����־
	 * 
	 * @return
	 * **/
	private static void writeLogtoFile(String reclogtype, String tag, String text) {// �½������־�ļ�
		Date nowtime = new Date();
		String needWriteMessage = recLogSdf.format(nowtime) + "    " + reclogtype
				+ "    " + tag + "    " + text;
	
		try {
			FileWriter filerWriter = new FileWriter(logFile, true);//����������������ǲ���Ҫ�����ļ���ԭ�������ݣ������и���
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
	 * �ر���־RECLOG_SWITCH����
	 */
	public boolean turnOffRECLOG_SWITCH(){
		RECLOG_SWITCH = false;
		return true;
	}
	/**
	 * ����־RECLOG_WRITE_TO_FILE����,����־�浵��SD����
	 */
	public boolean turnOnRECLOG_WRITE_TO_FILE(){
		RECLOG_WRITE_TO_FILE = true;
		return true;
	}
	/**
	 * �ر���־RECLOG_WRITE_TO_FILE���أ�������SD���ϴ浵
	 */
	public boolean turnOffRECLOG_WRITE_TO_FILE(){
		RECLOG_WRITE_TO_FILE = false;
		return true;
	}
	/**
	 * ����־RECLOG_SWITCH����
	 */
	public boolean turnOnRECLOG_SWITCH(){
		RECLOG_SWITCH = true;
		return true;
	}
	
	/**
	 * ������־�ļ�
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
					     Log.d(TAG,"�����ļ�" + RECLOG_PATH_SDCARD_DIR+needWriteFiel
									+ RECLOGFILEName +"ʧ�ܣ�");
					}
		    }
		return true;
	}
	

	  /**
     *����洢Ŀ¼�µ��ļ���С�����ļ��ܴ�С���ڹ涨��MAX_SD_SIZE�Ĺ涨
     * ��ôɾ��40%���û�б�ʹ�õ��ļ�
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