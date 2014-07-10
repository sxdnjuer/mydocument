package TestProblems;

public class Multithread {
	static int a = 10;
	static int b = 1;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		threadone to = new threadone();
		threadtwo tt = new threadtwo();
		tt.start();
		
		to.start();
//		System.out.println("a2= " + a);
		System.out.println("a1= " + a);
		
	}
}

class threadone extends Thread {
	public void run() {
		if(Multithread.b==1){
//		synchronized(threadone.class){
			Multithread.b--;
			Multithread.a++;
//		}
		}
		Multithread.b++;
	}
}

class threadtwo extends Thread {
	public void run() {
		if(Multithread.b==1){
//		synchronized(threadone.class){
			Multithread.b--;
			Multithread.a--;
//		}
		}
		Multithread.b++;
	}
}
