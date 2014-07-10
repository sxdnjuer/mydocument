package onLineProblem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LRU_Cache {
	int capacity;
	int curcapacity = 0;
	Map<Integer , Integer> map = new HashMap<Integer,Integer>();
	static List<Integer> list = new LinkedList<Integer>();
	static int count = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRU_Cache lru = new LRU_Cache(3);
		lru.set(2, 2);
		lru.set(4, 4);
		lru.set(3, 3);
		lru.set(5, 5);
		lru.set(3, 3);
		lru.set(2, 2);
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		System.out.println(lru.get(3));
		System.out.println(count);
	}
	
	public LRU_Cache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
        	return map.get(key);
        }
        else{
        	return -1;
        }
    }
    
    public void set(int key, int value) {
       if(map.containsKey(key)){
    	   for(int i = 0;i<list.size();i++){
    		   if(list.get(i)==key){
    			   list.remove(i);
    			   list.add(key);
    			   break;
    		   }
    	   }
    	   return;
       }
       else{
    	    count++;
	    	if(curcapacity==capacity){
	    		map.remove(list.get(0));
	        	list.remove(0);
	        	list.add(key);
	        	map.put(key, value);
	        }
	    	else{
	    		map.put(key, value);
	    		list.add(key);
	    		curcapacity++;
	    	}
       }
    }

}
