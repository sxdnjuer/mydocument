package DesignPattern;
//Targetable接口的实现类就具有了Source类的功能(类的适配器模式)
class Source{
	public void method1(){
		System.out.println("this is original method");
	}
}

interface Targetable{
	/* 与原类中的方法相同 */ 
	public void method1();
	/* 新类的方法 */  
    public void method2();
}

class Adapter extends Source implements Targetable{
	public void method2(){
		System.out.println("this is targetable method");
	}
}

//对象的适配器模式
class Wrapper implements Targetable{
	private Source source;
	public Wrapper(Source source){
//		super();
		this.source = source;
	}
	public void method1(){
		source.method1();
	}
	public void method2(){
		System.out.println("this is targetable method");
	}
	
}

public class AdapterPattern {
	public static void main(String args[]){
		Targetable target = new Adapter();  
        target.method1();  
        target.method2(); 
        
        Source source = new Source();
        Targetable target2 = new Wrapper(source);
        target2.method1();
        target2.method2();
	}
}
