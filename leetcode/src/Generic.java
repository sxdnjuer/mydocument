import java.util.ArrayList;
import java.util.Collection;

//java����ʹ��
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
		// ������,������ô�ɡ�
		// ���������ط�����
//		 CollectionGenFoo< Collection> listFoo1 = null;
//		 listFoo1=new CollectionGenFoo<ArrayList>(new ArrayList());
		CollectionGenFoo< ? extends Collection> listFoo1 = null;
		 listFoo1=new CollectionGenFoo<ArrayList>(new ArrayList());
		System.out.println("ʵ�����ɹ�!");
	}
	/*
	 * ��ǰ���������д���ǿ��Ա���ͨ���������гɹ�������ע�͵������м��Ͼͳ����ˣ���Ϊ<T extends
	 * Collection>��ô�������͵�ʱ�򣬾��޶��˹������ʵ����ʱ��T��ȷ����һ�����ͣ��������ʵ����Collection�ӿڣ�����ʵ��
	 * Collection�ӿڵ���ܶ�ܶ�
	 * ��������ÿһ�ֶ�Ҫд��������������ͣ���Ҳ̫�鷳�ˣ��Ҹɴ໹������Objectͨ��һ�¡��𼱣������������������и��õĽ������
	 * ���Ǿ��ǡ�ͨ������͡���<? extends Collection>
	 */

}
