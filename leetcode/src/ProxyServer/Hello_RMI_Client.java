package ProxyServer;

import java.rmi.Naming;

public class Hello_RMI_Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	        try {
	        	GumballMachineRemote hello = (GumballMachineRemote) Naming.lookup("rmi://localhost:1099/gumballMachine");
	                System.out.println(hello.getLocation());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
