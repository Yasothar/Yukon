/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yukon.monitoapp.test;

import org.mockito.Mockito;
import java.util.Date;
import java.util.Calendar;
import com.yukon.monitorapp.Service;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 10938
 */
public class ServiceTest {

    Service s1 = new Service(445, "10.110.6.49");
    Service s2 = new Service(80, "10.110.6.49");
    int servicePort = s1.getPortNo();
    String serviceHost = s1.getHost();

    public ServiceTest() {
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
    public void testService() {
        // test for service s port#=445,host=localhost and if service is connected(pre=service is up)

        assertTrue(serviceHost == "10.110.6.49");
        assertTrue(servicePort == 445);
    }

    @Test
    public void testGetHost() {
        assertTrue(serviceHost == "10.110.6.49");
    }

    @Test
    public void testGetportNum() {
        assertTrue(servicePort == 445);
    }

    @Test
    public void testSetHost() {
        Service s = new Service(445, "localhost");
        s.setHost("www.google.com");
        assertEquals("www.google.com", s.getHost());
    }

    @Test
    public void testSetPortNum() {
        s1.setPortNo(20);
        assertTrue(s1.getPortNo() == 20);
    }

    @Test
    public void testPlanOutage() {

        Calendar cal = Calendar.getInstance();
        cal.getTime();
        Calendar cal2 = Calendar.getInstance();
        cal2.getTime();
        cal2.set(Calendar.MINUTE, ((Calendar.MINUTE) + 5));

        Date start = cal.getTime();
        Date end = cal2.getTime();

        Service s = Mockito.mock(Service.class);
        s.planOutage(start, end);
        Mockito.verify(s).planOutage(start, end);

    }
}
