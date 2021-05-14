package com;

import java.sql.*;

public class Proposal {
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gb_projects", "root", "");

			System.out.print("Successfully connected");
		} catch (Exception e) {
			System.out.print("Connection Failed");
			e.printStackTrace();
			System.out.print(e);
		}

		return con;
	}
	
	    //View Proposal Details
	
	    public String viewProposals() {
		
		String output = "";
		
		
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ProjectName</th><th>budget</th> "+" <th>CompletionDate</th> "+"<th>productCategory</th> "+" <th>sellOrNot</th> "+" <th>description</th> "+" <th>status</th> "+" <th>userID</th> "+" <th>Update</th>"+"<th>Remove</th></tr>";

			String query = "select * from project_proposals";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String proposal_ID = Integer.toString(rs.getInt("proposal_ID"));
				String ProjectName = rs.getString("ProjectName");
				String budget = Double.toString(rs.getDouble("budget"));
				String CompletionDate = rs.getString("CompletionDate");
				String productCategory = rs.getString("productCategory");
				String sellOrNot = rs.getString("sellOrNot");
				String description = rs.getString("description");
				String status = rs.getString("status");
				String userID = rs.getString("userID");
				
				output += "<tr><td><input id='hidIdProposalUpdate' name='hidIdProposalUpdate' type='hidden' value='" + proposal_ID + "'>" + ProjectName + "</td>";
				// Add into the html table
				//output += "<tr><td>" + pid + "</td>";
				//output += "<td>" + pname + "</td>";
				output += "<td>" + budget + "</td>";
				output += "<td>" + CompletionDate + "</td>";
				output += "<td>" + productCategory + "</td>";
				output += "<td>" + sellOrNot + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + status + "</td>";
				output += "<td>" + userID + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate'  type='button' value='Update' class ='btnUpdate btn btn-secondary' data-proposal_ID='" + proposal_ID + "'></td>"
						 + "<td><form method='post' action='proposal.jsp'>"
						 + "<input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-proposal_ID='" + proposal_ID + "'>"
						 + "<input name='hidProposalDelete' type='hidden' value='" + proposal_ID + "'>" + "</form></td></tr>"; 
						 } 
	
			
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while viewing proposal details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	    //Insert Proposal  Details
	
		public String addProposals(String ProjectName, String budget, String CompletionDate, String productCategory ,String sellOrNot , String description , String status , String userID) {
			
			String output = "";

			try {

				Connection con = connect();

				if (con == null) {

					return "Error while connecting to the database";
				}

				// insert data

				String query = " INSERT INTO project_proposals (ProjectName, budget, CompletionDate, productCategory, sellOrNot, description, status, userID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				//preparedStmt.setInt(1, 0); 
				preparedStmt.setString(1, ProjectName);
				preparedStmt.setDouble(2, Double.parseDouble(budget));
				preparedStmt.setString(3, CompletionDate);
				preparedStmt.setString(4, productCategory);
				preparedStmt.setString(5, sellOrNot);
				preparedStmt.setString(6, description);
				preparedStmt.setString(7, status);
				preparedStmt.setString(8, userID);
				

				// execute the statement
				preparedStmt.execute();
				con.close();
				String newProposal = viewProposals();
				
				output = "{\"status\":\"success\", \"data\": \"" + newProposal + "\"}";
				
			} catch (Exception e) {

				output = "{\"status\":\"error\", \"data\":\"Error while inserting the Proposal.\"}";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		
		//Update Proposal Details

		public String updateProposals(String proposal_ID, String ProjectName, String budget, String CompletionDate, String productCategory ,String sellOrNot , String description, String status, String userID) {

			String output = "";

			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE project_proposals SET ProjectName=?,budget=?,CompletionDate=?,productCategory=?,sellOrNot=?,description=?,status=?,userID=? WHERE proposal_ID =?";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values

				preparedStmt.setString(1, ProjectName);
				preparedStmt.setDouble(2, Double.parseDouble(budget));
				preparedStmt.setString(3, CompletionDate);
				preparedStmt.setString(4, productCategory);
				preparedStmt.setString(5, sellOrNot);
				preparedStmt.setString(6, description);
				preparedStmt.setString(7,status);
				preparedStmt.setString(8,userID);
				preparedStmt.setInt(9, Integer.parseInt(proposal_ID));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newProposal = viewProposals();
				output = "{\"status\":\"success\", \"data\": \"" + newProposal + "\"}";
				
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\":\"Error while updating the proposal.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//Delete QUERY //Not coded
		
		public String deleteProposal(String proposal_ID) {
			String output = "";
			try {

				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				String query = "DELETE FROM project_proposals WHERE proposal_ID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				  preparedStmt.setInt(1, Integer.parseInt(proposal_ID));
				  
				// execute the statement
				preparedStmt.execute();
				con.close();
				 
			     String newProposal = viewProposals();
			     output = "{\"status\":\"success\", \"data\": \"" +
			     newProposal + "\"}";

			} catch (Exception e) {

				output = "{\"status\":\"error\", \"data\":\"Error while deleting the proposal.\"}";
				System.err.println(e.getMessage());
			}

			return output;
		}
	

}
