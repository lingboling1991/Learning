package sdn.v6_addr;

public class ByteCal {
	public static void main(String args[]) {
		String s = "1101";
		char[] cl = s.toCharArray();
		Integer[] il = new Integer[cl.length];
		Byte[] bl=new Byte[cl.length];
		for (int i = 0; i < cl.length; i++) {
			il[i] = cl[i] - 48;
			bl[i]=il[i].byteValue();
		}
		System.out.println("LLLLLLL");
	}
}
