$( document ).ready(function() {
    $("#serviceSelect").change(function () {    
        //alert($("#serviceSelect").val());
        $.ajax({
            url         : "/api/schedule/id/"+dateIs+"/"+$("#serviceSelect").val(),
            method      : "get",
            contentType : 'application/json',
            dataType    : 'json',
            success: function (data) { 
                $('#hourSelect').empty();
                data.forEach(function(entry) {
                    $('#hourSelect').append('<option value="'+entry+'">'+entry+'</option>');
                });
            },
            error: function (data){
                alert("error loading data");
            },
        });
    }); 
});