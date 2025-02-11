package com.example.payments.security;

public class JWT {

	private String header;
	private String payload;
	private String signature;

	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public JWT(String header, String payload, String signature) {
		super();
		this.header = header;
		this.payload = payload;
		this.signature = signature;
	}
	public JWT() {
		super();
	}
	
	
}
