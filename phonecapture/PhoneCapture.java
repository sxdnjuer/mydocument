package com.example.phonecapture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.baidu.locget.Bdlocation;
import com.example.phonecapture.SettingIpDialog.OnSureClickListener;

@SuppressLint("NewApi")
public class PhoneCapture extends Activity {
	private Button takePic, video, tabledata, button_confirm;
	private TextView about, config;
	private TextView longitude, latitude, device_id;
	private Button confirm, cancel;
	private EditText projectname, sampleid, place, temperature, PHvalue,
			averagePH, number, describe;
	private ToggleButton tButton;
	private String logTag = "Exception";
	// private String mediaurl = null;
	// private String statusmedia = null;
	static float pointx;
	static float pointy;
	boolean bdflag = true;
	private Bdlocation.MyBinder bdBinder = null;
	private boolean quit;
	final Intent bdIntent = new Intent();
	// /*****更新经纬度2014.3.17*******///
	private Handler handler = new Handler();
	private Runnable mUpdate = new Runnable() {
		public void run() {
			Log.e("PhoneCapture", (float) bdBinder.getlong() + "");
			longitude.setText("经度:" + (float) bdBinder.getlong());
			latitude.setText("纬度:" + (float) bdBinder.getlati());
		}
	};
	// /////////*****sxd2013.6.1**********////////////
	// private static final String[] address = { "南京", "苏州", "南通", "泰州", "镇江",
	// "扬州", "无锡", "常州" };
	// private static final String[] add_Eng = { "nanjing", "suzhou", "nantong",
	// "taizhou", "zhenjiang", "yangzhou", "wuxi", "changzhou" };
	WebService webservice;
	MainActivity mainactivity;
	public static String deviceid;
	// String test;
	// 分别是服务器IP，端口，录制帧率和传输帧率
	static int recordframerate;
	// private static int refreshinterv=3;
	static String resolutiontrype;
	static String PictureIp;
	static String AudioIp;
	static String SockstrIp;
	static String SockstrPort;
	static String webip;
	boolean oldvideoresult;
	// 监测地点标识
	// static String SockstrAddr;
	static int addrIndex;
	// 历史数据：记录上次配置信息
	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	// /////////*****sxd2013.6.1**********////////////

