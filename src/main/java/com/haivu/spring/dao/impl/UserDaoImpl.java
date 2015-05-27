package com.haivu.spring.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haivu.spring.dao.UserDao;
import com.haivu.spring.model.User;

@Service(UserDao.NAME)
public class UserDaoImpl implements UserDao {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<User> getAllUser(int limit, int offset) {
		List<User> listUser = new ArrayList<User>();
		String sql = "SELECT * FROM User ORDER BY userId DESC LIMIT ? OFFSET ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPwd(rs.getString("pwd"));
				user.setFullName(rs.getString("fullName"));
				user.setDateOfBirth(rs.getDate("dateOfBirth"));
				user.setEmail(rs.getString("email"));
				user.setActive(rs.getBoolean("isActive"));
				listUser.add(user);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return listUser;
	}

	@Override
	public User getUserById(int userId) {
		User user = new User();
		String sql = "SELECT * FROM User WHERE userId = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPwd(rs.getString("pwd"));
				user.setFullName(rs.getString("fullName"));
				user.setDateOfBirth(rs.getDate("dateOfBirth"));
				user.setEmail(rs.getString("email"));
				user.setActive(rs.getBoolean("isActive"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return user;
	}

	@Override
	public boolean addNewUser(User user) {
		boolean isCheck = false;
		String sql = "INSERT INTO User (userName, pwd, fullName, dateOfBirth, email, isActive) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPwd());
			ps.setString(3, user.getFullName());
			ps.setDate(4, (Date) user.getDateOfBirth());
			ps.setString(5, user.getEmail());
			ps.setBoolean(6, user.isActive());
			int check = ps.executeUpdate();
			if (check != 0) {
				isCheck = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					ps.close();
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return isCheck;
	}

	@Override
	public boolean editUser(User user) {
		boolean isCheck = false;
		String sql = "UPDATE User SET fullName = ?, dateOfBirth = ?, isActive = ?"
				+ " WHERE userId = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFullName());
			ps.setDate(2, (Date) user.getDateOfBirth());
			ps.setBoolean(3, user.isActive());
			ps.setInt(4, user.getUserId());
			int check = ps.executeUpdate();
			if (check != 0) {
				isCheck = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					ps.close();
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return isCheck;
	}

	@Override
	public boolean delUser(int userId) {
		boolean isCheck = false;
		String sql = "DELETE FROM User WHERE userId = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			int check = ps.executeUpdate();
			if (check != 0) {
				isCheck = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					ps.close();
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return isCheck;
	}

	@Override
	public int getPageNumber() {
		int pageNumber = 0;
		String sql = "SELECT COUNT(*) FROM User";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				pageNumber = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return pageNumber;
	}
}
