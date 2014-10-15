package com.example.phonecapture;

public class GetPara {
	
	public byte[] getsps(String mtype,String ctype)
	{		
		
		if(mtype.equals("HTC T328w")){
			if(ctype.equals("QCIF"))
				return new byte[]{0x67,0x42,0x00,0x1f,(byte) 0x96,0x54,0x16,0x26, 0x20};//htc 176*144
			else if(ctype.equals("CIF"))
				return new byte[]{0x67,0x42,0x00,0x1f,(byte) 0x96,0x54,0x0b,0x04,(byte) 0xa2}; //htc 352*288			
			else if(ctype.equals("QVGA"))
			    return new byte[]{0x67,0x42,0x00,0x1f,(byte) 0x96,0x54,0x0a,0x0f,(byte) 0x88}; //htc 320*240
		}
		else if(mtype.equals("GT-P1000")){
			if(ctype.equals("QCIF"))
				return new byte[]{0x67,0x42,0x00,0x0b,(byte)0xe9,0x05,(byte) 0x89,(byte)0xc8}; //sangsum 176*144
			else if(ctype.equals("CIF"))
				return new byte[]{0x67,0x42,0x00,0x0C,(byte)0xe9,0x02,(byte) 0xc1,(byte)0x2c,(byte) 0x80}; //sangsum 352*288			
			else if(ctype.equals("QVGA"))
			    return new byte[]{0x67,0x42,0x00,0x0C,(byte)0xe9,0x02,(byte)0x83,(byte) 0xf2}; //sangsum 320*240
			/*else if(ctype.equals("D1"))
			    return new byte[]{0x67,0x42,0x00,0x16,(byte) 0xe9,0x01,0x60,0x24,(byte) 0xc8}; //sangsum 704*576
			 */
		    }
		else if(mtype.equals("SCH-I939")){
			if(ctype.equals("QCIF"))
				return 	new byte[]{0x67,0x42,(byte) 0x80,0x0b,(byte) 0xe9,0x05,(byte) 0x89,(byte) 0xc8 };//sangsum SCH-I939 CIF 176*144:67 42 80 0b e9 05 89 c8
			else if(ctype.equals("CIF"))
				return 	new byte[]{0x67,0x42,(byte) 0x80,0x0c,(byte) 0xe9,0x02,(byte) 0xc1,0x2c,(byte) 0x80};//sangsum SCH-I939 CIF  352*288:67 42 80 0c e9 02 c1 2c 80 			
			else if(ctype.equals("QVGA"))
			    return new byte[]{0x67,0x42,(byte) 0x80,0x0b,(byte) 0xe9,0x02,(byte) 0x83,(byte) 0xf2}; //sangsum 320*240:67 42 80 0b e9 02 83 f2 
			/*else if(ctype.equals("D1"))
			    return new byte[]{0x67,0x42,0x00,0x16,(byte) 0xe9,0x01,0x60,0x24,(byte) 0xc8}; //sangsum 704*576
			 */
		    }	
		else if(mtype.equals("HUAWEI P6-C00")){
			if(ctype.equals("QCIF"))
				return new byte[]{0x27,0x42,(byte)0xE0,0x0A,(byte)0x8D,0x68,0x2C,0x4E,(byte)0x9B,0x20,0x00,0x00,0x03,0x00,0x20,0x00,0x00,0x03,0x01,0x41,(byte)0xE2,(byte)0x84,0x54};
			else if(ctype.equals("CIF"))
				return new byte[]{0x27,0x42,(byte)0xE0,0x16,(byte)0x8D,0x68,0x16,0x09,(byte)0x69,(byte)0xB2,0x00,0x00,0x03,0x00,0x20,0x00,0x00,0x03,0x00,0x14,(byte)0x1E,(byte)0x28,0x45,0x40};
			else if(ctype.equals("QVGA"))
				return new byte[]{0x27,0x42,(byte)0xE0,0x0C,(byte)0x8D,0x68,0x14,0x1F,(byte)0xA6,(byte)0xC8,0x00,0x00,0x03,0x00,0x08,0x00,0x00,0x03,0x00,0x50,(byte)0x78,(byte)0xA1,0x15};
		}
		else if(mtype.equals("M040")){
			if(ctype.equals("QVGA"))
				return new byte[]{0x67,0x4D,(byte)0x00,0x28,(byte)0xE9,0x02,(byte)0x83,(byte)0xF2};
		}
		else if(mtype.equals("T8830")){
			if(ctype.equals("QVGA"))
				return new byte[]{0x67,0x42,(byte)0xC0,0x0C,(byte)0xAB,0x40,(byte)0xA0,(byte)0xFD,0x08,0x00,0x00,0x03,0x00,0x08,0x00,0x00,0x03,0x00,(byte)0xF4,0x78,(byte)0xA1,0x55};
		}
		else if(mtype.equals("SM-N9009")){
			if(ctype.equals("QVGA"))
				return 	new byte[]{0x67,0x42,(byte) 0x80,0x0D,(byte)0xE4,0x40,(byte) 0xA0,(byte) 0xFD,0x00,(byte)0xDA,0x14,0x26,(byte)0xA0};//sangsum SCH-I939 CIF 176*144:67 42 80 0b e9 05 89 c8
			else if(ctype.equals("CIF"))
				return 	new byte[]{0x67,0x42,(byte) 0x80,0x0D,(byte)0xE4,0x40,(byte) 0xB0,(byte) 0x4B,0x40,(byte)0x36,(byte)0x85,0x09,(byte)0xA8};//sangsum SCH-I939 CIF  352*288:67 42 80 0c e9 02 c1 2c 80 			
			else if(ctype.equals("QCIF"))
			    return new byte[]{0x67,0x42,(byte) 0x80,0x0B,(byte)0xE4,0x41,(byte) 0x62,(byte) 0x74,0x03,(byte)0x68,(byte)0x50,(byte)0x9A,(byte)0x80}; 
		}
		return null;		
	}



