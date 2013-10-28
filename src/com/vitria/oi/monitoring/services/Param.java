package com.vitria.oi.monitoring.services;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Param {

	private String projectName, monitoring, startState, stopState, emails, groupName;
	int id;

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getMonitoring() {
		return monitoring;
	}
	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}
	public String getStartState() {
		return startState;
	}
	public void setStartState(String startState) {
		this.startState = startState;
	}
	public String getStopState() {
		return stopState;
	}
	public void setStopState(String stoppState) {
		this.stopState = stoppState;
	}
	public String getEmails() {
		return emails;
	}
	public void setEmails(String emails) {
		this.emails = emails;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
