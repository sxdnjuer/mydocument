package huaweicompetition;


public class JavaPlayer 
{ 
	static 
	{ 
	    String libPath = "CallJava"; 

	    // 向JVM加载指定相对路径的C++库。 
	    System.loadLibrary(libPath); 
		
		api_log("JavaPlayer Player: Load " + libPath + ".dll ok.\n");
	} 

	public JavaPlayer() 
	{ 
	} 

	//请子类实现play方法
	//public void play();
		
    //------------- 以下是server提供的各个api接口，全部是native方法--------------//
    
 	// 功能: 获取地图一共有多少行(row)
	public static native int api_getMatrixRow(); 

	// 功能: 获取地图一共有多少列(col)
	public static native int api_getMatrixCol(); 

	// 功能: 获取第row行第col列的格子的状态
	// 参数说明: row范围[0, api_getMatrixRow()-1], col范围[0, api_getMatrixCol()-1]
	// 返回值: GRID_EMPTY/GRID_LEFT/GRID_RIGHT/GRID_BOX/GRID_FENCE。如果传入参数不合法，返回值未定义。
	public static native int api_getGridInfo(int iRow, int iCol); 

	// 获取我方在哪边
	// 返回值: PLAYER_L我是左方 / PLAYER_R我是右方
	public static native int api_whoami(); 

	// 功能: 发送执行动作给server
	// 虽然函数名称是push, 但实际效果可以是小人移动行走，亦可以是小人推动箱子
	// 参数说明: iRow/iCol: 指定的本方小人的位置(行/列)
	//           direction: 指定的本方小人移动的方向DIR_UP/DIR_DOWN/DIR_LEFT/DIR_RIGHT
    // 备注：一个回合内最多只能发送一个api_push指令。如果发送多个，sever只取第一个。
	//       如果参数不合法，server将视作此方没有发送api_push。
	public static native void api_push(int iRow, int iCol, int direction); 

	// 功能: 打印日志(server会自动补上日志头部), 如果需要换行，由str参数内部置入换行符
	public static native void api_log(String str);

	// 功能: 打印日志(但是不打印server的日志头部), 如果需要换行，由str参数内部置入换行符
	static public native void api_log_without_logheader(String str);

    //------------- 以下是常量定义 --------------//
	public static final int GRID_EMPTY = 0; //空白
	public static final int GRID_LEFT  = 1; //左方
	public static final int GRID_RIGHT = 2; //右方
	public static final int GRID_BOX   = 3; //箱子
	public static final int GRID_FENCE = 4; //篱笆(障碍)

	public static final int PLAYER_L = GRID_LEFT;   //左方
	public static final int PLAYER_R = GRID_RIGHT;  //右方

	public static final int DIR_UP    = 0;  //向上
	public static final int DIR_DOWN  = 1;  //向下
	public static final int DIR_LEFT  = 2;  //向左
	public static final int DIR_RIGHT = 3;  //向右
        
} 
