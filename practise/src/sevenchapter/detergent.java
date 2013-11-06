package sevenchapter;
//P127

class cleanser {
	private String s="Cleanser";
//	public void append(String a){s+=a;}
	public void dilute(){a.append("dilute()");}
	public void apply(){a.append("apply()");}
	public void scrub(){a.append("scrub()");}
//	public String string(){return a.toString();}
	public String toString(){return a.toString();}
	StringBuffer a = new StringBuffer();
//	public static void main(String[] args){
//		cleanser x =new cleanser();
//		x.dilute();
//		x.apply();
//		x.scrub();
//	}
}
 class detergent extends cleanser{
	public void scrub(){
		a.append(" Detergent.scrub()");
		super.scrub();
	}
	public void foam(){a.append(" foam()");}
	public static void main(String[] args){
		detergent x = new detergent();
		x.dilute();
		x.apply();
		x.scrub();
		x.foam();
		System.out.println(x);
		System.out.println("Testing base class:");
//		cleanser.main(args);
		
	}
}
