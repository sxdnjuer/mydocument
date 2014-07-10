package ProxyServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class GumballMachine extends UnicastRemoteObject implements
		GumballMachineRemote {
	int count;
	String location;

	public GumballMachine(String location, int count) throws RemoteException {
		this.count = count;
		this.location = location;
	}

	@Override
	public int getCount() throws RemoteException {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public String getLocation() throws RemoteException {
		// TODO Auto-generated method stub
		return location;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GumballMachineRemote gumballMachine = null;
		// int count;
		try {
			// 而且用命令rmiregistry启动注册服务还必须事先用RMIC生成一个stub类为它所用,并且需要设置CLASSPATH，而我尝试了并未成功
			LocateRegistry.createRegistry(1099);
			gumballMachine = new GumballMachine("seattle", 100);
			java.rmi.Naming.rebind("rmi://127.0.0.1:1099/gumballMachine",
					gumballMachine);
			System.out.print("Ready");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
