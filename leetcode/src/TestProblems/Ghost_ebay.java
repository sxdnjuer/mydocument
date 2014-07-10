package TestProblems;



abstract class test{
	void TS(){}
	abstract void t();
}
public class Ghost_ebay extends test{
	final  String s1 = "Running";
	class z{
		String s1 = "Run";
		void method(){
			System.out.println(Ghost_ebay.this.s1);
		}
	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ghost_ebay ge = new Ghost_ebay();
		ge.new z().method();
	}
	@Override
	void t() {
		// TODO Auto-generated method stub
	}

}
