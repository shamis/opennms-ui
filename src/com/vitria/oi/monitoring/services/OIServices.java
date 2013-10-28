package com.vitria.oi.monitoring.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vitria.oi.monitoring.db.ProjectsDAO;
import com.vitria.oi.monitoring.db.EmailsDAO;

@Path("/")
public class OIServices {
	
	@POST
	@Path("delete")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteProject(Param param){
		return ProjectsDAO.deleteProject(param.getId());
	}
	
	@POST
	@Path("new")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes("application/json")
	public String addProject(Param param){
		return ProjectsDAO.addProject(param);
	}
	
	@POST
	@Path("update")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes("application/json")
	public String updateProject(Param param){
		return ProjectsDAO.updateProject(param);
	}
	
	@GET
	@Path("projects")
	@Produces("application/json")
	public String getProjects(){
		return ProjectsDAO.getProjects();
	}
	
	@POST
	@Path("email/delete")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteEmail(Param param){
		return EmailsDAO.deleteEmail(param.getId());
	}
	
	@POST
	@Path("email/new")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes("application/json")
	public String addEmail(Param param){
		return EmailsDAO.addEmail(param);
	}
	
	@POST
	@Path("email/update")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes("application/json")
	public String updateEmail(Param param){
		return EmailsDAO.updateEmail(param);
	}
	
	@GET
	@Path("emails")
	@Produces("application/json")
	public String getEmail(){
		return EmailsDAO.getEmails();
	}
	
	@GET
	@Path("email/names")
	@Produces("application/json")
	public String getEmailGroupNames(){
		return EmailsDAO.getGroupsAndIds();
	}
}
