package kupai;

public class thread extends B{
	
	static {
		System.out.print(1);
	}
	thread() {
		System.out.print(3);
	}

	{
		System.out.print(2);
	}
	public static void main(String args[]) {
//		System.out.println(array[0][2]);
		Thread a = new Thread();
		Runnable r = new Thread();
		Thread t = new Thread(r);
		boolean res = r instanceof Thread;
//		System.out.println(res);
		StringBuffer str = new StringBuffer("1");
		for (int i = 2; i < 100; i++) {
			str.append(i);
		}
		// System.out.println(str);
//		 Switch();
		System.out.println("hello");
		thread aa = new thread();
//		byteappend();
//		Alpha al = new Alpha();
//		E x1= new E("X"), y1 = new E("Y");
////		new E("x");
//		x1 = null;
//		y1 = null;
//		System.gc();
		
	}

	
	public static void byteappend() {
		byte a = (byte) 127, b = (byte) 128, c = (byte) 255, d = (byte) 256;
		System.out.println(a + "" + b + "" + c + "" + d);
	}

	public static void Switch() {
		char c = 'd';
		switch (c) {
		default:
			System.out.println("0");
		case 'a':
			System.out.println("1");
		case 'b':
			System.out.println("2");
		case 'c':
			System.out.println("3");

		}
	}
}
//�����м̳У����һ��࣬�ӻ��ൽ���࣬static���γ�ʼ��
//2�����л���������ΪĬ��ֵ������Ϊnull
//3������ʵ��������ʼ�������๹����
//4������ʵ��������ʼ�������๹����

class A {
	static {
		System.out.print(1);
	}

	A() {
		System.out.print(3);
	}

	{
		System.out.print(2);
	}
}

class B  {
	static {
		System.out.print(4);
	}

	B() {
		System.out.print(6);
	}

	{
		System.out.print(5);
	}
}

class Alpha {
	public Alpha() {
		boolean flag;
		int i = 0;
		do {
			flag = false;
			System.out.print(i++);
			flag = i < 10;
			continue;
		} while ((flag) ? true : false);

	}
}

class E{
	private String name;
	public E(String s){
		name = s;
	}
	protected void finalize(){
		System.out.print(name);
	}
}