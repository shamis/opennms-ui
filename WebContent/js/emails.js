$(document).ready(function() {
	var data = JSON.parse($.ajax({ type: "GET",   
        url: "Vitria/OI/Services/emails",   
        async: false
      }).responseText);
	var columns = [	{ "sTitle": "Sl No", "sClass": "center" },
	               	{ "sTitle": "Group Name", "sClass": "center", "sWidth": "" },
		   			{ "sTitle": "Notification Emails", "sClass": "center" },
		   			{ "sTitle": "Actions", "sClass": "center" }];
	$('table#emailsTable').dataTable( {
		"sDom": "<'row'<'span6'l><'span8'f>r>t<'row'<'span6'i><'span8'p>>",
		"sPaginationType": "bootstrap",
		"oLanguage": {
			"sLengthMenu": "_MENU_ records per page"
		},
		"aaData": data,
		"aoColumns": columns
	} );
	
	var id = 0;
	$(document).on("click", ".edit", function (event) {
		id = $(this).data('id');
		$('#myModal').modal();
		$('#myModalLabel').text("Update Group Details");
		$('#myModal #groupName').val(data[id][1]);
		$('#myModal #emails').val(data[id][2]);
		id = $(this).data('tableid');
	});
	$(document).on("click", ".delete", function (event) {
		id = $(this).data('tableid');
		$('#deleteModal').modal();
	});
	
	$(document).on("click", "#newEmail", function (event) {
		$('#myModal').modal();
		$('#myModalLabel').text("Add New Notification Group");
		$('#myModal #groupName').val("");
		$('#myModal #emails').val("");
		$('#myModal #applyChanges').text("Add Group");
		id = 0;
	});
	
	$("#yesDelete").on('click', function(){
		console.log("gonna delete : "+ id);
		$("#deleteModal").modal('hide');
		ajaxCall('Vitria/OI/Services/email/delete', '{"id" : '+id+'}', 'Deletion');
	});
	
	$("#applyChanges").on('click', function(){
		console.log("gonna add project ");
		$("#myModal").modal('hide');
		console.log($('#emailForm').serializeObject());
		if(id == 0)
			ajaxCall('Vitria/OI/Services/email/new', JSON.stringify($('#emailForm').serializeObject()), 'Insertion');
		else {
			var params = $('#emailForm').serializeObject();
			params.id = id;
			ajaxCall('Vitria/OI/Services/email/update', JSON.stringify(params), 'Updation');
		}
	});
	
	$('#deleteModal').on('hidden', function () {
		  id = 0;
	});
	
});