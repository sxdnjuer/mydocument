package huaweicompetition;


public class JavaPlayer 
{ 
	static 
	{ 
	    String libPath = "CallJava"; 

	    // ��JVM����ָ�����·����C++�⡣ 
	    System.loadLibrary(libPath); 
		
		api_log("JavaPlayer Player: Load " + libPath + ".dll ok.\n");
	} 

	public JavaPlayer() 
	{ 
	} 

	//������ʵ��play����
	//public void play();
		
    //------------- ������server�ṩ�ĸ���api�ӿڣ�ȫ����native����--------------//
    
 	// ����: ��ȡ��ͼһ���ж�����(row)
	public static native int api_getMatrixRow(); 

	// ����: ��ȡ��ͼһ���ж�����(col)
	public static native int api_getMatrixCol(); 

	// ����: ��ȡ��row�е�col�еĸ��ӵ�״̬
	// ����˵��: row��Χ[0, api_getMatrixRow()-1], col��Χ[0, api_getMatrixCol()-1]
	// ����ֵ: GRID_EMPTY/GRID_LEFT/GRID_RIGHT/GRID_BOX/GRID_FENCE���������������Ϸ�������ֵδ���塣
	public static native int api_getGridInfo(int iRow, int iCol); 

	// ��ȡ�ҷ����ı�
	// ����ֵ: PLAYER_L������ / PLAYER_R�����ҷ�
	public static native int api_whoami(); 

	// ����: ����ִ�ж�����server
	// ��Ȼ����������push, ��ʵ��Ч��������С���ƶ����ߣ��������С���ƶ�����
	// ����˵��: iRow/iCol: ָ���ı���С�˵�λ��(��/��)
	//           direction: ָ���ı���С���ƶ��ķ���DIR_UP/DIR_DOWN/DIR_LEFT/DIR_RIGHT
    // ��ע��һ���غ������ֻ�ܷ���һ��api_pushָ�������Ͷ����severֻȡ��һ����
	//       ����������Ϸ���server�������˷�û�з���api_push��
	public static native void api_push(int iRow, int iCol, int direction); 

	// ����: ��ӡ��־(server���Զ�������־ͷ��), �����Ҫ���У���str�����ڲ����뻻�з�
	public static native void api_log(String str);

	// ����: ��ӡ��־(���ǲ���ӡserver����־ͷ��), �����Ҫ���У���str�����ڲ����뻻�з�
	static public native void api_log_without_logheader(String str);

    //------------- �����ǳ������� --------------//
	public static final int GRID_EMPTY = 0; //�հ�
	public static final int GRID_LEFT  = 1; //��
	public static final int GRID_RIGHT = 2; //�ҷ�
	public static final int GRID_BOX   = 3; //����
	public static final int GRID_FENCE = 4; //���(�ϰ�)

	public static final int PLAYER_L = GRID_LEFT;   //��
	public static final int PLAYER_R = GRID_RIGHT;  //�ҷ�

	public static final int DIR_UP    = 0;  //����
	public static final int DIR_DOWN  = 1;  //����
	public static final int DIR_LEFT  = 2;  //����
	public static final int DIR_RIGHT = 3;  //����
        
} 
