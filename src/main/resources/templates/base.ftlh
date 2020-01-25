<#macro common_page_head>
  <meta charset="utf-8">
</#macro>

<#macro page_head>
    <@common_page_head/>
    <title>Fryzjer Online</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/main.css" rel="stylesheet" type="text/css">
    <link rel="icon" 
      type="image/png" 
      href="/img/fav.png">
    <script src="/js/jquery-3.0.0.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/main.js"></script>
    <script src="/js/jquery.validate.js"></script>
    <script src="/js/localization/messages_pl.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</#macro>

<#macro page_body>
    <h1>Basic Page</h1>
    <p>This is the body of the page!</p>
</#macro>

<#macro page_navbar>
<nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light">
         <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent15"
    aria-controls="navbarSupportedContent15" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="navbar-brand d-block d-sm-none mx-auto"> 
        Fryzjer Online
        </div>
        <div class="navbar-brand d-none d-sm-block mx-auto"> 
        Fryzjer Online
        </div>
        <div class="collapse navbar-collapse" id="navbarSupportedContent15">
        <ul class="navbar-nav mt-sm-0">
        <li class="nav-item"> 
            <a class="nav-link" href="/">Home</a> 
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/register">Register</a>
        </li>
        </ul>
    
</nav>
</br>
</br>
</br>
<div class="container-fluid">
    <#if alert_success??>
        <div class="alert text-center alert-success alert-dismissible js-dismiss">
            <button type="button" class="close" data-dismiss="alert">&times;</button>${alert_success}
        </div>
    </#if>
    <#if alert_error??>
        <div class="alert text-center alert-danger alert-dismissible js-dismiss">
            <button type="button" class="close" data-dismiss="alert">&times;</button>${alert_error}
        </div>
    </#if>
</div>
</#macro>

<#macro page_footer>
<footer class="page-footer fixed-bottom bg-light  font-small pt-4">
    <div class="container-fluid text-center text-md-left">
        <h6 class="text-uppercase">
        <a href="https://www.facebook.com/Salon-Fryzjerski-Paulina-653797101462403" target="_blank"><img src="https://image.flaticon.com/icons/png/512/59/59439.png" style="width:40px;height:40px;" type="button"></img></a> 
        <a href="/dashboard">Dashboard</a>
        </h6>
        
</div>
</footer>
</#macro>

<#macro display_page>
    <!doctype html>
    <html>
        <head> 
            <@page_head/>
        </head>
        <body>
        <div class="container-fluid">
            <@page_navbar/>
            <@page_body/>
            <@page_footer/>
        </div>
        </body>
    </html>
</#macro>
