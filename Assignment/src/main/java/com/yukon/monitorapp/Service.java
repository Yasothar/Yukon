/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yukon.monitorapp;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author 10938
 */
public class Service {

    private int portNo;
    private String host;
    private Date downTimestart;
    private Date downTimeEnd;

    public Service(int portNo, String host) {
        this.portNo = portNo;
        this.host = host;
        /*
         * set downtime start and downtime end to current time
         * if no values are provided in the constructor
         */
        Calendar cal = Calendar.getInstance();
        this.downTimestart = cal.getTime();
        this.downTimeEnd = cal.getTime();

    }

    public Service(int portNo, String host, Date downTimeStart, Date downTimeEnd) {
        this.portNo = portNo;
        this.host = host;
        this.downTimestart = downTimeStart;
        this.downTimeEnd = downTimeEnd;

    }

    public void planOutage(Date start, Date end) {
        this.setDownTimestart(start);
        this.setDownTimeEnd(end);
    }

    void haltNotification() {
        System.out.println("Caller cannot be notified, Notification service is unavailable..!");
    }

    public long dateTolong(Date date) {
        long longDate = date.getTime();
        return longDate;
    }

    /**
     * @return the portNo
     */
    public int getPortNo() {
        return portNo;
    }

    /**
     * @param portNo the portNo to set
     */
    public void setPortNo(int portNo) {
        this.portNo = portNo;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the downTimestart
     */
    public Date getDownTimestart() {
        return downTimestart;
    }

    /**
     * @param downTimestart the downTimestart to set
     */
    public void setDownTimestart(Date downTimestart) {
        this.downTimestart = downTimestart;
    }

    /**
     * @return the downTimeEnd
     */
    public Date getDownTimeEnd() {
        return downTimeEnd;
    }

    /**
     * @param downTimeEnd the downTimeEnd to set
     */
    public void setDownTimeEnd(Date downTimeEnd) {
        this.downTimeEnd = downTimeEnd;
    }
}