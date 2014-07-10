package JavaPoints;

public class synchronizeTest implements Runnable{
	private int j ; 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		synchronizeTest st = new synchronizeTest();
//		Inc inc =st.new Inc();
//		Dec dec =st.new Dec();
		for(int i = 0;i<2;i++){
//			Thread t= new Thread(inc);
//			t.start();
//			t= new Thread(dec);
//			t.start();
			Thread t= new Thread(st);
			t.start();
		}
	}
	
	private synchronized void inc(){
		j++;
		System.out.println(Thread.currentThread().getName()+"-inc"+j);
	}
	
	private synchronized void dec(){
		j--;
		System.out.println(Thread.currentThread().getName()+"-dec"+j);
	}
	
	public void run(){
		for(int i = 0;i<100;i++){
			inc();
			
		}
		for(int i = 0;i<100;i++){
			dec();
		}
	}
	
//	class Inc implements Runnable{
//		public void run(){
//			for(int i = 0;i<100;i++){
//				inc();
//			}
//		}
//	}
//	class Dec implements Runnable{
//		public void run(){
//			for(int i = 0;i<100;i++){
//				dec();
//			}
//		}
//	}

}
