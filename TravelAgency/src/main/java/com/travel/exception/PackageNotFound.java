package com.travel.exception;

public class PackageNotFound extends RuntimeException {
	
	public PackageNotFound(String msg) {
		super(msg);
	}
}
