function ajaxCall(url, param, operation){
	$.ajax({
		url: url,
		data: param,
		type: 'POST',
		contentType: 'application/json',
		erorr: function(){bootbox.alert(operation+' was unsuccessfull');},
		success: function(response){
			if(operation){
				response == "true" ? bootbox.alert(operation+' was successfull') : bootbox.alert(operation+' was unsuccessfull'); 
				setTimeout(function(){location.reload();}, 3000);		
				} else {
					data = JSON.parse(response);
					return {"aaData":data};
				}
			}
	});
}
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};