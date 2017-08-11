/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yukon.monitoapp.test;

import java.util.Date;
import java.util.Calendar;
import com.yukon.monitorapp.Service;
import com.yukon.monitorapp.Monitor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;

/**
 *
 * @author 10938
 */
public class MonitorTest {

    public MonitorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void checkIfServiceIsUp() {
        Monitor m = Mockito.mock(Monitor.class);
        Service s = new Service(9000, "localhost");
        m.checkifServiceUp(s);
        Mockito.verify(m).checkifServiceUp(s);
    }

    @Test
    public void testMonitorAndgraceTime() {

        Calendar cal = Calendar.getInstance();
        cal.getTime();
        Calendar cal2 = Calendar.getInstance();
        cal2.getTime();
        cal2.set(Calendar.MINUTE, ((Calendar.MINUTE) + 15));

        Date date1 = cal2.getTime();
        Service s = new Service(80, "10.110.5.104");
        Monitor m = new Monitor(s, date1);
        m.checkifServiceUp(s);
        m.notifyCaller();
    }
    
    @Test
    public void testRegisterInterest() throws InterruptedException {
        Monitor m = Mockito.mock(Monitor.class);
        Service s = new Service(90000, "10.110.5.103");
        m.RegisterInterest(s, 10);
        Mockito.verify(m).RegisterInterest(s, 10);
    }
}
