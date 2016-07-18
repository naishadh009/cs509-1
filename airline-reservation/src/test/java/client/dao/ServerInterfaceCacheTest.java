package client.dao;

import java.text.ParseException;
import java.util.ArrayList;

import org.ehcache.Cache;

import client.flight.Flight;
import client.reservation.ReservationOptionTest;
import client.search.FlightSearch;
import client.util.Configuration;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ServerInterfaceCacheTest extends TestCase{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     * @return 
     */
	public ServerInterfaceCacheTest( String testName ){
		super(testName);
	}
	
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ServerInterfaceCacheTest.class );
    }
    
    /**
     * test the working of the cache 
     */
    public void testCache(){
    	
    	long startTime;
    	long endTime;
    	ServerInterfaceCache serverInterfaceCache=ServerInterfaceCache.getInstance();
    	FlightSearch search=new FlightSearch("BOS","LGA","2016 May 10 03:05 GMT", "Coach");
    	
   	    //reseting the database to its original state
   	    boolean resetCheck=serverInterfaceCache.resetDB(Configuration.TICKET_AGENCY);
   	    assertEquals("Not able to ResetDB to original state",true,resetCheck);
   	    
   	    
   	    //calling the search for the first time,where the both the flight and airport cache are initialized for the first time
   
   	    try {
   	    	startTime = System.currentTimeMillis();
			search.getOptions();
			endTime = System.currentTimeMillis();
			long timeFirstRun=endTime-startTime;
			startTime = System.currentTimeMillis();
			search.getOptions();
			endTime = System.currentTimeMillis();
			long timeSecondRun=endTime-startTime;
			assertTrue("Cache not working",timeFirstRun>timeSecondRun);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

}
