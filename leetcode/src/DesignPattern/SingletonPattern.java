package DesignPattern;


//在Java应用中，单例对象能保证在一个JVM中，该对象只有一个实例存在
//结合剑指Offer 48题， 设计一个不能被继承的类
public class SingletonPattern {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

class Singleton{
	 /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */ 
	private static Singleton instance = null;
	 /* 私有构造方法，防止被实例化 */  
	private Singleton(){}
	/* 静态工程方法，创建实例 */ 
	public static Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
		}
		return instance;
	}
	
	
}