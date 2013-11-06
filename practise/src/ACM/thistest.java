package ACM;

public class thistest {
    private int i=0;
    //第一个构造器：有一个int型形参
    thistest(int i){
       this.i=i+1;//此时this表示引用成员变量ｉ，而非函数参数ｉ
       System.out.println("Int constructor i——this.i:  "+i+"——"+this.i);
       System.out.println("i-1:"+(i-1)+"this.i+1:"+(this.i+1));
       //从两个输出结果充分证明了i和this.i是不一样的！
    }
    //  第二个构造器：有一个String型形参
    thistest(String s){
       System.out.println("String constructor:  "+s);
    }
    //  第三个构造器：有一个int型形参和一个String型形参
    thistest(int i,String s){
      // this(s);//this调用第二个构造器
       this(i);
      
       this.i=i++;//this以引用该类的成员变量
       System.out.println("Int constructor:  "+i);
       System.out.println("String constructor:  "+s);
    }
    public thistest increment(){
       this.i++;
       return this;//返回的是当前的对象，该对象属于（thistest）
    }
    public static void main(String[] args){
    	thistest tt0=new thistest(10);
    	thistest tt1=new thistest("ok");
    	thistest tt2=new thistest(20,"ok again!");
      
       System.out.println(tt0.increment().increment().increment().i);
       //tt0.increment()返回一个在tt0基础上ｉ++的thistest对象，
       //接着又返回在上面返回的对象基础上i++的thistest对象！
    }
}
