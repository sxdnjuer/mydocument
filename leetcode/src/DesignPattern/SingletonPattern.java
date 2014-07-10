package DesignPattern;


//��JavaӦ���У����������ܱ�֤��һ��JVM�У��ö���ֻ��һ��ʵ������
//��Ͻ�ָOffer 48�⣬ ���һ�����ܱ��̳е���
public class SingletonPattern {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

class Singleton{
	 /* ����˽�о�̬ʵ������ֹ�����ã��˴���ֵΪnull��Ŀ����ʵ���ӳټ��� */ 
	private static Singleton instance = null;
	 /* ˽�й��췽������ֹ��ʵ���� */  
	private Singleton(){}
	/* ��̬���̷���������ʵ�� */ 
	public static Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
		}
		return instance;
	}
	
	
}