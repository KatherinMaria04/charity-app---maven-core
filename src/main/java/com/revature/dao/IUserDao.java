package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.exception.DBException;
import com.revature.exception.ValidatorException;
import com.revature.model.AdminAccess;
import com.revature.model.UserActivity;
import com.revature.model.UserDetails;

public interface IUserDao {
	void register(String customername, String password, long phonenumber) throws DBException;

	UserDetails login(String cusname, String password1) throws ValidatorException;

	List<AdminAccess> findAll() throws SQLException;

	void donarFund(UserActivity user) throws SQLException;

	void updateDonation(AdminAccess admin ) throws SQLException;



}