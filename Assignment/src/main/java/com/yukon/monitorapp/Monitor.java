/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yukon.monitorapp;

import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  
 */
public class Monitor {

    private Service service;
    private int pollFrequency;
    private Date graceTime;

    /**
     * Constructor of the Monitor class, takes in a Service
     * and a grace time for that particular service 
     */
    public Monitor(Service service, Date graceTime) {
        this.service = service;
        this.graceTime = graceTime;
    }

    public Service getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * @return the pollFrequency
     */
    public int getPollFrequency() {
        return pollFrequency;
    }

    /**
     * @param pollFrequency the pollFrequency to set
     */
    public void setPollFrequency(int pollFrequency) {
        this.pollFrequency = pollFrequency;
    }

    /**
     * @return the graceTime
     */
    public Date getGraceTime() {
        return graceTime;
    }

    /**
     * @param graceTime the graceTime to set
     */
    public void setGraceTime(Date graceTime) {
        this.graceTime = graceTime;
    }
    
    
    public void checkifServiceUp(final Service service) {

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new Callable<Void>() {

            @Override
            public Void call() {

                Calendar cal = Calendar.getInstance();
                Date currTime = cal.getTime();
                //Check if the current time falls between service down time, if yes send a halt notification
                if (currTime.after(service.getDownTimestart()) && currTime.before(service.getDownTimeEnd())) {
                    service.haltNotification();
                } else {
                    Socket skt = null;
                    try {
                        skt = new Socket(service.getHost(), service.getPortNo());
                        if (skt.isConnected()) {
                            notifyCaller();
                            skt.close();

                        }
                    } catch (Exception e) {
                        Long tempTime = timeToExpire(graceTime) / 1000;
                        if (tempTime > 0) {
                            try {
                                System.out.println("Waiting " + tempTime + " seconds!");
                                TimeUnit.SECONDS.sleep(tempTime);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        System.out.println("Unable to connect to host :" + service.getHost() + " port " + service.getPortNo());
                    }
                }
                return null;
            }
        });
        executor.shutdown();
    }

    public void notifyCaller() {
        System.out.println(service.getHost() + "  " + service.getPortNo() + " caller has been notified");

    }
    
    /*
     * A user can call this method and assign a service to be monitored, with a polling frquency
    */
    public void RegisterInterest(Service sm, int pollFreq) throws InterruptedException {

        this.pollFrequency = pollFreq;
        while (pollFrequency > 0) {
            
            /*
             * check if a grace time is set then wait 
             */
            Long tempTime = timeToExpire(graceTime) / 1000;
            if (tempTime > 0) {
                System.out.println("Waiting " + tempTime + " seconds!");
                TimeUnit.SECONDS.sleep(tempTime);
            }
            checkifServiceUp(sm);
            //poll every one second for the service
            TimeUnit.SECONDS.sleep(1);
            pollFrequency--;
        }
    }
    
    /*
     * When a grace time is provided, get the actual long time to wait
     * by substracting it from the current time
     */
    public Long timeToExpire(Date graceTime) {

        Long expTime = 0L;
        Calendar cal = Calendar.getInstance();
        Date currentTime = cal.getTime();

        if (graceTime.after(currentTime)) {
            expTime = graceTime.getTime() - currentTime.getTime();
        }
        return expTime;
    }
}