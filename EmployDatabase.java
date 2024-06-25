package com.jdbc;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployDatabase {	
	
   private static Connection connection =null;
   private static Scanner scanner=new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		
		EmployDatabase employDatabase = new EmployDatabase();
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		String dbURL ="jdbc:mysql://localhost:3306/sumit";
		String username="root";
		String password="root";
		connection=DriverManager.getConnection(dbURL,username,password);

        boolean running = true;

        while (running) {
        	System.out.println("----------------------------------------------------------------");
            System.out.println("Enter Choice:");
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Insert Record");
            System.out.println("2. Select Record");
            System.out.println("3. Update Record");
            System.out.println("4. Delete Record");
            System.out.println("5. Exit");
            System.out.println("----------------------------------------------------------------");
            
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    employDatabase.insertRecord();
                    break;
                case 2:
                    employDatabase.selectRecord();
                    break;
                case 3:
                    employDatabase.updateRecord();
                    break;
                case 4:
                    employDatabase.deleteRecord();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                   
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

		public void insertRecord() throws SQLException
	{
		System.out.println("Inside Insert Record ");
		
		String sql="insert into emp(ename, eaddress,econtact,eage)values(?,?,?,?)";
		
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		System.out.println("Enter Name : ");
		preparedStatement.setString(1,scanner.nextLine());
		
		System.out.println("Enter Address : ");
		preparedStatement.setString(2,scanner.nextLine());
		
		System.out.println("Enter Contact Number : ");
		preparedStatement.setString(3,scanner.nextLine());
		
		System.out.println("Enter Age : ");
		preparedStatement.setString(4,scanner.nextLine());
		
		int rows=preparedStatement.executeUpdate();
		
		if(rows>0) {
			System.out.println("Record Inserted Successfully....");
			
		}
		
	}
		
		public void selectRecord() throws SQLException
		{
			System.out.println("Enter the Employ id to Get the Employ Details  :");
			int number=Integer.parseInt(scanner.nextLine());
			String sql="select * from emp where eid =  "+number;
			Statement statement=connection.createStatement();
			
			ResultSet result=statement.executeQuery(sql);
			
			while(result.next())
			{
				int empid=result.getInt("eid");
				String name=result.getString("ename");
				String address=result.getString("eaddress");
				String contact=result.getString("econtact");
				String age=result.getString("eage");
				System.out.println("Employe id is :"+ empid);
				System.out.println("Employe Name is :"+ name);
				System.out.println("Employe Address is :"+ address);
				System.out.println("Employe Contact is :"+ contact);
				System.out.println("Employe age is :"+ age);
			}
			
			
		}
		
		private void selectAllRecord() throws SQLException
		{
			
		
			String sql="select * from emp ";
			Statement statement=connection.createStatement();
			
			ResultSet result=statement.executeQuery(sql);
			
		}
		
		private void updateRecord() throws SQLException
		{
			System.out.println("Enter Employe id to update record : ");
			int empId=Integer.parseInt(scanner.nextLine());
			
			String sql="select * from emp where eid = "+ empId;
			
			Statement statement= connection.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next())
			{   int eId=result.getInt("eid");
			    String name=result.getString("ename");
			    String address=result.getString("eaddress");
			    String contact=result.getString("econtact");
			    String age=result.getString("eage");
			    
			    
			    System.out.println("Roll Number is "+eId );
			    System.out.println("Name is "+name);
			    System.out.println("Address is "+address);
			    System.out.println("Contact is "+contact);
			    System.out.println(" Age is "+age);
			    
				System.out.println("what do you want to udpate");
				System.out.println("1. Employe Name");
				System.out.println("2. Employe Address");
				System.out.println("3. Employe Contact");
				System.out.println("4. Employe Age");
				
				int choice=Integer.parseInt(scanner.nextLine());
				String sqlQuery="update emp set ";
				switch(choice)
				{
				case 1 :
					System.out.println("Enter new Name :");
					String newName=scanner.nextLine();
					sqlQuery = sqlQuery + "ename = ? where eid =" + empId;
				    PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
				    preparedStatement.setString(1, newName);
				    
				    int rows=preparedStatement.executeUpdate();
				    
				    if(rows > 0) {
				    	
				    	System.out.println("Records Updated Successfully..");
				    }
						break;
				case 2 :
					
					System.out.println("Enter new Address :");
					String newAddress=scanner.nextLine();
					sqlQuery = sqlQuery + "eaddress = ? where eid =" + empId;
				    PreparedStatement preparedStatement1=connection.prepareStatement(sqlQuery);
				    preparedStatement1.setString(1, newAddress);
				    
				    int rows1=preparedStatement1.executeUpdate();
				    
				    if(rows1 > 0) {
				    	
				    	System.out.println("Records Updated Successfully..");
				    }
					
					
						break;
				case 3 :
					System.out.println("Enter new Contact :");
					String newContact=scanner.nextLine();
					sqlQuery = sqlQuery + "econtact = ? where eid =" + empId;
				    PreparedStatement preparedStatement2=connection.prepareStatement(sqlQuery);
				    preparedStatement2.setString(1, newContact);
				    
				    int rows2=preparedStatement2.executeUpdate();
				    
				    if(rows2 > 0) {
				    	
				    	System.out.println("Records Updated Successfully..");
				    }
						break;
				case 4 :System.out.println("Name to be updated");
						break;
				case 5 :System.out.println("Name to be updated");
						break;
				}
			}
			else
			{
				System.out.println("Records Not Found...!");
			}
			
		}
		
		public void deleteRecord() throws SQLException
		{   System.out.println("Enter the Id which to be delete..");
		    
			int EId=Integer.parseInt(scanner.nextLine());
			String sql="delete from emp where eid = "+ EId;
			
			Statement statement=connection.createStatement();
			
			int rows=statement.executeUpdate(sql);
			
			if(rows > 0)
			{
				System.out.println("Record is Deleted Successfully...");
			}
			
		}
}















