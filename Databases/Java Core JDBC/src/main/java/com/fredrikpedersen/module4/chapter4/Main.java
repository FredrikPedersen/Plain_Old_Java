package com.fredrikpedersen.module4.chapter4;

public class Main {

	public static void main(String[] args) throws Exception {

		try {
			ProductsComponent comp = new ProductsComponent();
			comp.printProductList(50.0, 100.0);
		} catch (Exception exception) {
			util.ExceptionHandler.handleException(exception);
		}

	}
}
