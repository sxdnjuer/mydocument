package DesignPattern;

import java.util.Iterator;
import java.util.Vector;


interface Observer{
	public void update();
}


class Observer1 implements Observer{
	public void update(){
		System.out.println("observer1 has received!");
	}
}
class Observer2 implements Observer{
	public void update(){
		System.out.println("observer2 has received!");
	}
}

interface Subject{
	/*增加观察者*/
	public void add(Observer observer);
	/*删除观察者*/
	public void del(Observer observer);
	/*通知所有的观察者*/  
    public void notifyObservers();  
    /*自身的操作*/  
    public void operation();  
}

abstract class AbstractSubject implements Subject{
	private Vector<Observer> vector = new Vector<Observer>();
	public void add(Observer observer){
		vector.add(observer);
	}
	public void del(Observer observer){
		vector.remove(observer);
	}
	public void notifyObservers(){
		Iterator<Observer> it = vector.iterator();
//		Enumeration<Observer> enumo = vector.elements();
		while(it.hasNext()){
			it.next().update();
		}
//		while(enumo.hasMoreElements()){
//			enumo.nextElement().update();
//		}
	}
}

class MySubject extends AbstractSubject{
	 public void operation() {  
	        System.out.println("update self!");  
	        notifyObservers();  
	    }  
}
public class ObserverPattern {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Subject sub = new MySubject();
		Observer observer1 = new Observer1();
		sub.add(observer1);
		sub.add(new Observer2());
		sub.operation();
		sub.del(observer1);
		sub.operation();
	}

}