	private ServiceConnection bdcon = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			// TODO Auto-generated method stub
			System.out.println("--Service Connected--");
			// 获取service的onBind方法所返回的MyBind对象
			bdBinder = (Bdlocation.MyBinder) arg1;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			System.out.println("--Service Disconnected--");
		}

	};

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_capture);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		takePic = (Button) findViewById(R.id.take_picture);
		video = (Button) findViewById(R.id.video);
		config = (TextView) findViewById(R.id.config);
		about = (TextView) findViewById(R.id.about);
		tabledata = (Button) findViewById(R.id.tabledata);
		longitude = (TextView) findViewById(R.id.longitude);
		latitude = (TextView) findViewById(R.id.latitude);
		device_id = (TextView) findViewById(R.id.device_id);
		bdIntent.setAction("com.baidu.locget.BD_LOCATION");
		bindService(bdIntent, bdcon, Context.BIND_AUTO_CREATE);// 绑定启动定位服务

		// /////////*****sxd2013.6.1**********////////////
		preferences = getSharedPreferences("cxj", MODE_WORLD_READABLE);
		editor = preferences.edit();
		PictureIp = preferences.getString("picIp", "");

		// --------手机服务开始，将状态值1写入webservice----------//
		deviceid = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
				.getDeviceId();
		device_id.setText(deviceid);
		webservice = new WebService();
		mainactivity = new MainActivity();
		// new Thread() {
		// public void run() {
		webservice.MobileRigister(deviceid, 1);
		// }
		// }.start();
		// test = "today";
		// SockstrIp = preferences.getString("Ip", "");
		// mediaurl = "rtsp://" + SockstrIp + ":1556/" + deid
		// + ".264";
		// statusmedia = "off";
		// webservice.realvideoinfor(deid,mediaurl,statusmedia);
		// 获取经纬度
		if (webservice.log == 1) {
			Toast.makeText(this, "连接webservice失败，请确定网络是否连通", Toast.LENGTH_LONG)
					.show();
		} else if (webservice.log == 2) {
			Toast.makeText(this, "IP地址为空，请配置网络信息", Toast.LENGTH_LONG).show();
		}
		new Thread() {
			public void run() {
				while (quit == false) {

					// handler.post(mUpdate);
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.post(mUpdate);
					// Log.e("PhoneCapture_handle",bdBinder.getlong()+"");
				}
			}
		}.start();
		// --------手机服务开始，将状态值1写入webservice----------//
		// ///////*****sxd2013.6.1**********////////////
		takePic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 得到拍照定位信息，经度及纬度
				try {
					pointx = (float) bdBinder.getlong();
					pointy = (float) bdBinder.getlati();
					Log.v("pointx-pointy:", "pointx=" + pointx + "   pointy="
							+ pointy);
					startActivityForResult(new Intent(
							"android.media.action.IMAGE_CAPTURE"), 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		video.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pointx = (float) bdBinder.getlong();
				pointy = (float) bdBinder.getlati();
				Log.v("pointx-pointy:", "pointx=" + pointx + "   pointy="
						+ pointy);
				Intent intend = new Intent();
				intend.setClass(PhoneCapture.this, MainActivity.class);
				startActivity(intend);
			}

		});
		// /////////*****sxd2013.6.1**********////////////
		config.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// if(!PictureIp.equals("")){
				// webservice.MobileRigister(deviceid,0);
				// }
				OnSureClickListener listener1 = new OnSureClickListener() {
					public void getPicIp(String strPicIp) {
						PictureIp = strPicIp;
					}

					public void getIp(String strIp) {
						SockstrIp = strIp;
					}

					public void getAudioIp(String audioip) {
						AudioIp = audioip;
					}

					public void getPort(String strPort) {
						SockstrPort = strPort;
					}

					// public void getAddr(String strAddr, int addr_index) {
					// SockstrAddr = strAddr;
					// addrIndex = addr_index;
					// }

					public void getFR(int frValue) {
						recordframerate = frValue;
					}

					public void getResolution(String strResolution) {
						resolutiontrype = strResolution;
						saveData(PictureIp, SockstrIp, AudioIp, SockstrPort,
								recordframerate, resolutiontrype);// 将Ip/port/addr存储到editor中，并刷新textview

					}
				};

				SettingIpDialog d1 = new SettingIpDialog(
						com.example.phonecapture.PhoneCapture.this, listener1);
				d1.show();
				Log.e("PhoneCapture-->picip", PhoneCapture.PictureIp + "");
				System.out.println("执行到这一步！");
			}
		});
		about.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater
						.from(PhoneCapture.this);
				View layout = inflater.inflate(R.layout.about_dialog, null);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						PhoneCapture.this);

				builder.setView(layout);
				final AlertDialog alertDialog = builder.create();
				alertDialog.show();
				button_confirm = (Button) layout
						.findViewById(R.id.button_confirm);// 在layout中寻找button_confirm按钮
				button_confirm.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						alertDialog.dismiss();
					}
				});
			}
		});
		tabledata.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater
						.from(PhoneCapture.this);
				View layout = inflater.inflate(R.layout.sample_value, null);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						PhoneCapture.this);

				builder.setView(layout);
				final AlertDialog alertDialog = builder.create();
				alertDialog.show();
				projectname = (EditText) layout.findViewById(R.id.projectname);
				sampleid = (EditText) layout.findViewById(R.id.sample_id);
				place = (EditText) layout.findViewById(R.id.place);
				temperature = (EditText) layout.findViewById(R.id.temperature);
				PHvalue = (EditText) layout.findViewById(R.id.ph_value);
				averagePH = (EditText) layout.findViewById(R.id.average_ph);
				number = (EditText) layout.findViewById(R.id.number);
				describe = (EditText) layout.findViewById(R.id.describe);

				// 显示采样数据
				setsampledata();

				confirm = (Button) layout.findViewById(R.id.confirm);// 在layout中寻找button_confirm按钮
				confirm.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// 保存采样数据
						savesampledata(projectname.getText().toString(),
								sampleid.getText().toString(), place.getText()
										.toString(), temperature.getText()
										.toString(), PHvalue.getText()
										.toString(), averagePH.getText()
										.toString(), number.getText()
										.toString(), describe.getText()
										.toString());

						webservice.samplevalue(
								projectname.getText().toString(), sampleid
										.getText().toString(), place.getText()
										.toString(), temperature.getText()
										.toString(), PHvalue.getText()
										.toString(), averagePH.getText()
										.toString(), number.getText()
										.toString(), describe.getText()
										.toString(),
								(float) bdBinder.getlong(), (float) bdBinder
										.getlati(), deviceid);
						if (webservice.log == 0) {
							Toast.makeText(PhoneCapture.this, "数据上传成功",
									Toast.LENGTH_LONG).show();
						} else if (webservice.log == 1) {
							Toast.makeText(PhoneCapture.this, "数据上传失败",
									Toast.LENGTH_LONG).show();
						}
						Log.e(logTag, "sample data");
						alertDialog.dismiss();
					}
				});
				cancel = (Button) layout.findViewById(R.id.cancel);
				cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						alertDialog.dismiss();
					}
				});
			}
		});
	}

	public void savesampledata(String projectname, String sampleid,
			String place, String temperature, String phvalue, String averagepH,
			String number, String describe) {
		editor.putString("projectname", projectname);
		editor.putString("sampleid", sampleid);
		editor.putString("place", place);
		editor.putString("temperature", temperature);
		editor.putString("phvalue", phvalue);
		editor.putString("averagepH", averagepH);
		editor.putString("number", number);
		editor.putString("describe", describe);
		editor.commit();
	}

	public void setsampledata() {
		projectname.setText(preferences.getString("projectname", "GS0001"));
		sampleid.setText(preferences.getString("sampleid", "1"));
		place.setText(preferences.getString("place", "南京"));
		temperature.setText(preferences.getString("temperature", "30"));
		PHvalue.setText(preferences.getString("phvalue", "6"));
		averagePH.setText(preferences.getString("averagepH", "5"));
		number.setText(preferences.getString("number", "5"));
		describe.setText(preferences.getString("describe", "良好"));

	}

	public void saveData(String picIp, String Ip, String audioip, String Port,
			int recordframerate, String Resolution) {
		editor.putString("picIp", picIp);

		editor.putString("Ip", Ip);
		TextView tvIp = (TextView) this.findViewById(R.id.textView_ip);
		// tvIp.setText(Ip);
		editor.putString("audioip", audioip);

		editor.putString("Port", Port);
		TextView tvPort = (TextView) this.findViewById(R.id.textView_port);
		// tvPort.setText(Port);
		// editor.putString("Addr", Addr);
		// TextView tvAddr = (TextView) this.findViewById(R.id.textView_addr);
		// Addr = address[addr_index];
		// tvAddr.setText(Addr);
		// ==========================2012-12-21 =============
		editor.putInt("recordframerate", recordframerate);
		TextView tvFR = (TextView) this.findViewById(R.id.textView_RF);
		// tvFR.setText(recordframerate+"");

		editor.putString("resolution", Resolution);
		TextView tvResolution = (TextView) this
				.findViewById(R.id.textView_resolution);
		// tvResolution.setText(Resolution);

		editor.commit();

	}

	// /////////*****sxd2013.6.1**********////////////
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			if (requestCode != 0)
				return;
			super.onActivityResult(requestCode, resultCode, data);
			if (resultCode == RESULT_OK) {
				Bundle extras = data.getExtras();
				Bitmap b = (Bitmap) extras.get("data");
				Intent intent = new Intent();
				intent.setClass(this, takePicture.class);
				intent.putExtra("image", b);
				this.startActivity(intent);
			} else if (resultCode == RESULT_CANCELED) {
				Log.d("log", "cancel take picture");
				/*
				 * android.os.Process.killProcess(android.os.Process.myPid());
				 * System.exit(0);
				 */
			}
		} catch (Exception e) {
			// TODO: handle exception
			// Log.v(logTag, e.getMessage());
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			ExitDialog(PhoneCapture.this).show();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private Dialog ExitDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("系统消息");
		builder.setMessage("确定要退出程序吗？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				// 退出程序时，向webservice接口中写入status=0,代表下线
				webservice.MobileRigister(deviceid, 0);
				oldvideoresult = preferences.getBoolean("result", true);
				Log.e("oldvideoresult=", oldvideoresult + "");
				if (oldvideoresult == false) {
					boolean result = webservice.OldVideoInfo(deviceid,
							preferences.getString("mediaurl", null),
							preferences.getString("recordtime", null),
							preferences.getInt("datacount", 0),
							preferences.getInt("totaltime", 0));
					editor.putBoolean("result", result);
					editor.commit();
				}
				unbindService(bdcon);
				finish();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		return builder.create();
	}

	public void onDestroy() {
		super.onDestroy();
		quit = true;
		// unbindService(bdcon);
		System.out.println("Activity is Destroyed!");
	}

}
