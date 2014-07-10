package TestProblems;



public class Race_ebay {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		t1.start();
		try{
			t1.join();
		}
		catch(InterruptedException ie){
			ie.printStackTrace();
		}
//		try {
//			t1.wait();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("join");
	
		
	}
	
	public static Thread t1 = new Thread("First"){
		public void run(){
			
//			synchronized(Race_ebay.class){
//				yield();
				try{
					t1.wait(100);  
//					Race_ebay.class.wait(100);
//					yield();
//					Thread.sleep(1000);
				}
				catch(InterruptedException ie){
					ie.printStackTrace();
				}
				
				System.out.println("last");
			}
			
//		}
	};
}
