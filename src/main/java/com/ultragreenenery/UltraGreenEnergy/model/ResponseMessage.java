package com.ultragreenenery.UltraGreenEnergy.model;

public class ResponseMessage {
private  String responseMessage;
public ResponseMessage(String responseMessage){

    this.responseMessage=responseMessage;
}
//    public String getResponseMessage() {
//        return responseMessage;
//    }
//
//    public void setResponseMessage(String responseMessage) {
//        this.responseMessage = responseMessage;
//    }
//
//    public int getResponseStatus() {
//        return responseStatus;
//    }
//
//    public void setResponseStatus(int responseStatus) {
//        this.responseStatus = responseStatus;
//    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
