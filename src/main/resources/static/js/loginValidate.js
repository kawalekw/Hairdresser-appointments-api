/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$( document ).ready(function(){
    
    $("#loginForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        highlight: function (element) {
            $(element).closest(".form-control").removeClass("is-valid").addClass("is-invalid");
             
        },
        unhighlight: function (element) {
            $(element).closest(".form-control").removeClass("is-invalid").addClass("is-valid");
             
        }
        
    });    
});
