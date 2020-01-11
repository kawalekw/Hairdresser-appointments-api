$( document ).ready(function() {
    $("#serviceSelect").change(function () {
        var appDate;
        if (typeof dateIs !== 'undefined') {
            appDate = dateIs;
        }
        else {
            appDate = $("#daySelect").val();
        }
        //alert($("#serviceSelect").val());
        $.ajax({
            url         : "/api/schedule/id/"+appDate+"/"+$("#serviceSelect").val(),
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
    $("#daySelect").change(function () {
        $("#serviceSelect").val([]);
        $('#hourSelect').empty();
    });
});