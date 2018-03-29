package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.businesstier.service.EventServiceImpl;

/**
 * Junit test case to test class EventServiceImpl
 *
 */
public class TestEventServiceImpl {

	private List<Event> eventList;	
	private Visitor visitor;
	private EventServiceImpl eventServiceImpl;

	/**
	 * Set up the objects required before execution of every method
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {		
		eventServiceImpl = new EventServiceImpl();
		visitor = new Visitor();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
	}

	/**
	 * Test case to test the method getAllEvents
	 */
	@Test
	public void testGetAllEvents() {
		/**
		 * @TODO: Call getAllEvents method and assert it for the size of returned array
		 */	
		
		EventServiceImpl n=new EventServiceImpl();
		/*Event e=new Event();
		e.setEventid(1001);
		Event a=new Event();
		a.setEventid(1002);
		Event s=new Event();
		s.setEventid(1003);
		Event k=new Event();
		k.setEventid(1004);
		Event d=new Event();
		d.setEventid(1005);
		Event f=new Event();
		f.setEventid(1006);*/
		
		List<Event> expected= n.getAllEvents();
		//List<Event> actual = Arrays.asList(e,a,s,k,d,f);
        //List<Event> expected = Arrays.asList(e,a,s,k,d,f);
		
        assertEquals(expected.size(), 6);
		
	}

	

	/**
	 * Test case to test the method checkEventsofVisitor
	 
	 */
	@Test
	public void testCheckEventsofVisitor() {
		/**
		 * @TODO: Call checkEventsofVisitor and assert the returned type of this method
		 * for appropriate return type
		 */		
		EventServiceImpl p=new EventServiceImpl();
		Visitor v= new Visitor();
		v.setUserName("jjones");
		
		
		assertTrue(p.checkEventsofVisitor(v ,10003));
		
	}

	/**
	 * Test case to test the method updateEventDeletions
	 * 
	 */
	@Test
	public void testUpdateEventDeletions() {
		/**
		 * @TODO: Call updateEventDeletions and assert the return type of this method
		 * 
		 */
		
		EventServiceImpl es=new EventServiceImpl();
		
		
		
	}

}
