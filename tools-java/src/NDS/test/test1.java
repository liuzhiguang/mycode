package NDS.test;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class test1 {

	public static void main(String[] args) {
		// 使用线程安全的Vector 
		Vector<Thread> threads = new Vector<Thread>();
		for (int i = 0; i < 10; i++) {

			Thread iThread = new Thread(new Runnable() {
				public void run() {

					try {
						Thread.sleep(1000);
						// 模拟子线程任务
					} catch (InterruptedException e) {
					}
					System.out.println("子线程" + Thread.currentThread() + "执行完毕");

				}
			});

			threads.add(iThread);
			iThread.start();
		}

		for (Thread iThread : threads) {
			try {
				// 等待所有线程执行完毕
				iThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("主线执行。");
	}
}
