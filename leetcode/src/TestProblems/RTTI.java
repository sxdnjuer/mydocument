package TestProblems;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RTTI {
	int temp = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RTTI rtti = new RTTI();
		Class<?> c = null;
//		System.out.println(args.length);
		try {
//			c = rtti.getClass();
			 c= Class.forName("TestProblems.RTTI");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Can not find the class TestProblems.RTTI");
//			e.printStackTrace();
		}
		Object obj;
		try {
			obj = c.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("Can not instantiate");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			  System.out.println("Can not access");
		}
		Method methods[] = c.getDeclaredMethods();
//		for(Method method:methods){
//			System.out.println(method.getName());
//			System.out.println(method.getReturnType());
//			System.out.println(method.getParameterTypes().length);
//			for(int  i = 0 ;i<method.getParameterTypes().length;i++){
//				System.out.println(method.getParameterTypes()[i]);
//			}
//		}
		Field fields[] = c.getDeclaredFields();
		for(Field field:fields){
			System.out.println(field.getName());
			System.out.println(field.getType());
		}
//		Constructor constructors[] = c.getConstructors();
//		for(Constructor constructor: constructors){
//			System.out.println(constructor.toString());
//		}
	}
	
	private String RunTimeTypeInformation(String test1 , int test2){

		String result = "rtti";
		return result;
	}

}
