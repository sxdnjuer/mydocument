//构造器内部的多态方法的行为P162
package eightchapter;

class Glyph{
	void draw(){
		System.out.println("Glyph.draw()");
	}
	Glyph(int j){
		System.out.println("Glyph() before draw()");
		draw();
		System.out.println("Glyph() after draw()");
	}
}
class RoundGlyph extends Glyph{
	private int radius = 2;
	RoundGlyph(int r){
		super(r);
		radius = r;	
		System.out.println("RoundGlyph.RoundGlyph(),radius = "+ radius);
	}
	void draw(){
		System.out.println("RoundGlyph.draw(),radius =" + radius);
	}
}
public class PolyConstructors {
	public static void main(String[] args){
		RoundGlyph poly=new RoundGlyph(5);
	}
}
