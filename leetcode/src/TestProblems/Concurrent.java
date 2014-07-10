package TestProblems;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Concurrent {
	private ReentrantLock lock = new ReentrantLock();

	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock():" + captured);
		} finally {
			if (captured) {
				lock.unlock();
			}
		}
	}

	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		try {
			System.out.println("tryLock(2, TimeUnit.SECONDS):" + captured);
		} finally {
			if (captured) {
				lock.unlock();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Concurrent con = new Concurrent();
		con.untimed();
		con.timed();
		new Thread() {
			{
				setDaemon(true);//设置为后台线程
			}

			public void run() {
				con.lock.lock();
				System.out.println("acquired");
			}
		}.start();
		Thread.yield();
		con.untimed();
		con.timed();
	}
}
