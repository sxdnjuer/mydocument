import java.util.ArrayList;
import java.util.Collection;

//java泛型使用
class CollectionGenFoo<T extends Collection> {
	private T x;

	public CollectionGenFoo(T x) {
		this.x = x;
	}

	public T getX() {
		return x;
	}

	public void setX(T x) {
		this.x = x;
	}
}

public class Generic {

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		CollectionGenFoo<ArrayList> listFoo = null;
		listFoo = new CollectionGenFoo<ArrayList>(new ArrayList());
		// 出错了,不让这么干。
		// 下面的这个地方有误
//		 CollectionGenFoo< Collection> listFoo1 = null;
//		 listFoo1=new CollectionGenFoo<ArrayList>(new ArrayList());
		CollectionGenFoo< ? extends Collection> listFoo1 = null;
		 listFoo1=new CollectionGenFoo<ArrayList>(new ArrayList());
		System.out.println("实例化成功!");
	}
	/*
	 * 当前看到的这个写法是可以编译通过，并运行成功。可是注释掉的两行加上就出错了，因为<T extends
	 * Collection>这么定义类型的时候，就限定了构造此类实例的时候T是确定的一个类型，这个类型实现了Collection接口，但是实现
	 * Collection接口的类很多很多
	 * ，如果针对每一种都要写出具体的子类类型，那也太麻烦了，我干脆还不如用Object通用一下。别急，泛型针对这种情况还有更好的解决方案
	 * ，那就是“通配符泛型”。<? extends Collection>
	 */

}
