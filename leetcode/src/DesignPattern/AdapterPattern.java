package DesignPattern;
//Targetable�ӿڵ�ʵ����;�����Source��Ĺ���(���������ģʽ)
class Source{
	public void method1(){
		System.out.println("this is original method");
	}
}

interface Targetable{
	/* ��ԭ���еķ�����ͬ */ 
	public void method1();
	/* ����ķ��� */  
    public void method2();
}

class Adapter extends Source implements Targetable{
	public void method2(){
		System.out.println("this is targetable method");
	}
}

//�����������ģʽ
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
