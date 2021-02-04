package com.fredrikpedersen.module2.chapter1;


public class Main {

	public static void main(String[] args) throws Exception {

		try {
			ConnectComponent comp = new ConnectComponent();

			if (comp.tryConnection()) {
				System.out.println("Demo m2c1:");
				System.out.println("The attempt to Connection was a SUCCESS");
			} else {
				System.out.println("Demo m2c1:");
				System.out.println("The attempt to Connection FAILED");
			}
		} catch (Exception exception) {
			util.ExceptionHandler.handleException(exception);
		}
	}

}
