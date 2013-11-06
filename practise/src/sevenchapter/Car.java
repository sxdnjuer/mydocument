package sevenchapter;

class Engine {
	public void start(){System.out.println("start");}
	public void rev(){System.out.println("rev");}
	public void stop(){	System.out.println("stop");}
}
class Wheel{
	public void inflate(int psi){System.out.println("inflate");}
}
class Window{
	public void rollup(){System.out.println("rollup");}
	public void rolldown(){	System.out.println("rolldown");}
}
class Door{
	public Window window = new Window();
	public void open(){	System.out.println("open");}
	public void close(){System.out.println("close");}
}
public class Car {
	public Engine engine = new Engine();
	public Wheel[] wheel = new Wheel[4];
	public Door left=new Door(), right = new Door();
	public Car(){
		for (int i=0;i<4;i++){
			wheel[i] = new Wheel();
		}
	}
	public static void main(String[] args){
		Car car = new Car();
		car.left.window.rollup();
		car.wheel[0].inflate(72);
	}
	
}
