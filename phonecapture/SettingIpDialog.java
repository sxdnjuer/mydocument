package com.example.phonecapture;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingIpDialog extends Dialog {
	private EditText editText_ip, editText_port, exitText_picip, editText_audioip;
	private Button button_sure;
	private Button button_cancel;

//	private Spinner editAdd_address;
	// ==============================2012-12-21===========================
	private Spinner frameRateSpinner;
	private Spinner resolutionSpinner;

	public OnSureClickListener mListener;

//	private static final String[] address = { "�Ͼ�", "����", "��ͨ", "̩��", "��",
//			"����", "����", "����" };
//	private static final String[] add_Eng = { "nanjing", "suzhou", "nantong",
//			"taizhou", "zhenjiang", "yangzhou", "wuxi", "changzhou" };
	private static final String[] strResolutions = { "QCIF", "QVGA", "CIF",
			"D1" };

	private static final int[] frNum = { 6, 10, 15 };

	private ArrayAdapter<String> adapter;
//	private String addr_name;
//	private int addr_index;
	// =======================================================================
	private int recordframerate = 10;
	private String resolutiontrype = "QVGA";

	// =======================================================================
	private ArrayAdapter<CharSequence> frameRates = null;
	private ArrayAdapter<CharSequence> resolutions = null;

	public SettingIpDialog(Context context) {
		super(context);
	}

	public SettingIpDialog(Context context, OnSureClickListener listener) {
		super(context);
		mListener = listener;

//		adapter = new ArrayAdapter<String>(context,
//				android.R.layout.simple_spinner_item, address);
		// adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ============================
		frameRates = ArrayAdapter.createFromResource(context,
				R.array.frameRate, android.R.layout.simple_spinner_item);
		frameRates
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		resolutions = ArrayAdapter.createFromResource(context,
				R.array.resolution, android.R.layout.simple_spinner_item);
		resolutions
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.settingipdialog);
		setView();
	}

	private void setView() {
		button_sure = (Button) findViewById(R.id.button_project_dialog_sure);
		button_cancel = (Button) findViewById(R.id.button_project_dialog_cancel);
		editText_ip = (EditText) findViewById(R.id.liginForm_Ip);
		editText_port = (EditText) findViewById(R.id.liginForm_Port);
		exitText_picip = (EditText) findViewById(R.id.picture_Ip);
		editText_audioip =  (EditText) findViewById(R.id.audio_Ip);
		// ����ļ����¼�����Ϊ����̳�lDialog���DialogInterface,��DialogInterface��Ҳ��OnClickListener�������Ҫ�õ�ȫ��View.OnClickListener

//		editAdd_address = (Spinner) findViewById(R.id.address);

//		editAdd_address.setPrompt("��ѡ��");
//		editAdd_address.setAdapter(adapter);
//		editAdd_address.setOnItemSelectedListener(new OnItemSelectedListener() {
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				addr_name = add_Eng[arg2];
//				addr_index = arg2;
//				// Log.e("address", addr_name);
//
//			}

//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});

		// ================================================
		frameRateSpinner = (Spinner) findViewById(R.id.frameRateSpinner);
		frameRateSpinner.setAdapter(frameRates);
		frameRateSpinner.setSelection(1);
		frameRateSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						recordframerate = frNum[arg2];
						// Log.e("address", addr_name);
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});
		// frameRateSpinner.setAdapter(frameRates);//������ʾ��Ϣ

		resolutionSpinner = (Spinner) findViewById(R.id.resolutionSpinner);
		resolutionSpinner.setAdapter(resolutions);
		resolutionSpinner.setSelection(1);
		resolutionSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						resolutiontrype = strResolutions[arg2];
						// strResolutionTag=strResolutionTags[arg2];
						// Log.e("address", addr_name);
					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

		button_sure.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				WebService webservice=new WebService();
				if(!PhoneCapture.PictureIp.equals("")){	
					webservice.MobileRigister(PhoneCapture.deviceid,0);
				}
				mListener.getPicIp(exitText_picip.getText().toString());
				mListener.getIp(editText_ip.getText().toString());
				mListener.getPort(editText_port.getText().toString());
				mListener.getAudioIp(editText_audioip.getText().toString());
//				mListener.getAddr(addr_name, addr_index);
				// ==========2012-12-21=================
				mListener.getFR(recordframerate);
				mListener.getResolution(resolutiontrype);
				Log.e("picip",PhoneCapture.PictureIp+"");
				
				webservice.MobileRigister(PhoneCapture.deviceid,1);
//				Log.e("phc.deid",PhoneCapture.deviceid+" ");
				dismiss();
				// Log.e("FRsdbs",
				// recordframerate+":resolutiontrype:"+resolutiontrype);

			}
		});
		button_cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// mListener.getText(editText.getText().toString());//��Button�����¼���ʵ����һ����
				dismiss();
			}
		});
	}

	public interface OnSureClickListener {
		void getPicIp(String strPicIp);

		void getIp(String strIp); // ������ȡEditText_IP�����ݵĽӿ�

		void getPort(String strPort);// ������ȡEditText_Port�����ݵĽӿ�
		
	    void getAudioIp(String audioip);// ������ȡEditText_audioip�����ݵĽӿ�
		
//		void getAddr(String strAddr, int addr_index);

		void getFR(int frValue);

		void getResolution(String resolutiontrype);

	}
}