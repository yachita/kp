package com.accenture.adf.businesstier.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.exceptions.FERSGenericException;
import com.accenture.adf.helper.FERSDataConnection;
import com.accenture.adf.helper.FERSDbQuery;

/**
 * 
 * <br/>
 * CLASS DESCRIPTION:<br/>
 * A Data Access Object (DAO) class for handling and managing visitor related data requested, 
 * used, and processed in the application and maintained in the database.  
 * The interface between the application and visitor data persisting in the database.
 * 
 */
 
public class VisitorDAO {

// LOGGER for handling all transaction messages in VISITORDAO
private static Logger log = Logger.getLogger(VisitorDAO.class);

//JDBC API classes for data persistence
private Connection connection = null;
private PreparedStatement statement = null;
private ResultSet resultSet = null;
private FERSDbQuery query;

//Default constructor for injecting Spring dependencies for SQL queries
public VisitorDAO() {
ApplicationContext context = new ClassPathXmlApplicationContext(
"applicationContext.xml");
query = (FERSDbQuery) context.getBean("SqlBean");
}

/**
* <br/>
*  METHOD DESCRIPTION:<br/>
* DAO method to loading visitor details into VISITOR table in database<br/>
* and validating about existing visitor details before inserting a visitor <br/>
*  
*  <br/>
*  PSEUDOCODE: <br/>
*  Create a connection to database<br/>
*  Prepare a statement object using the connection that uses a query that inserts visitor information <br/>
*  into the visitor table <br/>
*  Execute a statement object selects all the usernames from the visitor table<br/>
*  if the username is not in the visitor table <br/>
* 
* @param visitor (type Visitor)
* 
* @return boolean
* 
* @throws ClassNotFoundException
* @throws SQLException
* @throws Exception
*   
*/
public boolean insertData(Visitor visitor) throws ClassNotFoundException,
SQLException, Exception {
connection = FERSDataConnection.createConnection();
statement = connection.prepareStatement(query.getInsertQuery());
statement.setString(1, visitor.getUserName());
statement.setString(2, visitor.getPassword());
statement.setString(3, visitor.getFirstName());
statement.setString(4, visitor.getLastName());
statement.setString(5, visitor.getEmail());
statement.setString(6, visitor.getPhoneNumber());
statement.setString(7, visitor.getAddress());
int result = statement.executeUpdate();
if (result>0)
return true;
return false;
}

/**
*  <br/>
*  METHOD DESCRIPTION:<br/>
*  DAO method for searching for visitor details using USERNAME and PASSWORD<br/>
* 
* <br/>
*  PSEUDOCODE: <br/>
*  Create a connection to database<br/>
*  Prepare a statement object using the connection<br/>
*  that uses a query that retrieves all the data from the visitor 
*  table based on the username and password provided. Execute the query and <br/>
*  Using a WHILE LOOP, store the results in the result set record in the visitor object.<br/>
*  
* @param username (type String)
* @param password (type String)
* 
* @return Visitor
* 
* @throws ClassNotFoundException
* @throws SQLException
* 
* 
*/
public Visitor searchUser(String username, String password)
throws ClassNotFoundException, SQLException {
Visitor visitor = new Visitor();
connection = FERSDataConnection.createConnection();
statement = connection.prepareStatement(query.getSearchQuery());
statement.setString(1, username);
statement.setString(2, password);
ResultSet r= statement.executeQuery();
while(r.next()){
visitor.setVisitorId(r.getInt(1));
visitor.setUserName(r.getString(2));
visitor.setPassword(r.getString(3));
visitor.setFirstName(r.getString(4));
visitor.setLastName(r.getString(5));
visitor.setEmail(r.getString(6));
visitor.setPhoneNumber(r.getString(7));
visitor.setAddress(r.getString(8));
}
// TODO:  Add code here.....
// TODO:  Pseudo-code are in the block comments above this method
// TODO:  For more comprehensive pseudo-code with details, refer to the Component/Class Detailed Design Document   
return visitor;
}

/**
*  <br/>
*  METHOD DESCRIPTION: <br/>
*  DAO method to register visitor to specific event and checking about status
*  of visitor to particular event. <br/>
*  
*  PSEUDO-CODE: <br/>
*  Create a connection to the database <br/>
*  Prepare a statement object using the connection: that inserts the   
*     visitor and event IDs into theExecute the query to perform the  EVENTSESSIONSIGNUP table <br/>
*  Execute the query to perform the update <br/>
*  
* 
*  @param visitor
*  @param eventid
*   
*  @throws ClassNotFoundException
*  @throws SQLException
*  @throws Exception
*  
*/

public void registerVisitorToEvent(Visitor visitor, int eventid)
throws ClassNotFoundException, SQLException, Exception {

connection = FERSDataConnection.createConnection();
statement = connection.prepareStatement(query.getRegisterQuery());
statement.setInt(2,visitor.getVisitorId());
statement.setInt(1, eventid);
  
int theExecute= statement.executeUpdate();
// TODO:  Add code here.....
// TODO:  Pseudo-code are in the block comments above this method
// TODO:  For more comprehensive pseudo-code with details, refer to the Component/Class Detailed Design Document   
}

/**
*  <br/>
*  METHOD DESCRIPTION:<br/>
*  DAO method to display all the events registered by particular visitor<br/>
*  
*  PSEUDO-CODE: <br/>
*  Create a connection to the database <br/>
*  Prepare a statement object using the connection: that returns the event   
*     information for all the events that are registered to a visitor<br/>
*  Execute the query to retrieve the results into a result set<br/>
*  Place each event record‘s information in an event list. <br/>
*  
* @param  visitor (type Visitor)
*  
*  @return Collection of Event Arrays (type Event)
*  
*  @throws ClassNotFoundException
*  @throws SQLException
*  
*/
public ArrayList<Event> registeredEvents(Visitor visitor)
throws ClassNotFoundException, SQLException {
connection = FERSDataConnection.createConnection();
statement = connection.prepareStatement(query.getStatusQuery());
statement.setInt(1, visitor.getVisitorId());
ResultSet rs= statement.executeQuery();
ArrayList<Event> Arr= new ArrayList<>();
while(rs.next()){
Event event = new Event();
event.setEventid(rs.getInt(1));
event.setName(rs.getString(2));
event.setDescription(rs.getString(3));
event.setPlace(rs.getString(4));
event.setDuration(rs.getString(5));
event.setEventtype(rs.getString(6));
event.setSignupid(rs.getInt(7));
Arr.add(event);
} 
// TODO:  Add code here.....
// TODO:  Pseudo-code are in the block comments above this method
// TODO:  For more comprehensive pseudo-code with details, refer to the Component/Class Detailed Design Document   
return Arr; 
}

/**
* <br/>
*  METHOD DESCRIPTION:<br/>
* DAO method to update visitor with additional information <br/>
*  <br/>
*  
*  @param visitor (type Visitor)
* 
* @return int
* 
* @throws ClassNotFoundException
* @throws SQLException
* 
*   
*/
public int updateVisitor(Visitor visitor) throws ClassNotFoundException,
SQLException {
connection = FERSDataConnection.createConnection();
statement = connection.prepareStatement(query.getUpdateQuery());
System.out.println(visitor.getFirstName()+" "+visitor.getLastName()+" "+visitor.getUserName()+" "
		+" "+visitor.getPassword()+" "+visitor.getEmail()+" "+visitor.getPhoneNumber()+" "+visitor.getAddress()
		+" "+visitor.getVisitorId());
statement.setString(1, visitor.getFirstName());
statement.setString(2, visitor.getLastName());
statement.setString(3, visitor.getUserName());
statement.setString(4, visitor.getPassword());
statement.setString(5, visitor.getEmail());
statement.setString(6, visitor.getPhoneNumber());
statement.setString(7, visitor.getAddress());
statement.setInt(8, visitor.getVisitorId());

int status = statement.executeUpdate();
log.info("Updating visitor details in Database for Visitor ID :"
+ visitor.getVisitorId());
FERSDataConnection.closeConnection();
return status;
}

/**
*  <br/>
*  METHOD DESCRIPTION: <br/>
*  DAO method to unregister from events <br/>
*  
*     
*  @param  visitor (type Visitor)
*  @param  eventid (type int)
*    
*  @throws ClassNotFoundException
*  @throws SQLException
*  @throws Exception
*  
*/
public void unregisterEvent(Visitor visitor, int eventid)
throws ClassNotFoundException, SQLException, Exception {
connection = FERSDataConnection.createConnection();
statement = connection.prepareStatement(query.getDeleteEventQuery());
statement.setInt(1, eventid);
statement.setInt(2, visitor.getVisitorId());
int status = statement.executeUpdate();
if (status <= 0)
throw new FERSGenericException("Records not updated properly",
new Exception());
log.info("unregistering event in Database for the visitor :"
+ visitor.getFirstName());
FERSDataConnection.closeConnection();
} 

public int changePassword(Visitor visitor) throws ClassNotFoundException, SQLException {	
	
	//TODO: Declare a variable with name status of type int and initialize with -1
		int status = -1;
		//TODO: Start a try block
		
		//TODO: Get a database connection from FERSDataConnection class
			connection = FERSDataConnection.createConnection();

		if(connection != null){
			
	//TODO: Check if Visitor object is null and log an error message ‘Visitor details are invalid’ to log file
			if(visitor == null) {
				log.error("Visitor details are invalid");

			}else
			if(matchWithOldPwd(visitor)){
				status = -10;
			}else{
				statement = connection.prepareStatement(query.getChangePWDQuery());

				//TODO: create a prepared statement with password change query
				statement.setString(1, visitor.getPassword());
				statement.setInt(2, visitor.getVisitorId());
	//TODO: Set the values of visitor and password to the query parameters
				 status = statement.executeUpdate();
				//if (status > 0) {
					log.info("Visitor password successfully modified"+visitor.getVisitorId());

				//}
				//TODO: Execute the password change query
				
	//TODO: Print a log message ‘Visitor password successfully modified’ along with visitor ID
			}
		}else{ 
	throw new SQLException("Connection Error, could not establish connection with database");
		 }
		
		
	//TODO: Add a finally block to clean up prepared statement and connection objects
		
		//TODO: Return status value
		return status;
	}

	private boolean matchWithOldPwd(Visitor visitor) throws SQLException{
		String currentPWD = "";
		try{

			//TODO: Create a prepared statement with with verify password query
			statement = connection.prepareStatement(query.getVerifyPWDQuery());
			statement.setInt(1, visitor.getVisitorId());
			//TODO: Set the visitor ID as parameter to the query
			resultSet = statement.executeQuery();
	//TODO: Execute the query and retrieve the current password if resultset  has values
			while(resultSet.next()) {
				currentPWD = resultSet.getString(1);
			}
			if(currentPWD.equalsIgnoreCase(visitor.getPassword())){
	log.info("New password must be different from previous password, please choose a different password");
				
	return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		//TODO: Add a finally block to clean up prepared statement
		
		//TODO: Return false after finally block
		return false;

	}

}