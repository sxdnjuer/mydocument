package com.example.phonecapture;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

public class takePicture extends Activity {
	private String logTag = "Exception";
	private Bitmap bm;
	private String path, imgName;
	private ProgressBar progressBar;
	private ListView list;
	private static List<Map<String, Object>> listItem;
	private List<Integer> buttonList;
	private static Map<String, Object> item;
	private MyAdapter adapter;
	private int upsuccess=0;
	// private SimpleAdapter adapter;
	// private static int count=10;
	private float point_x = PhoneCapture.pointx;
	private float point_y = PhoneCapture.pointy;
	WebService webservice;
	String deid;
	String fileurl;
	// //////*****sxd2013.9.17********//////////
	String PictureIp = PhoneCapture.PictureIp;// 图片传送的服务器IP

	SharedPreferences preferencestp;
	SharedPreferences.Editor editortp;
	// //////*****sxd2013.9.17********//////////
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// progressBar.setVisibility(View.VISIBLE);
			int size = msg.getData().getInt("size");
			Log.v("handler File size:", "size=" + size);
			progressBar.setProgress(size);
			float result = (float) progressBar.getProgress()
					/ (float) progressBar.getMax();
			int p = (int) (result * 100);
			// resultVew.setText(p+"%");
			if (progressBar.getProgress() == progressBar.getMax()) {
				Toast.makeText(takePicture.this, R.string.success, 1).show();
				progressBar.setVisibility(View.GONE);
			}
		}
	};

	@SuppressLint("WorldReadableFiles")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.take_picture);
		// //////*****sxd2013.9.17********//////////
		preferencestp = getSharedPreferences("cxj", MODE_WORLD_READABLE);
		editortp = preferencestp.edit();
		PictureIp = preferencestp.getString("picIp", "");
		// //////*****sxd2013.9.17********//////////
		list = (ListView) findViewById(R.id.ListView);
		progressBar = (ProgressBar) findViewById(R.id.progressbar);

		SimpleDateFormat df = new SimpleDateFormat("HH_mm_ss");// 设置日期格式
		imgName = String
				.format(df.format(new Date(System.currentTimeMillis())))
				+ ".jpg";

		listItem = new ArrayList<Map<String, Object>>();
		buttonList=new ArrayList<Integer>();
		// item=new HashMap<String,Object>();
		try {
			Bundle bundle = this.getIntent().getExtras();
			bm = bundle.getParcelable("image");
			createSDCardDir();
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(new File("/sdcard/cardImages/"
							+ imgName)));
			bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
			/*
			 * if(bm.compress(Bitmap.CompressFormat.JPEG, 80, bos))
			 * Toast.makeText(takePicture.this, "保存成功!",
			 * Toast.LENGTH_SHORT).show();
			 */
			bos.flush();
			bos.close();
			// count++;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		List<Map<String, String>> pic_List = getPictures("/sdcard/cardImages/");
		if (pic_List != null) {
			Map<String, String> mp;
			for (int i = 0; i < pic_List.size(); i++) {
				Log.d("pic_List.size():  ",
						"pic_List.size()=" + pic_List.size());
				mp = pic_List.get(i);
				System.out.println(mp);
				Bitmap bm = BitmapFactory.decodeFile(mp.get("img_path"));
				// img.setImageBitmap(bm);
				String bmName = mp.get("img_name");
				Log.d("bmName:  ", "bmName=" + bmName);
				item = new HashMap<String, Object>(); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				item.put("img", bm);
				item.put("name", bmName);
				listItem.add(item);
				buttonList.add(0);

			}
		}
		// adapter=new SimpleAdapter(this,listItem,R.layout.listview,new
		// String[]{"img","name"},new int[]{R.id.showPic,R.id.showText});
		adapter = new MyAdapter(this, listItem, R.layout.listview,
				new String[] { "img", "name" }, new int[] { R.id.showPic,
						R.id.showText });
		// listview不直接接受图片等列表信息，故要进行显示说明
		adapter.setViewBinder(new ViewBinder() {
			public boolean setViewValue(View view, Object data,
					String textRepresentation) {
				if (view instanceof ImageView && data instanceof Bitmap) {
					ImageView iv = (ImageView) view;
					iv.setImageBitmap((Bitmap) data);
					return true;
				} else {
					return false;
				}
			}
		});
		list.setAdapter(adapter);
	}
	public boolean  deleteFile(String file)
    {
	 if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
		 File sdcardDir = Environment.getExternalStorageDirectory();
			String path = sdcardDir.getPath() + "/cardImages/"+file;
			File removefile = new File(path);
			if(removefile.exists()) removefile.delete();
			return true;
	   }
	 return false;
	 
    }
	// 创建SD卡文件夹
	public void createSDCardDir() {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			// 创建一个文件夹对象，赋值为外部存储器的目录
			File sdcardDir = Environment.getExternalStorageDirectory();
			// 得到一个路径，内容是sdcard的文件夹路径和名字
			String path = sdcardDir.getPath() + "/cardImages";
			File path1 = new File(path);
			if (!path1.exists()) {
				path1.mkdirs();
				// setTitle("paht ok,path:"+path);
			}
		} else {
			setTitle("false");
			return;
		}
	}

	// 获取SD卡图片路径
	public List<Map<String, String>> getPictures(final String strPath) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		File file = new File(strPath);
		File[] files = file.listFiles(); // file的文件列表

		if (files == null) {
			return null;
		}

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				try {
					int idx = files[i].getPath().lastIndexOf("."); // 统计碰到第一个.时的前面字符数
					if (idx <= 0)
						continue;
					String suffix = files[i].getPath().substring(idx);// substring(int
																		// statrt)从第start个字节开始的子字符串
					if (suffix.toLowerCase().equals(".jpg")
							|| suffix.toLowerCase().equals(".jpeg")
							|| suffix.toLowerCase().equals(".bmp")
							|| suffix.toLowerCase().equals(".png")
							|| suffix.toLowerCase().equals(".gif")) {
						System.out.println("图片路经： " + files[i].getPath());
						System.out.println("图片名字 ： " + files[i].getName());
						map = new HashMap<String, String>(); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						map.put("img_path", files[i].getPath());
						map.put("img_name", files[i].getName());
						list.add(map);
						System.out.println(list);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("打印加入list的图片内容。。。。。。。");
		System.out.println(list);
		return list;
	}

	private class MyAdapter extends SimpleAdapter {
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return super.getCount();
		}

		int count = 0;
		private LayoutInflater layoutInflater;
		private List<Map<String, Object>> mItemList;

		public MyAdapter(Context context, List<? extends Map<String, ?>> data,
				int resource, String[] from, int[] to) {
			super(context, data, resource, from, to);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return super.getItem(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
 
			
			final int mPosition = position;
			convertView = super.getView(position, convertView, parent);
			final Button upload = (Button) convertView
					.findViewById(R.id.upload);
			TextView text = (TextView) convertView.findViewById(R.id.showText);
			
			//listview的每条Item显示都会调用getView()函数,读出button的状态值，并进行设置，让其显示
			if(buttonList.get(mPosition)==1) upload.setEnabled(false);
			else upload.setEnabled(true);
			
			Log.d("---getView():---"+mPosition,buttonList.get(mPosition)+"");
			
			final String filename = text.getText().toString();
			upload.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Log.d("-getView()-OnClickListener:---",mPosition+"");
					
					Toast.makeText(takePicture.this, "开始上传。。。。", 1).show();
					Log.v("File Length:", "上传开始。。。");
					progressBar.setVisibility(View.VISIBLE);

					if (Environment.getExternalStorageState().equals(
							Environment.MEDIA_MOUNTED)) {
						Log.d("filename...", filename);
						final File uploadFile = new File(
								Environment.getExternalStorageDirectory()
										+ "/cardImages/", filename);
						Log.d("filenameUpdate...", String.valueOf(uploadFile));
						upFile(uploadFile);
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			
						if (upsuccess == 1)
						{	
							deleteFile(filename); //图片上传成功后从SD卡删除
							buttonList.set(mPosition, 1);//按钮点击后状态记为1
							upload.setEnabled(false);//按钮设置为不可用
							upsuccess=0;
						} else {
							upload.setEnabled(true);
							Toast.makeText(takePicture.this,"连接图片服务器失败，请确认IP和网络问题",Toast.LENGTH_LONG).show();
						}
//						
					} else {
						Toast.makeText(takePicture.this, "SDCard Error", 1)
								.show();
					}
				}

			});
			return convertView;
		}
	}

	private void upFile(final File file) {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
				// TODO Auto-generated method stub
				// progressBar.setVisibility(View.VISIBLE); 子线程中不能更新控件
				try {
					Log.v("File Length:", String.valueOf(file.length()));
					progressBar.setMax((int) file.length());
					Socket socket = new Socket(PictureIp, 1558);
					socket.setSoTimeout(20000);
					Log.v("pictureIp:", PictureIp);
					DataOutputStream outStream = new DataOutputStream(socket
							.getOutputStream());
					String head = "Content-Length=" + file.length()
							+ ";filename=" + file.getName();
					// outStream.writeUTF(head);
					// outStream.flush();

					DataInputStream inStream = new DataInputStream(
							new BufferedInputStream(new FileInputStream(file)));
					byte[] buf = new byte[1024];

					int len = -1, length = 0;
					while ((len = inStream.read(buf)) != -1) {
						outStream.write(buf, 0, len);
						length += len;
						Log.v("upload File Length:", String.valueOf(length));
						Message msg = new Message();
						msg.getData().putInt("size", length);
						handler.sendMessage(msg);
					}
					outStream.writeUTF("send is over!");
					// //////******sxd2013-7-5*********//////////////
					
					DataInputStream InStream = new DataInputStream(socket
							.getInputStream());
					Log.v(logTag,"receive");
					String address = null;
					address = InStream.readUTF();
					Log.e("MyTag", address);
					System.out.println("address=" + address);
					// //////******sxd2013-7-5*********//////////////
					inStream.close();
					outStream.flush();
					socket.close();
					deid = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
							.getDeviceId();
					webservice = new WebService();
					
					if(PictureIp.equals("114.212.112.29")){
						fileurl = "http://its.nju.edu.cn/upload_service/picture/" + address;
					}
					else if(PictureIp.equals("58.213.123.54")){
						fileurl = "http://58.213.123.54:8081/upload_service/picture/" + address;
					}
					Log.e("fileurl=",PictureIp+fileurl);
					webservice.imageRigister(deid, point_x, point_y, fileurl);
					upsuccess = 1;
					Log.e("upsuccess1",""+upsuccess);
				} catch (Exception e) {
					upsuccess=0;
					Log.e("Catch Error:", e.toString());
				}
//			}
//		}).start();
	}
}
