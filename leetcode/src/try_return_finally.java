
public class try_return_finally {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try_return_finally trf = new try_return_finally();
		System.out.println(trf.test());
	}
	
	public int test(){
		try{
			return 1;
		}
//		catch(Exception e){
//			
//		}
		finally{
			System.out.println(2);
			return 2;
		}
	}

}
