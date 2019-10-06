package com.revature.service;

import java.sql.Connection;
import java.util.List;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.dao.AdminDao;
import com.revature.dao.IAdminDao;
import com.revature.dao.IUserDao;
import com.revature.dao.UserDao;
import com.revature.util.ConnectionUtil;
import com.revature.model.AdminAccess;
import com.revature.model.UserActivity;


public class UserFunctions {
	 static Scanner sc=new Scanner(System.in);
	    static Connection con = ConnectionUtil.getConnection();
	public static void operations() throws SQLException
	    {
	    boolean quit=false;
		do {
			 
			System.out.println("================================");
	        System.out.println("         select your choice             ");
	        System.out.println("=================================");
	        System.out.println("1.Donation Details");
	        System.out.println("2.User Donation");
	        System.out.println( "3.quit the process:");
	        System.out.println("Enter your choice");
	        
	        Scanner sc = new Scanner(System.in);
	        int userchoice = sc.nextInt();
	        switch (userchoice) {
	        case 1:
	            
	            donationDetails();
	            break;
	       
	        case 2:
	            
	        	userDonation() ;
	            break;
	        
	        case 3:
             
	          quit = true;

                break;

          default:

                System.out.println("Wrong choice.");

                break;

          }

          System.out.println();

    } while (!quit);

    System.out.println("=====thank you========");
    
}
	public static void donationDetails()
	{
	    try {
	    	IUserDao dao= new UserDao();
	        List<AdminAccess> list = dao.findAll();
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

	    
	public static void userDonation() throws SQLException
	{
		System.out.println("Enter donar name:");
	    String customername=sc.next();
	    System.out.println("Enter your type of request:");
	    String requesttype=sc.next();
	    System.out.println("Enter the donating amount:");
	    long donatingamount=sc.nextLong();
	    long amountrequested = donatingamount;
	    System.out.println("******************THANK YOU FOR DONATING WITH CHARITY*****************");

	    UserActivity user=new UserActivity();
	    user.setCustomername(customername);
	    user.setRequesttype(requesttype);
	    user.setDonatingamount(donatingamount);
	    IUserDao dao= new UserDao();
	    dao.donarFund(user);
	    
	    AdminAccess admin=new AdminAccess();
	    admin.setRequesttype(requesttype);
	    admin.setAmountrequested(amountrequested);
	    IAdminDao dao1= new AdminDao();
	    dao1.donationsUpdate(admin);
	   
	    
	   
	}

}
