<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projects</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/DT_bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/style.css">

<script type="text/javascript" charset="utf-8" language="javascript" src="js/jquery.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="js/DT_bootstrap.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="js/bootbox.min.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="js/utils.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="js/projects.js"></script>
</head>
<body>
<div class="main" style="margin-top: 10px">
<div id="projects">
		<h3>List of Projects</h3>
		<button id="newProject" class="btn btn-success">Add New Project</button>
		<a href="emailGroups.jsp"><button id="emailGroup" class="btn btn-primary">Add or Edit Email Groups</button></a>
		<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="projectsTable">
		</table>
	</div>
	</div>
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Update Project Details</h3>
  </div>
  <div class="modal-body" style="margin: 10px 50px">
    <form id="projectForm">
    <label>Project Name : </label><input type="text" id="projectName" name="projectName"></input><br />
    <label>Monitoring : </label><select name="monitoring" id="monitoring"></select><br />
    <label>Stop State : </label><select name="stopState" id="stopState"></select></input><br />
    <label>Notification Group : </label><select name="groupName" id="groupName"></select></input><br />
    </form>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    <button class="btn btn-primary" id="applyChanges">Apply changes</button>
  </div>
</div>

<div id="deleteModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-body">
   <p>Are you sure you want to delete this project ?</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">No</button>
    <button class="btn btn-primary" id="yesDelete">Yes</button>
  </div>
</div>
</body>
</html>