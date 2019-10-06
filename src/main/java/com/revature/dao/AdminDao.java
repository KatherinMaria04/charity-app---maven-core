package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.util.ConnectionUtil;

import com.revature.model.AdminAccess;
import com.revature.model.AdminLogin;
import com.revature.model.UserActivity;
import com.revature.model.UserDetails;

public class AdminDao implements IAdminDao {
public AdminLogin adminLogin(String name,String password2) throws SQLException {
	AdminLogin al= null;
       try {
    	   Connection con = ConnectionUtil.getConnection();
        String sql = "select name,password from adminlogin where name=?  and password=? ";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, name);
		pst.setString(2, password2);
		ResultSet rs = pst.executeQuery();
		
		  if (rs.next()) {
                al = new AdminLogin();
                al.setName(name);
                al.setPassword(password2);
                System.out.println("Login Success");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException("Unable to login",e);
        } 
        return al;

}
public  List<UserActivity> findAll() throws SQLException {
    Connection con = ConnectionUtil.getConnection();
    String sql = "select cus_name,request_type,donating_amt from useractivity";
    PreparedStatement pst = con.prepareStatement(sql);
    ResultSet rs = pst.executeQuery();
    List<UserActivity> list = new ArrayList<UserActivity>();
    while (rs.next()) {
        
        UserActivity user= toRow(rs);
        list.add(user);
    }
    return list;
}
private static UserActivity toRow(ResultSet rs) throws SQLException {
    String customername = rs.getString("cus_name");
    String requesttype= rs.getString("request_type");
    Long donatingamount= rs.getLong("donating_amt");
    UserActivity user = new UserActivity();
    user.setCustomername(customername);
    user.setRequesttype(requesttype);
    user.setDonatingamount(donatingamount);
    
    return user;
}
public  List<AdminAccess> requestDetails() throws SQLException {
    Connection con = ConnectionUtil.getConnection();
    String sql = "select request_type,amt_requested,target_amt from adminaccess";
    PreparedStatement pst = con.prepareStatement(sql);
    ResultSet rs = pst.executeQuery();
    List<AdminAccess> list = new ArrayList<AdminAccess>();
    while (rs.next()) {
        
        AdminAccess admin= toRow1(rs);
        list.add(admin);
    }
    return list;
}
private static AdminAccess toRow1(ResultSet rs) throws SQLException {
    String requesttype = rs.getString("request_type");
    Integer amountrequested= rs.getInt("amt_requested");
    Long targetamount= rs.getLong("target_amt");
    AdminAccess admin = new AdminAccess();
    admin.setRequesttype(requesttype);
    admin.setAmountrequested(amountrequested);
    admin.setTargetamount(targetamount);
    
    return admin;
}

public  void donationsUpdate( AdminAccess admin) throws SQLException {
	try {
		Connection con = ConnectionUtil.getConnection();
		String sql = "insert into adminaccess(request_type,amt_requested,target_amt) values (?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, admin.getRequesttype());
		pst.setLong(2, admin.getAmountrequested());
		pst.setLong(3,admin.getTargetamount());
		pst.executeUpdate();
	} catch (Exception e) {
	
		e.printStackTrace();
		throw new SQLException("Unable to insert donation");
		
	}
	
}

public  List<UserDetails> donarDetails() throws SQLException {
    Connection con = ConnectionUtil.getConnection();
    String sql = "select cus_name,phone_num from userlogin";
    PreparedStatement pst = con.prepareStatement(sql);
    ResultSet rs = pst.executeQuery();
    List<UserDetails> list = new ArrayList<UserDetails>();
    while (rs.next()) {
        
        UserDetails user= toRow2(rs);
        list.add(user);
    }
    return list;
}
private static UserDetails toRow2(ResultSet rs) throws SQLException {
    String customername = rs.getString("cus_name");
   // String password= rs.getString("password");
    Long phonenumber= rs.getLong("phone_num");
    UserDetails user = new UserDetails();
    user.setCustomername(customername);
   // user.setPassword(password);
    user.setPhoneno(phonenumber);
    
    return user;

}
}
