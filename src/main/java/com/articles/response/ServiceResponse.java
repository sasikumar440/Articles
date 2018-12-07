package com.articles.response;

public class ServiceResponse<T> {

private String status;
private String statusMessage;
private T payload;

public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getStatusMessage() {
	return statusMessage;
}
public void setStatusMessage(String statusMessage) {
	this.statusMessage = statusMessage;
}
public T getPayload() {
	return payload;
}
public void setPayload(T payload) {
	this.payload = payload;
}

}


