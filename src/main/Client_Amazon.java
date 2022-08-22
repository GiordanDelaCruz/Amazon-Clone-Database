package main;

import java.sql.*;
import java.util.Scanner;  

public class Client_Amazon {

	public static void main(String[] args) {
		
		boolean continueFlag = true ;
		String clientChar = "";
		int queryNum = 0;
		String MENU = "[1] How many customer accounts are registered in the DB\n"
				+ "[2] How many products have a rating attribute is equal to 5\n"
				+ "[3] How many products have a rating attribute is greater than or equal 3\n"
				+ "[4] Find the most expensive product\n"
				+ "[5] How many products are in the electronics genre\n"
				+ "[6] How many user accounts are from Canada\n"
				+ "[7] List of all the items that start with the letter d\n"
				+ "[8] List of all the customers with emails that start with the letter a\n"
				+ "[9] How many males are in Canada\n"
				+ "[10] How many males are in Canada\n";
		
		Scanner input = new Scanner(System.in);
		
		// Get option of query from user until they wish to quit
		while(continueFlag) {
			// Print out menu
			System.out.println(MENU);
			
			// Get query number from user
			System.out.println("Please enter the query number you would like(e.g. 3): ");
			queryNum = input.nextInt();
			
			executeQuery(queryNum);
			
			// Determine if user would like to quit
			System.out.println("\nWould you like to continue? [y, n]");
			clientChar = input.next();
			if( clientChar.equals("n") ) {
				continueFlag = false;
			}	
		}// End of while loop
		
		// Goodbye message
		System.out.println("\nGoodbye! Thank you for using our system!");
		
		input.close();
		
	}// End of main
	
	
	/**
	 *  Execute the requested sql query
	 *  @param		option: int
	 */
	public static void executeQuery(int caseNum) {
		
		try {
			// Connect to Database
			String url_db = "jdbc:sqlite:C:\\Users\\Giordan\\sqlite\\gui\\SQLiteStudio\\amazon-db-2";
//			String url_db = "jdbc:sqlite:amazon-db-2.db";
	        Connection conn = DriverManager.getConnection(url_db);
	        
	        switch(caseNum) {
	        	
	        	// QUERY #1: How many customer accounts are registered in the DB
				case 1:
					try {
				        // Execute Query 
				        Statement stmt = conn.createStatement();	        
				        String query = "SELECT COUNT(*) AS rowcount FROM CUSTOMER";
				        ResultSet rs = stmt.executeQuery(query); 
				        
				        while(rs.next() ) {
				        	// Print out result
				        	int count = rs.getInt("rowcount");
				        	System.out.println("There are " + count + " number of users registered in the Database.");
				        }
	
				    } catch(SQLException ex) {
					    	  ex.printStackTrace();
					} 
					break;	
				
				// QUERY #2: How many products have a rating attribute is equal to 5
				case 2:
					try {
				        // Execute Query 
				        Statement stmt = conn.createStatement();	        
				        String query = "SELECT COUNT(*) as rowcount FROM PRODUCT WHERE rating == 5";
				        ResultSet rs = stmt.executeQuery(query); 
				        
				        while(rs.next() ) {
				        	// Print out result
				        	int count = rs.getInt("rowcount");
				        	System.out.println("There are " + count + " number of products with a rating of 5.");
				        }
	
				    } catch(SQLException ex) {
					    	  ex.printStackTrace();
					} 
					break;	
					
				// QUERY #3: How many products have a rating attribute is greater than or equal 3
				case 3:
					try {
				        // Execute Query 
				        Statement stmt = conn.createStatement();	        
				        String query = "SELECT COUNT(*) as rowcount FROM PRODUCT WHERE rating >= 3";
				        ResultSet rs = stmt.executeQuery(query); 
				        
				        while(rs.next() ) {
				        	// Print out result
				        	int count = rs.getInt("rowcount");
				        	System.out.println("There are " + count + " number of products with a rating greater than or equal than 3.");
				        }
	
				    } catch(SQLException ex) {
					    	  ex.printStackTrace();
					} 
					break;	
					
				// QUERY #4: Find the most expensive product
				case 4:
					try {
				        // Execute Query 
				        Statement stmt = conn.createStatement();	        
				        String query = "SELECT MAX(price) as price, productName FROM PRODUCT";
				        ResultSet rs = stmt.executeQuery(query); 
				        
				        while(rs.next() ) {
				        	// Print out result
				        	String name = rs.getString("productName");
				        	double price = rs.getDouble("price");
				        	System.out.println("The most expensive product in the database is " + name + " with a price of $" + price + 
				        			'.');
				        }
	
				    } catch(SQLException ex) {
					    	  ex.printStackTrace();
					} 
					break;	
					
				// QUERY #5: How many products are in the electronics genre 
				case 5:
					try {
				        // Execute Query 
				        Statement stmt = conn.createStatement();	        
				        String query = "SELECT COUNT(*) AS rowcount FROM PRODUCT WHERE genre == 'electronics'";
				        ResultSet rs = stmt.executeQuery(query); 
				        
				        while(rs.next() ) {
				        	// Print out result
				        	int rowcount = rs.getInt("rowcount");
				        	System.out.println("There are " + rowcount + " many products in the electronics genre.");
				        }
	
				    } catch(SQLException ex) {
					    	  ex.printStackTrace();
					} 
					break;	
					
				// QUERY #6: How many user accounts are from Canada 
				case 6:
					try {
				        // Execute Query 
				        Statement stmt = conn.createStatement();	        
				        String query = "SELECT COUNT(*) AS rowcount FROM CUSTOMER WHERE country == 'CA'";
				        ResultSet rs = stmt.executeQuery(query); 
				        
				        while(rs.next() ) {
				        	// Print out result
				        	int rowcount = rs.getInt("rowcount");
				        	System.out.println("There are " + rowcount + " many users living in Canada.");
				        }
	
				    } catch(SQLException ex) {
					    	  ex.printStackTrace();
					} 
					break;	
				
				// QUERY #7: List of all the items that start with the letter d
				case 7:
					try {
				        // Execute Query 
				        Statement stmt = conn.createStatement();	        
				        String query = "SELECT * FROM PRODUCT WHERE productName like 'd%'";
				        ResultSet rs = stmt.executeQuery(query); 
				        
				        System.out.println("List of products that start with d");
				        while(rs.next() ) {
				        	// Print out result
				        	String name = rs.getString("productName");
				        	System.out.println(name);
				        }
	
				    } catch(SQLException ex) {
					    	  ex.printStackTrace();
					} 
					break;
					
				// QUERY #8: List of all the customers with emails that start with the letter a
				case 8:
					try {
				        // Execute Query 
				        Statement stmt = conn.createStatement();	        
				        String query = "SELECT * FROM CUSTOMER WHERE email like 'a%'";
				        ResultSet rs = stmt.executeQuery(query); 
				        
				        System.out.println("List of emails that start with d");
				        while(rs.next() ) {
				        	// Print out result
				        	String email = rs.getString("email");
				        	System.out.println(email);
				        }
	
				    } catch(SQLException ex) {
					    	  ex.printStackTrace();
					} 
					break;
					
				// QUERY #9: How many males are in Canada
				case 9:
					try {
				        // Execute Query 
				        Statement stmt = conn.createStatement();	        
				        String query = "SELECT * FROM CUSTOMER WHERE gender == 'M' AND country == 'CA'";
				        ResultSet rs = stmt.executeQuery(query); 
				        
				        System.out.println("List of males who live in Canada.");
				        while(rs.next() ) {
				        	// Print out result
				        	String gender = rs.getString("gender");
				        	String name = rs.getString("name");
				        	System.out.println(gender + "\t" +  name);
				        }
	
				    } catch(SQLException ex) {
					    	  ex.printStackTrace();
					} 
					break;
					
				// QUERY #10: How many males are in Canada
				case 10:
					try {
				        // Execute Query 
				        Statement stmt = conn.createStatement();	        
				        String query = "SELECT * FROM CUSTOMER WHERE gender == 'F' AND country == 'USA'";
				        ResultSet rs = stmt.executeQuery(query); 
				        
				        System.out.println("List of females who live in USA.");
				        while(rs.next() ) {
				        	// Print out result
				        	String gender = rs.getString("gender");
				        	String name = rs.getString("name");
				        	System.out.println(gender + "\t" +  name);
				        }
	
				    } catch(SQLException ex) {
					    	  ex.printStackTrace();
					} 
					break;
					
	        }// End of Switch Case
	        
	    } catch(SQLException ex) {
		    	  ex.printStackTrace();
		} 
		
		
	}
	
}

