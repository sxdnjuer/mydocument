package com.example.phonecapture;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

public class WebService {
	// SharedPreferences preferences;
	// SharedPreferences.Editor editor;
	// static String webserviceip = PhoneCapture.webip;// ͼƬ���͵ķ�����IP
	// ����Web Service�������ռ�
	final String Tag = "WebService";
	static final String SERVICE_NS = "http://tempuri.org/";
	// ����Web Service�ṩ�����URL
	static String SERVICE_URL = null;
	// static String SERVICE_URL =
	// "http://58.213.123.54:8081/upload_service/upload_service.asmx";
	// "http://210.29.192.25/lbsservice/Service1.asmx";
	// "http://114.212.126.87/zp/upload/upload_service.asmx";
	String webip;
	public int log = 0;
	public int samplelog = 1;// samplelog=0,�����ϴ��ɹ���samplelog=1�������ϴ�ʧ�ܡ�
	public int locationInfo;
	public int insertOldVideoInfo;

	// ����Զ��Web Services

	public String MobileRigister(String Deviceid, int status) {
		// Log.v("webip",webserviceip);
		webip = PhoneCapture.PictureIp;
		Log.e(Tag, "webip" + webip);
		if (webip.equals("114.212.112.29")) {
			Log.e(Tag, "get the SERVICE_URL");
			SERVICE_URL = "http://its.nju.edu.cn/upload_service/upload_service.asmx";
		} else if (webip.equals("58.213.123.54")) {
			SERVICE_URL = "http://58.213.123.54:8081/upload_service/upload_service.asmx";
		} else if (webip.equals("")) {
			log = 2;
			Log.e("MobileRigister:", "log" + log);
			return null;
		}
		Log.e(Tag, "SERVICE_URL" + SERVICE_URL);
		Log.v("MobileRigister:", "Deviceid=" + Deviceid + "   status=" + status);
		// ���õķ���
		String methodName = "insert_device_info";

		// ����HttpTransportSE�������
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL, 2000);
		Log.e("MobileRigister:", "here2");
		ht.debug = true;
		// ʹ��SOAP1.1Э�鴴��Envelop����
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// ʵ����SoapObject����
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		envelope.bodyOut = soapObject;
		// ������.Net�ṩ��Web Service���ֽϺõļ�����
		envelope.dotNet = true;
		// ��������url������
		// HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		// ��������������
		int mystatus = status;
		soapObject.addProperty("device_id", Deviceid);
		soapObject.addProperty("status", mystatus);
		Log.e("MobileRigister:", "here1");
		try {
			// ����Web Service
			ht.call(SERVICE_NS + methodName, envelope);
			log = 0;
			Log.e("MobileRigister:", "here3");
			Object object = envelope.getResponse(); // ������Ϣ
			Log.e("MobileRigister:", "here4");
			if (object != null) {
				// ��ȡ��������Ӧ���ص�SOAP��Ϣ
				// SoapObject result = (SoapObject) envelope.bodyIn;
				// SoapObject detail = (SoapObject)
				// result.getProperty(methodName + "Result");
				// Object object =
				// envelope.getResponse();//webservice�����ַ���ʱ��soapobject�ķ���һֱ�޷��������ô˷�����������
				// return object.toString();
				// ������������Ӧ��SOAP��Ϣ��
				// return detail.getProperty(0).toString();
				Log.e("MobileRigister:", "here5");
				Log.e("MobileRigister:", object.toString());

			}

		} catch (IOException e) {
			log = 1;
			Log.e("failedconnect", "failedconnect to webservice!");
			return "cannot connect web service";

		} catch (XmlPullParserException e) {
			// e.printStackTrace();
		}
		return null;
	}

	public String imageRigister(String Deviceid, float pointx, float pointy,
			String fileurl) {
		Log.v("imageRigister:", "Deviceid=" + Deviceid + "pointx=" + pointx
				+ "pointy=" + pointy + " fileurl=" + fileurl);
		if (SERVICE_URL == null) {
			return null;
		}
		// ���õķ���
		// String methodName = "insert_device_info";
		// String methodName = "insert_user_info";
		String methodName = "insert_image_info";
		// String methodName = "insert_device_info";
		// String methodName = "InsertData";
		// ����HttpTransportSE�������
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL, 2000);
		Log.e("imageRigister:", "here2");
		ht.debug = true;
		// ʹ��SOAP1.1Э�鴴��Envelop����
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// ʵ����SoapObject����
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		envelope.bodyOut = soapObject;
		// ������.Net�ṩ��Web Service���ֽϺõļ�����
		envelope.dotNet = true;
		// ��������url������
		// HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		// ��������������
		String deviceid = Deviceid;
		String file_url = fileurl;
		String point_x = " " + pointx;
		String point_y = " " + pointy;
		// String updatetime = "2013-11-11";
		soapObject.addProperty("device_id", deviceid);
		soapObject.addProperty("pointx", point_x);
		soapObject.addProperty("pointy", point_y);
		soapObject.addProperty("file_url", file_url);
		// soapObject.addProperty("updatetime",updatetime);

		Log.e("imageRigister:", "here1");
		try {
			// ����Web Service
			ht.call(SERVICE_NS + methodName, envelope);
			Log.e("imageRigister:", "here3");
			log = 0;
			Object object = envelope.getResponse(); // ������Ϣ
			Log.e("imageRigister:", "here4");
			if (object != null) {
				// ��ȡ��������Ӧ���ص�SOAP��Ϣ
				// SoapObject result = (SoapObject) envelope.bodyIn;
				// SoapObject detail = (SoapObject)
				// result.getProperty(methodName + "Result");
				// Object object =
				// envelope.getResponse();//webservice�����ַ���ʱ��soapobject�ķ���һֱ�޷��������ô˷�����������
				// return object.toString();
				// ������������Ӧ��SOAP��Ϣ��
				// return detail.getProperty(0).toString();
				Log.e("imageRigister:", "here5");
				Log.e("imageRigister:", object.toString());

			}

		} catch (IOException e) {
			log = 1;
			return "cannot connect web service";
		} catch (XmlPullParserException e) {
			// e.printStackTrace();
		}

		return null;
	}

	public String realvideoinfor(String Deviceid, float pointx, float pointy,
			String rtspinfor, String statusinfo) {
		Log.v("realvideoinfor:", "Deviceid=" + Deviceid + "rtspinfor="
				+ rtspinfor);
		// ���õķ���
		String methodName = "insert_video_info";
		// ����HttpTransportSE�������
		if (SERVICE_URL == null) {
			return null;
		}
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL, 2000);
		Log.e("realvideoinfor:", "here2");
		ht.debug = true;
		// ʹ��SOAP1.1Э�鴴��Envelop����
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// ʵ����SoapObject����
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		envelope.bodyOut = soapObject;
		// ������.Net�ṩ��Web Service���ֽϺõļ�����
		envelope.dotNet = true;
		// ��������url������
		// HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		// ��������������
		String point_x = " " + pointx;
		String point_y = " " + pointy;
		soapObject.addProperty("device_id", Deviceid);
		soapObject.addProperty("pointx", point_x);
		soapObject.addProperty("pointy", point_y);
		soapObject.addProperty("rtsp_url", rtspinfor);
		soapObject.addProperty("status", statusinfo);
		Log.e("realvideoinfor:", "here1");
		try {
			// ����Web Service
			ht.call(SERVICE_NS + methodName, envelope);
			Log.e("realvideoinfor:", "here3");
			log = 0;
			Object object = envelope.getResponse(); // ������Ϣ
			Log.e("realvideoinfor:", "here4");
			if (object != null) {
				// ��ȡ��������Ӧ���ص�SOAP��Ϣ
				// SoapObject result = (SoapObject) envelope.bodyIn;
				// SoapObject detail = (SoapObject)
				// result.getProperty(methodName + "Result");
				// Object object =
				// envelope.getResponse();//webservice�����ַ���ʱ��soapobject�ķ���һֱ�޷��������ô˷�����������
				// return object.toString();
				// ������������Ӧ��SOAP��Ϣ��
				// return detail.getProperty(0).toString();
				Log.e("realvideoinfor:", "here5");
				Log.e("realvideoinfor:", object.toString());

			}

		} catch (IOException e) {
			log = 1;
			return "cannot connect web service";
		} catch (XmlPullParserException e) {
			// e.printStackTrace();
		}

		return null;
	}

	public String samplevalue(String projectname, String sampleid,
			String place, String temperature, String phvalue, String averagepH,
			String number, String describe, float pointx, float pointy,
			String device_id) {
		Log.v("samplevalue:", "place=" + place + "temperature=" + temperature
				+ "phvalue=" + phvalue + " averagepH=" + averagepH);
		if (SERVICE_URL == null) {
			return null;
		}
		// ���õķ���
		// String methodName = "insert_device_info";
		// String methodName = "insert_user_info";
		String methodName = "insert_samble_pH";
		// String methodName = "insert_device_info";
		// String methodName = "InsertData";
		// ����HttpTransportSE�������
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL, 2000);
		Log.e("samplevalue:", "here2");
		ht.debug = true;
		// ʹ��SOAP1.1Э�鴴��Envelop����
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// ʵ����SoapObject����
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		envelope.bodyOut = soapObject;
		// ������.Net�ṩ��Web Service���ֽϺõļ�����
		envelope.dotNet = true;
		// ��������url������
		// HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		// ��������������
		// float temper = Float.valueOf(temperature);
		// float phval = Float.valueOf(phvalue);
		// float avePH = Float.valueOf(averagepH);
		soapObject.addProperty("projectId", projectname);
		soapObject.addProperty("sambleId", sampleid);
		soapObject.addProperty("place", place);
		soapObject.addProperty("sambleNum", number);
		soapObject.addProperty("temperature", temperature);
		soapObject.addProperty("pH", phvalue);
		soapObject.addProperty("waterO2", averagepH);
		soapObject.addProperty("feeling", describe);
		soapObject.addProperty("pointx", pointx + "");
		soapObject.addProperty("pointy", pointy + "");
		soapObject.addProperty("deviceId", device_id);

		Log.e("samplevalue:", "here1");
		try {
			// ����Web Service
			ht.call(SERVICE_NS + methodName, envelope);
			Log.e("samplevalue:", "here3");
			Object object = envelope.getResponse(); // ������Ϣ
			Log.e("samplevalue:", "here4");
			if (object != null) {
				// ��ȡ��������Ӧ���ص�SOAP��Ϣ
				// SoapObject result = (SoapObject) envelope.bodyIn;
				// SoapObject detail = (SoapObject)
				// result.getProperty(methodName + "Result");
				// Object object =
				// envelope.getResponse();//webservice�����ַ���ʱ��soapobject�ķ���һֱ�޷��������ô˷�����������
				// return object.toString();
				// ������������Ӧ��SOAP��Ϣ��
				// return detail.getProperty(0).toString();
				Log.e("samplevalue:", "here5");
				Log.e("samplevalue:", object.toString());
				if ("true".equals(object.toString()))
					samplelog = 0;
			}

		} catch (IOException e) {
			samplelog = 1;
			return "cannot connect web service";
		} catch (XmlPullParserException e) {
			// e.printStackTrace();
		}

		return null;
	}

	public String locationInfo(String Deviceid, double pointX, double pointY,
			String timeOnLocation, String moveStatus) {
		// Log.v("realvideoinfor:", "Deviceid=" + Deviceid +
		// "audiortsp="+audiortsp);
		// ���õķ���
		String methodName = "insertLocationInfo";
		// ����HttpTransportSE�������
		if (SERVICE_URL == null) {
			return null;
		}
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL, 2000);
		Log.e("SERVICE_URL:", SERVICE_URL);
		Log.e("insertLocationInfo:", "here2");
		ht.debug = true;
		// ʹ��SOAP1.1Э�鴴��Envelop����
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// ʵ����SoapObject����
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		envelope.bodyOut = soapObject;
		// ������.Net�ṩ��Web Service���ֽϺõļ�����
		envelope.dotNet = true;
		// ��������url������
		// HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		// ��������������
		// Log.e("Deviceid:", Deviceid);
		// Log.e("pointX:", pointX + "");
		// Log.e("pointY:", pointY + "");
		// Log.e("insertLocationInfo:", timeOnLocation);
		// Log.e("moveStatus:", moveStatus);
		soapObject.addProperty("deviceId", Deviceid);
		soapObject.addProperty("pointX", pointX + "");
		soapObject.addProperty("pointY", pointY + "");
		soapObject.addProperty("timeOnLocation", timeOnLocation);
		soapObject.addProperty("moveStatus", moveStatus);
		// Log.e("insertLocationInfo:", "here1");
		try {
			// ����Web Service
			ht.call(SERVICE_NS + methodName, envelope);
			// Log.e("realaudioinfor:", "here3");
			locationInfo = 0;
			Object object = envelope.getResponse(); // ������Ϣ
			// Log.e("insertLocationInfo:", "here4");
			if (object != null) {
				// ��ȡ��������Ӧ���ص�SOAP��Ϣ
				// SoapObject result = (SoapObject) envelope.bodyIn;
				// SoapObject detail = (SoapObject)
				// result.getProperty(methodName + "Result");
				// Object object =
				// envelope.getResponse();//webservice�����ַ���ʱ��soapobject�ķ���һֱ�޷��������ô˷�����������
				// return object.toString();
				// ������������Ӧ��SOAP��Ϣ��
				// return detail.getProperty(0).toString();
				// Log.e("insertLocationInfo:", "here5");
				// Log.e("insertLocationInfo:", object.toString());

			}

		} catch (IOException e) {
			locationInfo = 1;
			return "cannot connect web service";
		} catch (XmlPullParserException e) {
			// e.printStackTrace();
		}
		return null;
	}

	public Boolean OldVideoInfo(String Deviceid, String videoUrl,
			String videoTime, int volum, int length) {
		// Log.v("realvideoinfor:", "Deviceid=" + Deviceid +
		// "audiortsp="+audiortsp);
		// ���õķ���
		String methodName = "insertOldVideoInfo";
		// ����HttpTransportSE�������
		if (SERVICE_URL == null) {
			return false;
		}
		HttpTransportSE ht = new HttpTransportSE(SERVICE_URL, 2000);
		Log.e("SERVICE_URL:", SERVICE_URL);
		Log.e("insertOldVideoInfo:", "here2");
		ht.debug = true;
		// ʹ��SOAP1.1Э�鴴��Envelop����
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// ʵ����SoapObject����
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		envelope.bodyOut = soapObject;
		// ������.Net�ṩ��Web Service���ֽϺõļ�����
		envelope.dotNet = true;
		// ��������url������
		// HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		// ��������������
		Log.e("deviceId:", Deviceid);
		Log.e("videoUrl:", videoUrl);
		Log.e("videoTime:", videoTime);
		Log.e("videoByte:", volum + "");
		Log.e("videoLength:", length + "");
		soapObject.addProperty("deviceId", Deviceid);
		soapObject.addProperty("videoUrl", videoUrl);
		soapObject.addProperty("videoTime", videoTime);
		soapObject.addProperty("videoByte", volum + "");
		soapObject.addProperty("videoLength", length + "");
		Log.e("insertOldVideoInfo:", "here1");
		try {
			// ����Web Service
			ht.call(SERVICE_NS + methodName, envelope);
			Log.e("insertOldVideoInfo:", "here3");
			insertOldVideoInfo = 0;
			Object object = envelope.getResponse(); // ������Ϣ
			Log.e("insertOldVideoInfo:", "here4");
			if (object != null) {
				// ��ȡ��������Ӧ���ص�SOAP��Ϣ
				// SoapObject result = (SoapObject) envelope.bodyIn;
				// SoapObject detail = (SoapObject)
				// result.getProperty(methodName + "Result");
				// Object object =
				// envelope.getResponse();//webservice�����ַ���ʱ��soapobject�ķ���һֱ�޷��������ô˷�����������
				// return object.toString();
				// ������������Ӧ��SOAP��Ϣ��
				// return detail.getProperty(0).toString();
				Log.e("insertOldVideoInfo:", "here5");
				Log.e("insertOldVideoInfo:", object.toString());

			}

		} catch (Exception e) {
			insertOldVideoInfo = 1;
			return false;
		}
		return true;
	}

}
