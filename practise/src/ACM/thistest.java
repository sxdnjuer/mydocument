package ACM;

public class thistest {
    private int i=0;
    //��һ������������һ��int���β�
    thistest(int i){
       this.i=i+1;//��ʱthis��ʾ���ó�Ա�����飬���Ǻ���������
       System.out.println("Int constructor i����this.i:  "+i+"����"+this.i);
       System.out.println("i-1:"+(i-1)+"this.i+1:"+(this.i+1));
       //���������������֤����i��this.i�ǲ�һ���ģ�
    }
    //  �ڶ�������������һ��String���β�
    thistest(String s){
       System.out.println("String constructor:  "+s);
    }
    //  ����������������һ��int���βκ�һ��String���β�
    thistest(int i,String s){
      // this(s);//this���õڶ���������
       this(i);
      
       this.i=i++;//this�����ø���ĳ�Ա����
       System.out.println("Int constructor:  "+i);
       System.out.println("String constructor:  "+s);
    }
    public thistest increment(){
       this.i++;
       return this;//���ص��ǵ�ǰ�Ķ��󣬸ö������ڣ�thistest��
    }
    public static void main(String[] args){
    	thistest tt0=new thistest(10);
    	thistest tt1=new thistest("ok");
    	thistest tt2=new thistest(20,"ok again!");
      
       System.out.println(tt0.increment().increment().increment().i);
       //tt0.increment()����һ����tt0�����ϣ�++��thistest����
       //�����ַ��������淵�صĶ��������i++��thistest����
    }
}
