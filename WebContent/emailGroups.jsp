<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Notification Email Groups</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/DT_bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/style.css">

<script type="text/javascript" charset="utf-8" language="javascript" src="js/jquery.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="js/DT_bootstrap.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="js/bootbox.min.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="js/utils.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="js/emails.js"></script>
</head>
<body>
<div class="main" style="margin-top: 10px">
<div id="emails">
		<h3>Notification Email Groups</h3>
		<button id="newEmail" class="btn btn-success">Add New Notification Group</button>
		<a href="projects.jsp"><button id="emailGroup" class="btn btn-primary">Projects List</button></a>
		<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="emailsTable">
		</table>
	</div>
	</div>
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Update Group Details</h3>
  </div>
  <div class="modal-body" style="margin: 10px 50px">
    <form id="emailForm">
    <label>Group Name : </label><input type="text" id="groupName" name="groupName"></input><br />
    <label>Notification Emails : </label><input type="text" id="emails" name="emails"></input><br />
    </form>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    <button class="btn btn-primary" id="applyChanges">Apply changes</button>
  </div>
</div>

<div id="deleteModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-body">
   <p>Are you sure you want to delete this notification group ?</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">No</button>
    <button class="btn btn-primary" id="yesDelete">Yes</button>
  </div>
</div>
</body>
</html>