//9.4 JAVAµÄ¶àÖØ¼Ì³Ð
package ninechapter;

interface canfight{
	void fight();
}

interface canswim{
	void swim();
}

interface canfly{
	void fly();
}

abstract class canwalk{
	public void walk(){System.out.println("walk()");}
} 

class actioncharter{
	public void fight(){System.out.println("fight()");
	}
}

class hero extends actioncharter implements canfight , canswim , canfly{
	public void swim(){System.out.println("swim()");}
	public void fly(){System.out.println("fly()");}
}
public class Adventure {
	public static void t(canfight x){x.fight();}
	public static void u(canswim x){x.swim();}
	public static void v(canfly x){x.fly();}
	public static void w(actioncharter x){x.fight();}
	public static void main(String[] args){
//		Adventure ad = new Adventure();
		canfight can = new hero();
		hero h = new hero();
//		ad.t(h);
		t(h);
		u(h);
		v(h);
		w(h);
		t(can);
	}
}
