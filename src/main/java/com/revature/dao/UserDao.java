package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.util.ConnectionUtil;
import com.revature.exception.DBException;
import com.revature.exception.ValidatorException;
import com.revature.model.AdminAccess;
import com.revature.model.UserActivity;
import com.revature.model.UserDetails;


public class UserDao implements IUserDao {

	public  void register( String customername, String password, long phonenumber) throws DBException {
		try {
			Connection con = ConnectionUtil.getConnection();
			String sql = "insert into userlogin(cus_name,password,phone_num) values (?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, customername);
			pst.setString(2, password);
			pst.setLong(3, phonenumber);
			pst.executeUpdate();
		} catch (Exception e) {

	      throw new DBException("invalid username or user already exist",e);
		}
		
	}
	public UserDetails login(String cusname,String password1) throws ValidatorException {
		UserDetails ud = null;
        
        try {
			Connection con = ConnectionUtil.getConnection();
			String sql = "select cus_name, password from userlogin where cus_name=?  and password=? ";
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, cusname);
			pst.setString(2, password1);
			ResultSet rs = pst.executeQuery();
			
			  if (rs.next()) {
	                ud = new UserDetails();
	                ud.setCustomername(cusname);
	                ud.setPassword(password1);
	                System.out.println("Login Success");
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            throw new ValidatorException("Unable to login");
	        } 
	        return ud;
	    }
     
	public  List<AdminAccess> findAll() throws SQLException {
	    Connection con = ConnectionUtil.getConnection();
	    String sql = "select request_type,amt_requested,target_amt from adminaccess";
	    PreparedStatement pst = con.prepareStatement(sql);
	    ResultSet rs = pst.executeQuery();
	    List<AdminAccess> list = new ArrayList<AdminAccess>();
	    while (rs.next()) {
	        
	        AdminAccess admin= toRow(rs);
	        list.add(admin);
	    }
	    return list;
	}
	private static AdminAccess toRow(ResultSet rs) throws SQLException {
	    String requesttype = rs.getString("request_type");
	    Integer amountrequested= rs.getInt("amt_requested");
	    long targetamount= rs.getLong("target_amt");
	    AdminAccess admin = new AdminAccess();
	    admin.setRequesttype(requesttype);
	    admin.setAmountrequested(amountrequested);
		admin.setTargetamount(targetamount);
	    
	    return admin;
	}
	public  void donarFund( UserActivity user) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "insert into useractivity(request_type,cus_name,donating_amt) values (?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, user.getRequesttype());
		pst.setString(2, user.getCustomername());
		pst.setLong(3,user.getDonatingamount());
		pst.executeUpdate();
		
    }
	public  void updateDonation( UserActivity user) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "update  adminaccess set amt_requested=amt_requested+? where request_type=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setLong(1,user.getDonatingamount());
		pst.setString(2, user.getRequesttype());
		pst.executeUpdate();
		
    }

}