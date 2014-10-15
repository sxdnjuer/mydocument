package com.example.phonecapture;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.media.MediaRecorder.OnInfoListener;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ljh.audio.AudioHelper;
import com.ljh.audio.WaitingService;

/*chenxiangjun 2013-08*/
/*version 1.9.4*/
/*latest modified by chenxiangjun 2012 11-26*/

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private SurfaceView surfaceView;
	private MediaRecorder mediaRecorder;
	private Camera mCamera;
	private static final String[] address = { "�Ͼ�", "����", "��ͨ", "̩��", "��",
			"����", "����", "����" };
	private static final String[] add_Eng = { "nanjing", "suzhou", "nantong",
			"taizhou", "zhenjiang", "yangzhou", "wuxi", "changzhou" };

	// =====add 2013-1-21 =========
	private final RecordLog recordLog = new RecordLog();
	private final SavingVideoFile savingVideoFile = new SavingVideoFile();

	CheckBox audioCheck;
	private boolean record;
	File file, videoFile; // ¼���ļ�

	RandomAccessFile raf = null;

	// �ֱ��Ƿ�����IP���˿ڣ�¼��֡�ʺʹ���֡��
	int recordframerate = PhoneCapture.recordframerate;
	private static int refreshinterv = 3;
	String resolutiontrype = PhoneCapture.resolutiontrype;
	String SockstrIp = PhoneCapture.SockstrIp;
	String AudioIp = PhoneCapture.AudioIp;
	String SockstrPort = PhoneCapture.SockstrPort;
	// ��Ƶ���㾭γ��
	private float point_x = PhoneCapture.pointx;
	private float point_y = PhoneCapture.pointy;
	// ���ص��ʶ
	// String SockstrAddr = PhoneCapture.SockstrAddr;
	// int addrIndex=PhoneCapture.addrIndex;

	// ��ʷ���ݣ���¼�ϴ�������Ϣ
	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	Socket socket;
	// OutputStream os;

	// �����׽��֣����ڽ�������
	LocalSocket receiver, sender;
	LocalServerSocket lss; // ����Socket

	// ʹ��NIO�������� 2012-12-18 by chenxiangjun
	// ������socketchannel��selector����
	private Selector selector = null;
	private SocketChannel sc = null;
	private Charset charset = Charset.forName("UTF-8");

	// Button stopButton;
	Button recordButton;
	Button localrecordButton;
	// �߳�
	Thread t, tcount, timeThread;
	// ����ͳ��
	long starttime, currenttime, pretime;
	private int datacount = 0, predata = 0, tcpspeed = 0;

	private int runflag = 0;// runflag״̬�� 0:δ���� 1�������� 2ֹͣ�Ͽ� 3�����ж϶Ͽ� 4¼����Ƶ

	// private boolean updateUI_stop=false;//�жϼ�ʱֹͣ
	long currentTimeMillis;

	private int lostdatacount = 0;// ����5��Ϊ0���ش�
	private int reconnectcount = 0;// �ش��Ĵ���,�Ըı�֡�ʷֱ���
	// static int speedcount=0;//�����ٶ�Ϊ0�Ĵ���
	private int speed = 0;// ������س�ʼ�ٶ�
	private int total = 0, pretotal = 0;// ����������
	// ��ȡ��Ƶ¼�ƿ�ʼʱ��
	private SimpleDateFormat dateformat;
	private SimpleDateFormat datetime;
	private String videotime;// ��ʼ¼��ʱ��
	// private String Oldvideourl;
	private String videoname;
	private String recordtime;
	private int totaltime;
	private boolean result = true;
	// �ṩ�߳����޸�Activity�������
	final Handler cwjHandler = new Handler();
	final Runnable mUpdateResults = new Runnable() {
		public void run() {
			// sjj
			System.out.println("runflag=" + runflag);
			if (runflag == 1) // if(!updateUI_stop)
			{
				updateUI();
				System.out.println("ˢ�£�");
			} else if (runflag == 3) {
				System.out.println("�ش���");
				// pause();//���ϴ����ʹ�Сʱ���ش��������������ֻ�����
				// reconnectcount++;
				exit();// ��˵��ϴ����ʹ�Сʱ��ֱ���˳����ͣ�
				showToast("���粻�������볢�������ϴ�");
			}
		}
	};
	WebService webservice;
	private String deid; // �ֻ��ͺ�
	private String moblename = null;
	private TextView min_count, sec_count;

	// ////////********2013.9.25*********/////////
	private String mediaurl = null;
	private String statusmedia = null;

	// private String audiourl = null;
	// private String statusaudio = null;
	// ////////********2013.9.25*********/////////

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Log.e("mainactivity log",""+WebService.log);
		// ȫ���쳣����
		// CrashHandler.getInstance().init(getApplicationContext());
		// ��ȡ�ֻ��ͺ�
		webservice = new WebService();
		Build db = new Build();
		moblename = db.MODEL;
		Log.e("moblename", moblename);
		min_count = (TextView) findViewById(R.id.mymins);
		sec_count = (TextView) findViewById(R.id.mysecs);

		surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);
		/* ��������Surface��ά���Լ��Ļ����������ǵȴ���Ļ����Ⱦ���潫�������͵��û���ǰ */
		this.surfaceView.getHolder().setType(
				SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		this.surfaceView.getHolder().setFixedSize(320, 240);// ���÷ֱ���
		this.surfaceView.getHolder().setKeepScreenOn(true);// ���ø��������Ļ�����Զ��ر�
		// ��ȡSocket IP Port��������Ϣ
		preferences = getSharedPreferences("cxj", MODE_WORLD_READABLE);
		editor = preferences.edit();

		SockstrIp = preferences.getString("Ip", "");
		TextView tvIp = (TextView) this.findViewById(R.id.textView_ip);
		tvIp.setText(SockstrIp);
		// ��Ƶ�ӿ�IP����ͳһ
		AudioIp = preferences.getString("audioip", "");
		AudioHelper.setIp(AudioIp);

		deid = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
				.getDeviceId();
		// ����һ�δ�¼����Ƶ����û�ɹ����������´�һ��
		if (preferences.getBoolean("result", true) == false) {
			boolean oldvideoresult = webservice.OldVideoInfo(deid,
					preferences.getString("mediaurl", null),
					preferences.getString("recordtime", null),
					preferences.getInt("datacount", 0),
					preferences.getInt("totaltime", 0));
			editor.putBoolean("result", oldvideoresult);
			editor.commit();
		}
		SockstrPort = preferences.getString("Port", "1559");
		TextView tvPort = (TextView) this.findViewById(R.id.textView_port);
		tvPort.setText(SockstrPort);
		TextView device_id = (TextView) this.findViewById(R.id.device_id);
		device_id.setText(deid);
		localrecordButton = (Button) this.findViewById(R.id.localrecord);
		// stopButton = (Button) this.findViewById(R.id.stop);
		recordButton = (Button) this.findViewById(R.id.record);
		audioCheck = (CheckBox) this.findViewById(R.id.audioCheck);
		// stopButton.setEnabled(false);
		// EditText t_count = (EditText) findViewById(R.id.textView_Count);
		final ToggleButton tButton = (ToggleButton) this
				.findViewById(R.id.toggleButton1);

		// SockstrAddr = preferences.getString("Addr", "");
		// TextView tvAddr = (TextView) this.findViewById(R.id.textView_addr);
		// Log.d("address", SockstrAddr);
		// String str = null;
		// for (int i = 0; i < address.length; i++) {
		// if (SockstrAddr.equals(add_Eng[i])) {
		// str = address[i];
		// break;
		// }
		// }
		// tvAddr.setText(str);

		// ==================================
		recordframerate = preferences.getInt("recordframerate", 10);
		TextView tvRF = (TextView) this.findViewById(R.id.textView_RF);
		tvRF.setText(recordframerate + "");

		resolutiontrype = preferences.getString("resolution", "QVGA");
		TextView tvResolution = (TextView) this
				.findViewById(R.id.textView_resolution);
		tvResolution.setText(resolutiontrype);
		//
		// cwjHandler.post(mUpdateResults);
		// stopButton.setEnabled(false);
		recordButton.setEnabled(true);
		// ��ʼ��dataformat
		dateformat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		videotime = dateformat.format(new Date());
		recordtime = datetime.format(new Date());

		videoname = deid + "_" + videotime;
		// Oldvideourl = "rtsp://" + SockstrIp + ":1556/"+videoname+".264";
		mediaurl = "rtsp://" + SockstrIp + ":1556/" + videoname + ".264";
		statusmedia = "off";
		webservice
				.realvideoinfor(deid, point_x, point_y, mediaurl, statusmedia);
		Log.e(TAG, "point_x" + point_x + "point_y" + point_y);
		// ���öԻ���
		// configButton.setOnClickListener(new OnClickListener()
		// {
		// public void onClick(View v)
		// {
		// OnSureClickListener listener1 = new OnSureClickListener(){
		// public void getIp(String strIp)
		// {
		// SockstrIp=strIp;
		// }
		//
		// public void getPort(String strPort) {
		// SockstrPort=strPort;
		// }
		// public void getAddr(String strAddr,int addr_index) {
		// SockstrAddr=strAddr;
		// addrIndex=addr_index;
		// }
		// public void getFR(int frValue){
		// recordframerate = frValue;
		// }
		//
		// public void getResolution(String strResolution){
		// resolutiontrype = strResolution;
		// saveData(SockstrIp,
		// SockstrPort,SockstrAddr,addrIndex,recordframerate,resolutiontrype);//
		// ��Ip/port/addr�洢��editor�У���ˢ��textview
		//
		// }
		// };
		//
		//
		// SettingIpDialog d1= new
		// SettingIpDialog(com.example.phonecapture.MainActivity.this,listener1);
		// d1.show();
		//
		// }
		// });
		// ********�������Ƶ2013-11-04*********//
		Intent intent = new Intent();
		intent.setClass(this, WaitingService.class);
		this.startService(intent);
		Log.i("wait", "Service Start from A");
		Log.i("Server", String.valueOf(AudioTrack.getMinBufferSize(
				AudioHelper.sampleRateInHz, AudioHelper.outchannelConfig,
				AudioHelper.audioFormat)));
		audioCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked == true) {
					try {
						AudioHelper.init();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					AudioHelper.setDeid(deid);
					AudioHelper.connect();
					// deid = ((TelephonyManager)
					// getSystemService(TELEPHONY_SERVICE))
					// .getDeviceId();
					// webservice = new WebService();
					// audiourl = SockstrIp;
					// statusaudio = "on";
					// webservice.realaudioinfor(deid,audiourl,statusaudio);
				} else {
					AudioHelper.stop();
					// webservice = new WebService();
					// audiourl = SockstrIp;
					// statusaudio = "off";
					// webservice.realaudioinfor(deid,audiourl,statusaudio);
				}
			}
		});
		// ********�������Ƶ2013-11-04*********//
		localrecordButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					showToast("�����SD����");
					return;
				}
				runflag = 4;
				if (initiCamera()) {
					showToast("����ʼ�����...");
				} else {
					showToast("����ʼ�����ʧ�ܣ���");
					return;
				}
				localrecordButton.setEnabled(false);
				// stopButton.setEnabled(true);
				recordButton.setEnabled(false);
			}
		});
		recordButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ���SD��
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					showToast("�����SD����");
					return;
				}
				if (runflag == 4) {
					runflag = 0;
				}
				if (runflag >= 1) {
					reConecttion();
					showToast("�ش�...");
				}
				// ��ʼ������Socket
				else {
					cwjHandler.post(mUpdateResults);
					localrecordButton.setEnabled(false);
					// stopButton.setEnabled(true);
					recordButton.setEnabled(false);
					if (!initiReceiver()) {
						showToast("���ػ����ʼ������������������ֻ�...");
						return;
					}
					// ;
					// showToast("����ʼ���ػ���...");
					// iniSocket();
					if (initiCamera()) {
						showToast("����ʼ�����...");
					} else {
						showToast("����ʼ�����ʧ�ܣ���");
						return;
					}
					iniSocket();
					/*
					 * if(!iniSocket()) {
					 * showToast("����ʼ����Socketʧ��:"+SockstrIp+":"+SockstrPort);
					 * return; } else
					 * showToast("����ʼ����Socke..."+SockstrIp+":"+SockstrPort);
					 */
					runflag = 1;
					startVideoRecording();
					showToast("starting recording...");
					startCounting();
					// ////*****sxd2013.12.26����Ƶ��ַ���͵�webservice****/////
					// deid = ((TelephonyManager)
					// getSystemService(TELEPHONY_SERVICE))
					// .getDeviceId();
					// webservice = new WebService();
					//
					// mediaurl = "rtsp://" + SockstrIp + ":1556/" + deid
					// + ".264";
					writeoldvideoinfo();
					statusmedia = "on";
					webservice.realvideoinfor(deid, point_x, point_y, mediaurl,
							statusmedia);

					Log.e("MyTag", "mediaurl = " + mediaurl);
					// ////*****sxd2013.12.26����Ƶ��ַ���͵�webservice****/////
				}
			}
		});
		// stopButton.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// if (runflag == 4) {
		// releaseMediaRecorder();
		// } else {
		// runflag = 2;
		// pause();
		// }
		// }
		// });

		tButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ////*****sxd2013.12.26����Ƶ��ַ���͵�webservice****/////
				// deid = ((TelephonyManager)
				// getSystemService(TELEPHONY_SERVICE))
				// .getDeviceId();
				exit();
			}
		});
	}

	// sjj
	long millis = 0;
	int covert_sec, covert_min;
	private boolean count_flag = false;// ��û��ʼ¼�Ƽ�ʱǰ�����и���UI�����������ʱ����millis��Ϊ��
	private long record_time = 0;

	protected void updateUI() {
		// TODO Auto-generated method stub
		// �޸�UI
		// runflag 0:δ���� 1�������� 2ֹͣ�Ͽ� 3�����ж϶Ͽ�
		// sjj
		if (!count_flag) {
			sec_count.setText("00");
			min_count.setText("00:");
		} else {
			long nowTimeMillis = System.currentTimeMillis();
			// Log.d("count time...",""+nowTimeMillis);
			millis = nowTimeMillis - currentTimeMillis + record_time;
			// Log.d("msg", "millis="+millis);
			covert_sec = (int) (millis / 1000);
			// Log.d("msg", "covert_sec="+covert_sec);
			if (covert_sec < 10)
				sec_count.setText("0" + covert_sec);
			else if (10 <= covert_sec && covert_sec < 60)
				sec_count.setText("" + covert_sec);
			else {
				covert_min = (int) (covert_sec / 60);
				// Log.d("msg", "covert_min="+covert_min);
				covert_sec = covert_sec % 60;
				// Log.d("msg", "covert_sec22="+covert_sec);
				if (covert_sec < 10)
					sec_count.setText("0" + covert_sec);
				else
					sec_count.setText("" + covert_sec);
				if (covert_min < 10)
					min_count.setText("0" + covert_min + ":");
				else
					min_count.setText("" + covert_min + ":");
			}
		}
		TextView tvSpeed = (TextView) this.findViewById(R.id.textView_Count);
		TextView data_total = (TextView) this.findViewById(R.id.data_total);
		TextView scspeed = (TextView) this.findViewById(R.id.textView_speed);
		tvSpeed.setText("�ɼ����ʣ�" + tcpspeed + " KB");
		// sxd
		scspeed.setText("�ϴ����ʣ�" + speed + "KB");
		// sjj
		data_total.setText(datacount + " kB");
	}

	// ��Ip/port�洢��editor�У���ˢ��textview
	// public void saveData(String Ip,String Port,String Addr,int addr_index,int
	// recordframerate,String Resolution)
	// {
	//
	// editor.putString("Ip",Ip);
	// TextView tvIp=(TextView) this.findViewById(R.id.);
	// tvIp.setText(Ip);
	// editor.putString("Port",Port);
	// TextView tvPort=(TextView) this.findViewById(R.id.textView_port);
	// tvPort.setText(Port);
	//
	// editor.putString("Addr",Addr);
	// TextView tvAddr=(TextView) this.findViewById(R.id.textView_addr);
	// Addr=address[addr_index];
	// tvAddr.setText(Addr);
	// //==========================2012-12-21 =============
	// editor.putInt("recordframerate", recordframerate);
	// TextView tvFR=(TextView) this.findViewById(R.id.textView_RF);
	// tvFR.setText(recordframerate+"");
	//
	// editor.putString("resolution",Resolution);
	// TextView tvResolution=(TextView)
	// this.findViewById(R.id.textView_resolution);
	// tvResolution.setText(Resolution);
	//
	// editor.commit();
	//
	// }

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// Log.d(TAG, "surfaceChanged");
		recordLog.d(TAG, "surfaceChanged");
	}

	/**
	 * ��ʼ��Socket
	 * 
	 * @author chen xiangjun
	 */

	private void iniSocket()// ��ʼ�������׽���
	{
		// boolean flag=false;
		(new Thread() {
			public void run() {
				try {
					// ����selectorʵ��
					if (runflag != 2) {
						selector = Selector.open();
					}
					// try {
					InetSocketAddress isa = new InetSocketAddress(SockstrIp,
							Integer.parseInt(SockstrPort));
					// ����OPEn��̬�����������ӵ�ָ��������socketchannel
					sc = SocketChannel.open(isa);
					//
					// } catch (IOException e) {
					// Log.e("connection failed", "������Ƶ������ʧ��");
					// recordLog.v("connection failed:", e.getMessage());
					// // return flag;
					// }

					// ����SCΪ��������ʽ����
					sc.configureBlocking(false);
					// ��sockchannel����ע_�ᵽָ��selector
					// selector.select(1000);
					sc.register(selector, SelectionKey.OP_READ);

					// sc.write(charset.encode(SockstrAddr));// ���͹۲��
					// sc.write(charset.encode("nj"));
					
					sc.write(charset.encode(videoname));// �����豸��
					// Log.e(TAG,charset.encode(deid).toString());
					recordLog.v("connection info:", "OK!");
					// flag=true;

				} catch (IOException e) {
					// Log.v("connection error:",e.getMessage().toString());

					Log.e("connection failed", "������Ƶ������ʧ��");
					recordLog.v("connection error:", e.getMessage().toString());
					exit();
					// flag=false;
				}
			}
		}).start();
		// return flag;
	}

	private boolean initiCamera() {

		if (mediaRecorder != null)
			mediaRecorder = null;
		boolean flag = false;
		mediaRecorder = new MediaRecorder();
		// /////******sxd2013-11-16******//////////
		// if(moblename.equals("HUAWEI P6-C00")||moblename.equals("SM-N9009"))
		if (moblename.equals("HUAWEI P6-C00")) {

			mCamera = getCameraInstance();
			mCamera.unlock();
			mediaRecorder.setCamera(mCamera);
		}
		// /////******sxd2013-11-16******//////////
		try {

			mediaRecorder.reset();
			// mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
			mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			// mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264); // ������Ƶ���뷽ʽ
			// ���ݲ�����Ʒֱ���
			if (reconnectcount >= 4) {
				if (!moblename.equals("SM-N9009")) {
					mediaRecorder.setVideoFrameRate(6);
				}
				resolutiontrype = "QCIF";
				mediaRecorder.setVideoSize(176, 144);
				System.out.println("change the videosize and videoframerate!");
			} else {
				if (!moblename.equals("SM-N9009")) {
					mediaRecorder.setVideoFrameRate(recordframerate);
				}
				// mediaRecorder.setVideoFrameRate(4);
				if (resolutiontrype.equals("QCIF"))
					mediaRecorder.setVideoSize(176, 144);
				else if (resolutiontrype.equals("QVGA"))
					mediaRecorder.setVideoSize(320, 240);
				else if (resolutiontrype.equals("CIF"))
					mediaRecorder.setVideoSize(352, 288);
				else if (resolutiontrype.equals("D1"))
					mediaRecorder.setVideoSize(720, 576);
			}

			mediaRecorder.setPreviewDisplay(surfaceView.getHolder()
					.getSurface());
			mediaRecorder.setMaxDuration(0);
			mediaRecorder.setMaxFileSize(0);
			if (runflag == 4) {
				Log.e("TAG", "����ͷ��ʼ��1");
				mediaRecorder.setOutputFile("/mnt/sdcard/test.3gp");
				Log.e("TAG", "����ͷ��ʼ��2");
				mediaRecorder.prepare();
				Log.e("TAG", "����ͷ��ʼ��3");
				mediaRecorder.start();
				record = true;
			} else {
				mediaRecorder.setOutputFile(sender.getFileDescriptor());
			}
			flag = true;

		} catch (Exception e) {
			recordLog.v("cameror error:", e.getMessage().toString());
			showToast("����ͷ��ʼ����������������ֻ�:" + e.getMessage().toString());
			exit();
		}
		return flag;
	}

	private boolean initiReceiver() {

		if (receiver != null)
			receiver = null;
		if (sender != null)
			sender = null;
		boolean flag = false;
		// ��ʼ�������׽���
		receiver = new LocalSocket();
		try {
			LocalServerSocket lss = new LocalServerSocket("VideoCamera");
			receiver.connect(new LocalSocketAddress("VideoCamera"));
			receiver.setReceiveBufferSize(50000);
			receiver.setSendBufferSize(50000);
			sender = lss.accept();
			sender.setReceiveBufferSize(50000);
			sender.setSendBufferSize(50000);

			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			showToast("initiReceiver error:" + e.getMessage());
			flag = false;
		}
		return flag;
	}

	private void startCounting() {
		// predata=0;datacount=0;
		tcpspeed = 0;
		lostdatacount = 0;
		(tcount = new Thread() {
			@SuppressWarnings("deprecation")
			public void run() {

				currentTimeMillis = System.currentTimeMillis();
				count_flag = true;
				recordLog.d("start time...", currentTimeMillis + "");
				while (true && lostdatacount < 5 && runflag == 1) {
					TrafficMonitoring();// ��������������غ���
					pretotal = total;
					predata = datacount;
					try {
						Thread.currentThread();
						Thread.sleep(refreshinterv * 1000);
					} catch (InterruptedException e1) {

						e1.printStackTrace();
					}
					TrafficMonitoring();
					tcpspeed = (datacount - predata) / refreshinterv;// ��������ͷ�ɼ���Ƶ���ٶ�
					speed = (total - pretotal) / refreshinterv;// ���������������ٶ�
					Log.d(TAG, String.format("H264 speed,%d KB", tcpspeed));
					if (tcpspeed >= 5 * speed) {
						try {
							lostdatacount++;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					cwjHandler.post(mUpdateResults);
				}
				System.out.println("runflag=" + runflag);
				if (runflag != 2) {
					runflag = 3; // �Ͽ���������
					cwjHandler.post(mUpdateResults);
					System.out.println("runflag=" + runflag);
					// tcpspeed=0;
					// updateUI_stop=true;
					// Log.d(TAG,String.format("thread count break!"));
					recordLog.d(TAG, String.format("thread count break!"));
					// Log.d(TAG,String.format("�������Ӵ���,���������ӣ�"));
					recordLog.d(TAG, String.format("�������Ӵ���,���������ӣ�"));
				}
			}
		}).start();

	}

	private void startVideoRecording() {// ��ʼ¼�ƣ�������ݲ�����
		(t = new Thread() {
			@SuppressWarnings("deprecation")
			public void run() {
				byte[] buffer = new byte[1024 * 8];
				int framcount = 0;// cxj 2013218 for samsung frame discard
				// ��ʼ����ͷ
				try {
					mediaRecorder.prepare();
				} catch (IllegalStateException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					mediaRecorder.start();
				} catch (Exception e1) {
					// Log.v("camera started error!", e1.toString());
					recordLog.v("camera started error!", e1.toString());
					return;
				}
				record = true;

				// ByteBuffer ����������
				ByteBuffer buff = ByteBuffer.allocate(1024);

				int num = 0, number = 0;
				int frame_size = 1024;
				InputStream fis = null;
				// Log.d(TAG,String.format("Thread working!"));
				recordLog.d(TAG,
						String.format("StartVideoRecording thread working!"));

				try {
					// fis = receiver.getInputStream();
					fis = receiver.getInputStream();
				} catch (IOException e1) {

					return;
				}
				// showToast("receiver��ȡ����.....");
				try {
					Thread.currentThread();
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				number = 0;
				DataInputStream dis = new DataInputStream(fis);
				GetPara getpa = new GetPara();
				// int mdat=0;
				// int numdat=0;
				// try {
				// while((mdat=dis.readInt())!=1835295092)
				// {
				// numdat=numdat+4;
				// System.out.println("numdat="+numdat);
				// continue;
				// }
				// } catch (IOException e2) {
				// // TODO Auto-generated catch block
				// e2.printStackTrace();
				// }
				// System.out.println("numdat="+numdat);
				// Log.d(TAG,"mobilename:"+moblename);

				// ��ȡ��Ƶ����
				byte[] h264sps = getpa.getsps(moblename, resolutiontrype);
				byte[] h264pps = getpa.getpps(moblename);
				byte[] h264head = { 0, 0, 0, 1 };
				int disread = 0;
				disread = getpa.getdisread(moblename);
				// ��ȥ֡ǰ��Ч�ֽڣ���ͬ�ֻ����Ȳ�ͬ;
				try {
					dis.read(buffer, 0, disread);
					System.out.println(disread);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// д����Ƶ����
				try {
					// os.write(h264head);
					sc.write(ByteBuffer.wrap(h264head));
					savingVideoFile.write(h264head);

					// os.write(h264sps);
					sc.write(ByteBuffer.wrap(h264sps));
					savingVideoFile.write(h264sps);

					// os.write(h264head);
					sc.write(ByteBuffer.wrap(h264head));
					savingVideoFile.write(h264head);

					// os.write(h264pps);
					sc.write(ByteBuffer.wrap(h264pps));
					savingVideoFile.write(h264pps);

				} catch (IOException e) {
					Log.e("write sps and pps", "дsps��ppsʧ��");
					// TODO Auto-generated catch block
					// Log.d(TAG,String.format("Thread breaking down os error!"+e1.getLocalizedMessage()));
				}

				while (true && sc.isConnected() && runflag == 1) {
					try {
						// ��ȡÿ֡�ĳ���
						int h264length = dis.readInt();
						number = 0;
						// if(h264length!=0)

						SimpleDateFormat df = new SimpleDateFormat(
								"MM-dd HH:mm:ss");// �������ڸ�ʽ
						// recordLog.d(
						// "lalala",
						// String.format("H264 head 0 0 0 1"
						// + df.format(new Date(System
						// .currentTimeMillis()))));
						// ���������֡
						if (h264length > 32767 || h264length <= 0) {
							System.out.println("h264length=" + h264length);
							// sc.close();
							// //
							// Log.d(TAG,String.format("H264 discard a frame large then 32767,%s",df.format(new
							// Date(System.currentTimeMillis()))));
							// recordLog.d(TAG,String.format("H264 discard a frame large then 32767,%s",df.format(new
							// Date(System.currentTimeMillis()))));
							continue;
						}
						framcount++;
						// //I֡ǰ����sps/pps
						// if(framcount%30==0)
						// {
						// sc.write(ByteBuffer.wrap(h264head));
						// sc.write(ByteBuffer.wrap(h264sps));
						// sc.write(ByteBuffer.wrap(h264head));
						// sc.write(ByteBuffer.wrap(h264pps));
						// }
						sc.write(ByteBuffer.wrap(h264head));
						savingVideoFile.write(h264head);
						Log.d(TAG,
								String.format("H264 frame %S:%d", df
										.format(new Date(System
												.currentTimeMillis())),
										h264length));
						datacount += ((int) h264length / 1024);
						while (number < h264length) {
							int lost = h264length - number;
							// num =
							// fis.read(buffer,0,frame_size<lost?frame_size:lost);
							num = frame_size < lost ? frame_size : lost;
							byte[] tmpbuffer = new byte[num];
							num = fis.read(tmpbuffer, 0, num);
							number += num;
							buff = ByteBuffer.allocate(num);
							buff = ByteBuffer.wrap(tmpbuffer);
							sc.write(buff); // buff���緢��
							savingVideoFile.write(buff.array(), 0, num);
							buff.clear();
							recordLog
									.d(TAG, String.format("bufferlen:%d", num));
						}
					} catch (IOException e) {
						Log.e("wirte failed", "д����ʧ�ܣ�");
						showToast("����ϲ��Ƶ�޷��������������볢���������ӣ�");
						exit();
						break;
					}
				}
				Log.d(TAG, String.format("Thread recording break!"));
				recordLog.d(TAG, String.format("Thread recording break!"));
				// lostdatacount=5;
			}
		}).start();
	}

	protected void reConecttion() {
		runflag = 1;
		// stopButton.setEnabled(true);
		recordButton.setEnabled(false);
		record_time = millis;
		initiReceiver();
		initiCamera();
		iniSocket();
		startVideoRecording();
		startCounting();
		cwjHandler.post(mUpdateResults);

	}

	public void onPause() {
		super.onPause();
		exit();
	}

	protected void pause() {
		// super.pause();
		// Log.d(TAG,String.format("starting stop!"));
		recordLog.d(TAG, String.format("starting stop!"));
		// showToast("�����������жϣ���������������");
		if (runflag != 3) {
			runflag = 2;
		}
		if (record || mediaRecorder != null) {
			try {
				sc.close();
				// Log.d(TAG,String.format("socketChanel.closed()!"));
				recordLog.d(TAG, String.format("socketChanel.closed()!"));
				releaseMediaRecorder();
				// localrecordButton.setEnabled(true);
				// stopButton.setEnabled(false);
				// recordButton.setEnabled(true);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		if (runflag == 3) {
			showToast("�ش�...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reConecttion();
		}

	}

	private void releaseMediaRecorder() {

		if (mediaRecorder != null) {
			try {
				if (record) {
					localrecordButton.setEnabled(true);
					// stopButton.setEnabled(false);
					recordButton.setEnabled(true);

					mediaRecorder.stop();
					mediaRecorder.setOnErrorListener(null);
					mediaRecorder.setOnInfoListener(null);

					record = false;
					// Log.d(TAG,String.format("mediaRecorder.stop()!"));
				}

				Thread.sleep(100);
				mediaRecorder.reset();
				// Log.d(TAG,String.format("mediaRecorder.reset()!"));
				recordLog.d(TAG, String.format("mediaRecorder.reset()!"));
				mediaRecorder.release();
				// Log.d(TAG,String.format("mediaRecorder.release()!"));
				recordLog.d(TAG, String.format("mediaRecorder.release()!"));

				mediaRecorder = null;
			} catch (Exception e) {
				showToast("ֹͣ����ͷʧ��:" + e.getLocalizedMessage());
			}

		}
	}

	// /////******sxd2013-11-16******//////////
	private void releaseCamera() {
		if (mCamera != null) {
			// Ϊ����Ӧ���ͷ�����ͷ
			// mCamera.lock();
			mCamera.release();
			mCamera = null;
		}
	}

	private Camera getCameraInstance() {
		Camera c = null;

		try {
			// ��ȡCameraʵ��
			c = Camera.open();
		} catch (Exception e) {
			// ����ͷ�����ã�����ռ�û򲻴��ڣ�
		}
		// �������򷵻�null
		return c;
	}

	// /////******sxd2013-11-16******//////////
	// sxd�����������
	private void TrafficMonitoring() {
		List<ApplicationInfo> applications = getPackageManager()
				.getInstalledApplications(
						PackageManager.GET_UNINSTALLED_PACKAGES);
		// ��ȡӦ�ó�����Ϣ
		int uid = 0;
		for (int i = 0; i < applications.size(); i++) {
			ApplicationInfo application = applications.get(i);
			if (application.packageName.equals("com.example.phonecapture")) {
				uid = application.uid;
				// System.out.println("uid:"+uid);
				break;
			}

		}
		total = (int) TrafficStats.getUidTxBytes(uid) / 1024;// ��ѯĳ��Uid������ֵ
	}

	private void exit() {
		totaltime = covert_min * 60 + covert_sec;
		statusmedia = "off";
		webservice
				.realvideoinfor(deid, point_x, point_y, mediaurl, statusmedia);
		if (datacount > 0) {
			result = webservice.OldVideoInfo(deid, mediaurl, recordtime,
					datacount, totaltime);
			if (result == false) {
				writeoldvideoinfo();
			}
		}
		
		Log.e("MyTag","statusmedia="+statusmedia);
		Log.e("MyTag", "mediaurl = " + mediaurl);
		// ////*****sxd2013.12.26����Ƶ��ַ���͵�webservice****/////
		Log.e("killprocess", "killprocess");
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);

	}

	private void writeoldvideoinfo() {
		editor.putString("mediaurl", mediaurl);
		editor.putString("recordtime", recordtime);
		editor.putInt("datacount", datacount);
		editor.putInt("totaltime", totaltime);
		editor.putBoolean("result", result);
		editor.commit();
	}

	/**
	 * ������ʾ��Ϣ
	 * 
	 * @param text
	 */
	private void showToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

}
