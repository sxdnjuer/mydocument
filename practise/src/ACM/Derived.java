package ACM;

class Base {
	public int Bar(char x){return (int)(x);}
    public int Bar(int x){return (2*x);}
}
public class Derived extends Base
{
    public  int Bar(char x){return(int)(-x);}
 //   public  int Bar(int x){return (x/2);}
    public int Bar(){return 0;}
    public static void main(String []args){
    	Base obj = new Derived();
    	Derived pobj = new Derived();
    	System.out.println(obj.Bar((char)(100)));
    	System.out.println(obj.Bar(100));
    	System.out.println(pobj.Bar(100));
    	
    }
}

