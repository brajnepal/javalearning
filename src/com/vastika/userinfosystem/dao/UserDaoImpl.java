package com.vastika.userinfosystem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vastika.userinfosystem.model.User;
import com.vastika.userinfosystem.util.DbUtil;

public class UserDaoImpl  implements UserDao{
	private static final String INSEART_SQL= "insert into user_tbl(uaser_name, password, email, dob) values(?,?,?,?)";
	private static final String UPDATE_SQL= "update user_tbl set user_name=?, password =?, email =?,dob =? where id=?";
	private static final String DELETE_SQL= "delete from user_tbl where id=?";
	private static final String GET_BY_ID_SQL= "selete * from  user_tbl where id =? ";
	private static final String GET_ALL_SQL= "select * user_tbl";

	@Override
	public void saveUser(User user) {
		try (Connection con =DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(INSEART_SQL);){
			
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getEmail());
		ps.setDate(4, new Date(user.getDob().getTime()));
		ps.executeUpdate();
		
			
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateUser(User user) {
	
		try (Connection con =DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_SQL);){
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setDate(4, new Date(user.getDob().getTime()));
			ps.setInt(5, user.getId());
			ps.executeUpdate();
			
				
			} catch (SQLException | ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
		}

	@Override
	public void deleteUser(int id) {
	
try (Connection con =DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_SQL);){
			ps.setInt(1, id);
			ps.executeUpdate();
			
				
			} catch (SQLException | ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
		
	}

	@Override
	public User getUserInfoById(int id) {
		User user = new User();
		try (Connection con =DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(GET_BY_ID_SQL);){
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
		if (rs.next()) {
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("user_name"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setDob(rs.getDate("dob"));
			
		}
	} catch (SQLException | ClassNotFoundException e) {
		
		e.printStackTrace();
	}
		return user;
	}

	@Override
	public List<User> getAllUserInfo() {
		
		try (Connection con =DbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(GET_ALL_SQL);){
			List<User> userList = new ArrayList<>();
			
			ResultSet rs=ps.executeQuery();
			
		if (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("user_name"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setDob(rs.getDate("dob"));
			userList.add(user);
			
		}
	} catch (SQLException | ClassNotFoundException e) {
		
		e.printStackTrace();
	}
			
		return null;
	}

}
