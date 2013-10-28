package com.vitria.oi.monitoring.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.sf.json.JSONSerializer;

import com.vitria.oi.monitoring.services.Param;

public class EmailsDAO {
	
	public static void main(String args[]){
		System.out.println(getEmails());
	}
	
	public static String getEmails(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null; 
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		try{
			con = DBAccess.getConnection();
			String sql = "select groupname, emails, id from emailgroups";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()){
				ArrayList<String> tmp = new ArrayList<String>();
				tmp.add(i+1 + "");
				tmp.add(rs.getString(1));
				tmp.add(rs.getString(2));
				tmp.add("&nbsp;&nbsp;<img class=\"edit\" data-id=\""+i+"\" data-tableId=\""+rs.getString(3)+"\" src=\"img/edit.png\" />&nbsp;&nbsp;&nbsp;<img class=\"delete\" data-id=\""+i+"\" data-tableId=\""+rs.getString(3)+"\" src=\"img/delete.png\" />");
				++i;
				data.add(tmp);
			}
			System.out.println(data.size());
		} catch (SQLException e){
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return JSONSerializer.toJSON(data).toString();
	}

	public static String addEmail(Param param){
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String result = "false";
		try{
			con = DBAccess.getConnection();
			String sql = " insert into emailgroups values(DEFAULT, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, param.getGroupName());
			pstmt.setString(2, param.getEmails());
			rs = pstmt.executeUpdate();
			result = rs == 1 ? "true" : "false"; 
			System.out.println("Insertion was " +result);
		} catch (SQLException e){
			e.printStackTrace();
		} finally{
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String deleteEmail(int id){
		Connection con = null;
		Statement stmt = null;
		int rs = 0;
		String result = "false";
		try{
			con = DBAccess.getConnection();
			String sql = "delete from emailgroups where id = "+id;
			stmt = con.createStatement();
			rs = stmt.executeUpdate(sql);
			result = rs == 1 ? "true" : "false"; 
			System.out.println("Deletion was " +result);
		} catch (SQLException e){
			e.printStackTrace();
		} finally{
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String updateEmail(Param param) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String result = "false";
		try{
			con = DBAccess.getConnection();
			String sql = " update emailgroups set groupname = ?, emails = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, param.getGroupName());
			pstmt.setString(2, param.getEmails());
			pstmt.setInt(3, param.getId());
			rs = pstmt.executeUpdate();
			result = rs == 1 ? "true" : "false"; 
			System.out.println("Insertion was " +result);
		} catch (SQLException e){
			e.printStackTrace();
		} finally{
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String getGroupsAndIds(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null; 
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		try{
			con = DBAccess.getConnection();
			String sql = "select groupname, id from emailgroups";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				ArrayList<String> tmp = new ArrayList<String>();
				tmp.add(rs.getString(1));
				tmp.add(rs.getString(2));
				data.add(tmp);
			}
			System.out.println(data.size());
		} catch (SQLException e){
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return JSONSerializer.toJSON(data).toString();
	}
	
}
