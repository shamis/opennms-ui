/* Table initialisation */
$(document).ready(function() {
	var data = JSON.parse($.ajax({ type: "GET",   
        url: "Vitria/OI/Services/projects",   
        async: false
      }).responseText);
	var emailGroups = JSON.parse($.ajax({ type: "GET",   
		url: "Vitria/OI/Services/email/names",   
		async: false
	}).responseText);
	var columns = [	{ "sTitle": "Project Name", "sClass": "center", "sWidth": "" },
	               	{ "sTitle": "Monitoring", "sClass": "center"  },
		   			{ "sTitle": "Stopped State", "sClass": "center"  },
		   			{ "sTitle": "Notification Email", "sClass": "center" },
		   			{ "sTitle": "Actions", "sClass": "center" }];
	var options = '<option value="Normal">Normal</option><option value="Warning">Warning</option><option value="Minor">Minor</option><option value="Major">Major</option><option value="Critical">Critical</option>';
	var yesOrNo = '<option value="Yes">Yes</option><option value="No">No</option>';
	var groupOptions = "";
	emailGroups.forEach(function(e){
		groupOptions += '<option vlaue="'+e[1]+'">'+e[0]+'</option>';
	});
	$('table#projectsTable').dataTable( {
		"sDom": "<'row'<'span6'l><'span8'f>r>t<'row'<'span6'i><'span8'p>>",
		"sPaginationType": "bootstrap",
		"oLanguage": {
			"sLengthMenu": "_MENU_ records per page"
		},
		//"aaData": data,
		"aaData": data,
		"aoColumns": columns
	} );
	var id = 0;
	$(document).on("click", ".edit", function (event) {
		id = $(this).data('id');
		$('#myModal').modal();
		$('#myModalLabel').text("Update Project Details");
		$('#myModal #projectName').val(data[id][0]);
		$('#myModal #monitoring').html(yesOrNo);
		$('#myModal #stopState').html(options);
		$('#myModal #monitoring').val(data[id][1]);
		$('#myModal #stopState').val(data[id][2]);
		$('#myModal #groupName').html(groupOptions);
		$('#myModal #groupName').val(data[id][3]);
		id = $(this).data('tableid');
	});
	$(document).on("click", ".delete", function (event) {
		id = $(this).data('tableid');
		$('#deleteModal').modal();
	});
	
	$(document).on("click", "#newProject", function (event) {
		$('#myModal').modal();
		$('#myModalLabel').text("Add New Project");
		$('#myModal #monitoring').html(yesOrNo);
		$('#myModal #stopState').html(options);
		$('#myModal #projectName').val("");
		$('#myModal #groupName').html(groupOptions);
		$('#myModal #applyChanges').text("Add Project");
		id = 0;
	});
	
	$("#yesDelete").on('click', function(){
		console.log("gonna delete : "+ id);
		$("#deleteModal").modal('hide');
		ajaxCall('Vitria/OI/Services/delete', '{"id" : '+id+'}', 'Deletion');
	});
	
	$("#applyChanges").on('click', function(){
		console.log("gonna add project ");
		$("#myModal").modal('hide');
		console.log($('#projectForm').serializeObject());
		if(id == 0)
			ajaxCall('Vitria/OI/Services/new', JSON.stringify($('#projectForm').serializeObject()), 'Insertion');
		else {
			var params = $('#projectForm').serializeObject();
			params.id = id;
			ajaxCall('Vitria/OI/Services/update', JSON.stringify(params), 'Updation');
		}
	});
	
	$('#deleteModal').on('hidden', function () {
		  id = 0;
	});
		
} );