	public byte[] getpps(String mtype)
	{
		if(mtype.equals("HTC T328w"))
			return new byte[]{0x68,(byte) 0xce,0x38,(byte) 0x80};
		else if(mtype.equals("GT-P1000"))
		    return new byte[]{0x68,(byte) 0xce,0x01,(byte) 0x0f,0x20};
		else if(mtype.equals("SCH-I939"))
		    return new byte[]{0x68,(byte) 0xce,0x06,(byte) 0x02};
		else if(mtype.equals("HUAWEI P6-C00"))
			return new byte[]{0x28,(byte) 0xce,0x02,(byte) 0xF2,0x48};
		else if(mtype.equals("M040"))
			return new byte[]{0x68,(byte) 0xCE,0x06,(byte) 0xF2};
		else if(mtype.equals("T8830")){
			return new byte[]{0x68,(byte) 0xCE,0x3C,(byte) 0x80};
		}
		else if(mtype.equals("SM-N9009")){
			return new byte[]{0x68,(byte)0xCE,0X38,(byte)0X80};
		}
		return null;
	}

	public  int getdisread(String mtype)
	{
		int disread = 0;
		if(mtype.equals("HTC T328w"))
		{    disread=52;
			return disread;
		}
		else if(mtype.equals("GT-P1000"))
		{
		    disread=40;
		    return disread;
		}
		else if(mtype.equals("SCH-I939"))
		{
		    disread=32;
		    return disread;
		}
		else if(mtype.equals("HUAWEI P6-C00"))
		{
			disread=36;
			return disread;		
		}
		else if(mtype.equals("M040"))
		{
			disread=44;
			return disread;		
		}
		else if(mtype.equals("T8830")){
			disread=36;
			return disread;	
		}
		else if(mtype.equals("SM-N9009")){
			disread=32;
			return disread;	
		}
		return 0;
			
	}
}



//sps pps
//ÿ���ֻ��ͺŶ���һ����						

//��Ϊhuawei	
/*

352*288
SPS����Ϊ��23
SPS������Ϊ��67 42 c0 0c ab 40 b0 4b 42 00 00 03 00 02 00 00 03 00 3d 1e 28 55 40 
----------
PPS����Ϊ��4
PPS������Ϊ��68 ce 3c 80 

byte[] h264sps={0x67,0x42,(byte) 0xc0,0x0c,(byte) 0xab,
		0x40,(byte) 0xb0,0x4b,0x42,0x00, 
		0x00,0x03,0x00,0x02,0x00, 
		0x00,0x03,0x00,0x3d, 0x1e, 
		0x28,0x55,0x40};
	byte[] h264pps={0x68,(byte) 0xce,0x3c,(byte) 0x80};	

----------

///
//176*144 fps 10
///
SPS����Ϊ��22
SPS������Ϊ��67 42 c0 0c ab 41 62 74 20 00 00 03 00 20 00 00 03 03 d1 e2 85 54 
----------
PPS����Ϊ��4
PPS������Ϊ��68 ce 3c 80 
----------

 */

