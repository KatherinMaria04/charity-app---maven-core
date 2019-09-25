package com.revature.service;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.dao.AdminDao;
import com.revature.dao.IAdminDao;
import com.revature.util.ConnectionUtil;
import com.revature.model.AdminAccess;
import com.revature.model.UserActivity;
import com.revature.model.UserDetails;


public class AdminFunctions {
	 
	    static Scanner sc=new Scanner(System.in);
	    static Connection con = ConnectionUtil.getConnection();
	  static  boolean quit=false;
	public static void operations() throws SQLException
	    {
	    
		
	    do {

        System.out.println("================================");
        System.out.println("         select your choice          ");
        System.out.println("=================================");
        System.out.println("1.Donation Details");
        System.out.println("2.Donor Activity");
        System.out.println("3.Donor Details");
        System.out.println("4.insert new Donation details");
        System.out.println("5.quit the process:");
        System.out.println("Enter your choice:");

        
        Scanner sc = new Scanner(System.in);
        int userchoice = sc.nextInt();
        switch (userchoice) {
        
        case 1:
            
            donationDetails();
            break;
       
        case 2:
            
            donarActivity();
            break;
        case 3:
            
   	     donarDetails();
               break;
     case 4:
            
	     updateRequest();
            break;
     
     case 5:
         
         quit = true;

           break;

     default:

           System.out.println("Wrong choice.");

           break;

     }

     System.out.println();

    } while (!quit);

    System.out.println("*********thank you***********");

    }
public static void donationDetails()
{
    try {
    	IAdminDao dao= new AdminDao();
       List<AdminAccess> list = dao.requestDetails();
       display(list);
     
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public static void display(List<AdminAccess> list) {
	StringBuilder content=new StringBuilder();
    content.append(" request_type\tamount_requested\ttarget_amount\t\n");
	for (AdminAccess admin : list) {
		 content.append(admin.getRequesttype()).append("\t\t\t");
            content.append(admin.getAmountrequested()).append("\t\t\t");
            content.append(admin.getTargetamount()).append("\t");
            content.append("\n");
        }
        System.out.println(content);
	}
public static void donarActivity() throws SQLException
{
    
	 try {
		 IAdminDao dao= new AdminDao();
	       List<UserActivity> list = dao.findAll();
	       
	       
	        displayActivity1(list);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
}
public static void displayActivity1(List<UserActivity> list) {
	StringBuilder content=new StringBuilder();
    content.append(" customer_name\trequest_type\tdonating_amount\t\n");
	for (UserActivity user : list) {
		 content.append(user.getCustomername()).append("\t\t\t");
            content.append(user.getRequesttype()).append("\t\t\t");
            content.append(user.getDonatingamount()).append("\t");
            content.append("\n");
        }
        System.out.println(content);
	}


	 public static void donarDetails() throws SQLException
	 {
	     
	 	 try {
	 		 IAdminDao dao= new AdminDao();
	 	       List<UserDetails> list = dao.donarDetails();
	 	       
	 	       
	 	        donarLogin1(list);
	 	    } catch (SQLException e) {
	 	        e.printStackTrace();
	 	    }
	     }
	 public static void donarLogin1(List<UserDetails> list) {
			StringBuilder content=new StringBuilder();
		    content.append(" customer_name\tpassword\tphone_number\t\n");
			for (UserDetails user : list) {
				 content.append(user.getCustomername()).append("\t\t\t");
		            content.append(user.getPassword()).append("\t\t\t");
		            content.append(user.getPhoneno()).append("\t");
		            content.append("\n");
		        }
		        System.out.println(content);
			}
	
public static void updateRequest() throws SQLException
{    
	
	 System.out.println("Enter  request type:");
	    String requesttype=sc.nextLine();
	    System.out.println("Amount requested");
	    Long amountrequested=sc.nextLong();
	    System.out.println("Traget Amount:");
	    Long targetamount=sc.nextLong();
	    System.out.println("***************Charity request has been updated successfully**************");
	    AdminAccess admin=new AdminAccess();
	    admin.setRequesttype(requesttype);
	    admin.setAmountrequested(amountrequested);
	    admin.setTargetamount(targetamount);
	    IAdminDao dao= new AdminDao();
	   
			try {
				dao.donationsUpdate(admin);
			} catch (Exception e) {

				e.printStackTrace();
			}
		
	    
	   
}

}

