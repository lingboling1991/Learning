package internet.multi.temp_test;

import static internet.multi.temp_test.Send.send;

/**
 * Created by lenovo on 2016-6-13.
 */
public class Send2 {
	public static void main(String[] args) {
		while (true) {

			send(7777, "FF01:0000:0000:0000:0001:2345:6789:abce");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
