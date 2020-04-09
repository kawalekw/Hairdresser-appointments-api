$( document ).ready(function() {
    setTimeout(function(){ $(".js-dismiss").hide(); }, 5000); //auto hiding alerts

    $("#serviceSelect").change(function () {
        var appDate;
        if (typeof dateIs !== 'undefined') {
            appDate = dateIs;
        } else {
            appDate = $("#daySelect").val();
        }
        if (appDate) {
            $.ajax({
                url: "/api/schedule/" + appDate + "/" + $("#serviceSelect").val(),
                method: "get",
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    $('#hourSelect').empty();
                    data.forEach(function (entry) {
                        $('#hourSelect').append('<option value="' + entry + '">' + entry + '</option>');
                    });
                    if(data.length==0){
                        $('#hourSelect').append('<option>Brak Terminów</option>');
                    }
                },
                error: function (data) {
                    $('#hourSelect').empty();
                    $('#hourSelect').append('<option>Brak Terminów</option>');
                },
            });
        }
    });
    $("#daySelect").change(function () {
        $("#serviceSelect").val([]);
        $('#hourSelect').empty();
    });
});