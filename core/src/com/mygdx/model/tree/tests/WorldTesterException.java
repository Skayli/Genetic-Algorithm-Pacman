package com.mygdx.model.tree.tests;

@SuppressWarnings("serial")
public class WorldTesterException extends Exception {
	
	private String errMessage;
	
	public WorldTesterException(String message) {
		errMessage = message;
	}
	
	public void printStackTrace() {
		System.err.println("WorldTester error : " + errMessage);
	}

}
