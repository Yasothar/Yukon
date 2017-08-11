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
public class Sample {

    public static void main(String[] args) throws InterruptedException {

        Service s = new Service(9000, "localhost");

        Calendar cal = Calendar.getInstance();

        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.SECOND, 5);

        Date graceTime = cal1.getTime();
        System.out.println("Current Time ::" + cal.getTime());
        System.out.println("Grace Time ::" + graceTime);

        Monitor m = new Monitor(s, graceTime);
        //m.checkifServiceUp(s);
        m.RegisterInterest(s, 50);
    }
}