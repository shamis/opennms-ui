package com.vitria.oi.monitoring.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONSerializer;

import com.vitria.oi.monitoring.events.ProjectInfo;
import com.vitria.oi.monitoring.services.Param;

public class ProjectsDAO {

	public static void main(String args[]){
		System.out.println(getProjects());
	}

	public static String getProjects(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null; 
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		try{
			con = DBAccess.getConnection();
			String sql = "select project_name, monitor, stopped_state, b.groupname, a.id from project_monitor a, emailgroups b where a.groupid = b.id";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()){
				ArrayList<String> tmp = new ArrayList<String>();
				tmp.add(rs.getString(1));
				tmp.add(rs.getInt(2) == 1 ? "Yes" : "No");
				tmp.add(rs.getString(3));
				tmp.add(rs.getString(4));
				tmp.add("&nbsp;&nbsp;<img class=\"edit\" data-id=\""+i+"\" data-tableId=\""+rs.getString(5)+"\" src=\"img/edit.png\" />&nbsp;&nbsp;&nbsp;<img class=\"delete\" data-id=\""+i+"\" data-tableId=\""+rs.getString(5)+"\" src=\"img/delete.png\" />");
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

	public static String addProject(Param param){
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String result = "false";
		try{
			con = DBAccess.getConnection();
			String sql = "  insert into project_monitor (project_name, monitor, stopped_state, groupid) select ?, ?, ?, id from emailgroups where groupname=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, param.getProjectName());
			pstmt.setInt(2, param.getMonitoring().equalsIgnoreCase("yes") ? 1 : 0);
			pstmt.setString(3, param.getStopState());
			pstmt.setString(4, param.getGroupName());
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

	public static String deleteProject(int id){
		Connection con = null;
		Statement stmt = null;
		int rs = 0;
		String result = "false";
		try{
			con = DBAccess.getConnection();
			String sql = "delete from project_monitor where id = "+id;
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

	public static String updateProject(Param param) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String result = "false";
		try{
			con = DBAccess.getConnection();
			String sql = " update project_monitor set project_name = ?, monitor = ?, stopped_state = ?, groupid = (Select id from emailgroups where groupname=?) where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, param.getProjectName());
			pstmt.setInt(2, param.getMonitoring().equalsIgnoreCase("yes") ? 1 : 0);
			pstmt.setString(3, param.getStopState());
			pstmt.setString(4, param.getGroupName());
			pstmt.setInt(5, param.getId());
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

	public static HashMap<String, ProjectInfo> getProjectsToBeMonitored(HashMap<String, ProjectInfo> projects){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null; 
		HashMap<String, ProjectInfo> requiredProjects = new HashMap<String, ProjectInfo>();
		try{
			con = DBAccess.getConnection();
			String sql = "select project_name, stopped_state from project_monitor where monitor <> 0";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				ProjectInfo project = projects.get(rs.getString(1));
				if(null != project){
					project.setStoppedState(rs.getString(2));
					requiredProjects.put(project.getProjectName(), project);
				}
			}
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception ee) {
			System.out.println("Exception in  getProjectsToBeMonitored \n: " + ee);
		} finally{
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return requiredProjects;
	}

	public static HashMap<String, ProjectInfo> getProjectsToBeNotified(HashMap<String, ProjectInfo> projects){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null; 
		HashMap<String, ProjectInfo> projectsToBeNotified = new HashMap<String, ProjectInfo>();
		try{
			con = DBAccess.getConnection();
			String sql = "select project_name, emails, a.id from project_monitor a, emailgroups b  where monitor <> 0 and notificationstate = 0 and b.id=groupid and extract(hour from (now() - lastdowntime)) >= 1";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				ProjectInfo project = projects.get(rs.getString(1));
				if(null != project){
					project.setEmails(rs.getString(2));
					project.setId(rs.getInt(3));
					projectsToBeNotified.put(project.getProjectName(), project);
				}
			}
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception ee) {
			System.out.println("Exception in  getProjectsToBeMonitored \n: " + ee);
		} finally{
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return projectsToBeNotified;

	}

	public static String updateNotificationState(int projectId){
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String result = "false";
		try{
			con = DBAccess.getConnection();
			String sql = " update project_monitor set notificationstate = 1, lastdowntime = now() where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, projectId);
			rs = pstmt.executeUpdate();
			result = rs == 1 ? "true" : "false"; 
			System.out.println("updation of notificationstate for "+ projectId +" was " +result);
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

}
