package internet.multi.temp_test;

import static internet.multi.temp_test.Receive.receive;

/**
 * Created by lenovo on 2016-6-13.
 */
public class Process {
	public static void main(String[] args) {
		while (true) {
			String res = receive(7777, "FF01:0000:0000:0000:0001:2345:6789:abce");
			System.out.println("fuck me" + res);
		}
	}
}