//byte[] h264sps={0x67,0x42,(byte) 0xc0,0x0c,(byte) 0xab,0x41,0x62,0x74,0x20,0x00,0x00,0x03,0x00,0x20,0x00,0x00,0x03,0x03,(byte) 0xd1,(byte) 0xe2,(byte) 0x85,0x54};
//byte[] h264pps={0x68,(byte) 0xce,0x3c,(byte) 0x80};	


//htc t328
/*
 * 320*240
 SPS����Ϊ��9
SPS������Ϊ��67 42 00 1f 96 54 0a 0f 88 
----------
PPS����Ϊ��4
PPS������Ϊ��68 ce 38 80 
----------			  

//352*288  -6167
SPS����Ϊ��9
SPS������Ϊ��67 42 00 1f 96 54 0b 04 a2 
byte[] h264sps={0x67,0x42,0x00,0x1f,(byte) 0x96,0x54,0x0b,0x04,(byte) 0xa2}; //htc 352*288
----------
PPS����Ϊ��4
PPS������Ϊ��68 ce 38 80 



----------

176*144
SPS����Ϊ��9
SPS������Ϊ��67 42 00 1f 96 54 16 26 20 
----------
PPS����Ϊ��4
PPS������Ϊ��68 ce 38 80 
byte[] h264sps={0x67,0x42,0x00,0x1f,(byte) 0x96,0x54,0x16,0x26, 0x20}; //htc 176*144

*/			
//byte[] h264sps={0x67,0x42,0x00,0x1f,(byte) 0x96,0x54,0x16,0x26, 0x20};				
//byte[] h264pps={0x68,(byte) 0xce,0x38,(byte) 0x80};			
//////



//�htc 352*288 rate10 
//byte[] h264sps={0x67,0x42,(byte) 0xc0,0x0c,(byte) 0xe9,0x02,(byte) 0xc1,0x2c,(byte) 0x80}; //htc 352*288				
//byte[] h264pps={0x68,(byte) 0xce,0x06,(byte) 0xe2};	 //htc
//
//67 42 c0 0c e9 02 c1 2c 80
//68 ce 06 e2 



//����939
/*
QVGA
SPS����Ϊ��8
SPS������Ϊ��67 42 80 0b e9 02 83 f2 
----------
PPS����Ϊ��4
PPS������Ϊ��68 ce 06 e2 
----------

QCIF
SPS����Ϊ��8
SPS������Ϊ��67 42 80 0b e9 05 89 c8 
----------
PPS����Ϊ��4
PPS������Ϊ��68 ce 06 e2 
----------


SPS����Ϊ��9
SPS������Ϊ��67 42 80 0c e9 02 c1 2c 80 
----------
PPS����Ϊ��4
PPS������Ϊ��68 ce 06 e2 
----------

*/
//���� sangsum P1000
/*
 * 
 * 
320*240---------

SPS����Ϊ��8
SPS������Ϊ��67 42 00 0c e9 02 83 f2 
byte[] h264sps={0x67,0x42,0x00,0x0C,(byte)0xe9,0x02,(byte) 0x83,(byte)0xf2};//���������ж�����sps

----------
PPS����Ϊ��5
PPS������Ϊ��68 ce 01 0f 20 
----------
352*288
SPS����Ϊ��9
SPS������Ϊ��67 42 00 0c e9 02 c1 2c 80 
byte[] h264sps={0x67,0x42,0x00,0x0C,(byte)0xE9,0x02,(byte) 0xc1,(byte)0x2c,(byte) 0x80};//���������ж�����sps

----------
PPS����Ϊ��5
PPS������Ϊ��68 ce 01 0f 20 
----------
*/
////


///
//����GP-S7562i 320*240
//
/*			

SPS����Ϊ��9
SPS������Ϊ��67 42 00 0a 96 54 0a 0f 88 
----------
PPS����Ϊ��4
PPS������Ϊ��68 ce 38 80 
----------

----------
*/
//byte[] h264sps={0x67,0x42,0x00,0x0a,(byte) 0x96,0x54,0x0a,0x0f,(byte) 0x88};//���������ж�����sps
//byte[] h264pps={0x68,(byte) 0xce,0x38,(byte) 0x80};//���������ж�����pps
//�����ֻ�
//sps������10���ֽ�
//byte[] h264sps={0x67,0x42,0x00,0x14,(byte)0x8D,(byte) 0x95,(byte)0x02,(byte)0xC1,0x2C,(byte) 0x80};//���������ж�����saps
////pps������4
//byte[] h264pps={0x68,(byte) 0xCE,(byte) 0x38,(byte) 0x80};


