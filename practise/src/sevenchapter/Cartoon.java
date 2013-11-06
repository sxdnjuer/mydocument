//P129
package sevenchapter;

class Art{
	Art(){System.out.println("Art constructor!!!");}
	Art(int i){
		System.out.println("i="+i);
		System.out.println("Art constructor");}
	public void A(int j){
		System.out.println("j="+j);
		System.out.println("A constructor");}
}
class Drawing extends Art{

	public void A(int k){
		System.out.println("k="+k);
}
	Drawing(int k){
		super(k);	
		System.out.println("k="+k);
	}
	Drawing(){
		super(1);
		super.A(2);
		this.A(2);
		System.out.println("Drawing constructor");}
}
public class Cartoon extends Drawing{
	public static int a;

	public Cartoon(){
//		super(11);
		System.out.println("Cartoon constructor");
		}
	public static void main(String[] args){
		Cartoon x ;
		x = new Cartoon();
		x.A(2);
		System.out.println(a);
	}
}
