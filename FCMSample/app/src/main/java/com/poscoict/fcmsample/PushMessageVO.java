package com.poscoict.fcmsample;

/**
 * Created by 장 기효 on 2016-12-26.
 */

public class PushMessageVO {
    private String FCM_TOKEN;
    private String DEV_ID;
    private String OS_VERSION;
    private String PHONE_NUMBER;

    public PushMessageVO(String dev_id, String os_version, String phone_number) {
        DEV_ID = dev_id;
        OS_VERSION = os_version;
        PHONE_NUMBER = phone_number;
    }

    public String getFCM_TOKEN() {
        return FCM_TOKEN;
    }

    public void setFCM_TOKEN(String FCM_TOKEN) {
        this.FCM_TOKEN = FCM_TOKEN;
    }

    public String getDEV_ID() {
        return DEV_ID;
    }

    public void setDEV_ID(String DEV_ID) {
        this.DEV_ID = DEV_ID;
    }

    public String getOS_VERSION() {
        return OS_VERSION;
    }

    public void setOS_VERSION(String OS_VERSION) {
        this.OS_VERSION = OS_VERSION;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public void setPHONE_NUMBER(String PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }
}
