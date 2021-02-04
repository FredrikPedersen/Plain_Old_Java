package com.fredrikpedersen.module6.chapter2;

public class Main {

	public static void main(String[] args) throws Exception {
		try {

			ProductComponent comp = new ProductComponent();

			comp.listProductsBy("Motorcycles");

		} catch (Exception exception) {
			util.ExceptionHandler.handleException(exception);
		}
	}
}
