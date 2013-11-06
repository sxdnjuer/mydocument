//∏≤∏«Œ Ã‚P144 2013-7-17
package sevenchapter;

 class Parent{
    int i=7;
	static public int Test(){
		System.out.println("parent method");
		return 47;
	}
}
//class Child extends Parent{
//	public void Test(){
//		System.out.println("child method");
//	}
//}
public class cover extends Parent{
	int i=0;
	int k=Test();
	public static void main(String[] args){
//		Child child = new Child();
//		child.Test();
		System.out.println("method");
		Parent parent = new Parent();
		parent.i=40;
		Parent.Test();
	}
}
