<#macro common_page_head>
  <meta charset="utf-8">
</#macro>

<#macro page_head>
    <@common_page_head/>
    <title>Fryzjer Online</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery-3.0.0.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/main.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</#macro>

<#macro page_body>
    <h1>Basic Page</h1>
    <p>This is the body of the page!</p>
</#macro>

<#macro page_navbar>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    
        <div class-"collapse navbar-collapse">
        
        <ul class=navbar-nav mr-auto mt-2 mt-lg-0">
        <div class="navbar-brand">Fryzjer Online</div>
        <li class="nav-item"> 
            <a class="nav-link" href="/">Home</a> 
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/register">Register</a>
        </li>
        </ul>
    
</nav>
<div class="container-fluid">
    <#if alert_success??>
        <div class="alert alert-success alert-dismissible js-dismiss">
            <button type="button" class="close" data-dismiss="alert">&times;</button>${alert_success}
        </div>
    </#if>
    <#if alert_error??>
        <div class="alert alert-danger alert-dismissible js-dismiss">
            <button type="button" class="close" data-dismiss="alert">&times;</button>${alert_error}
        </div>
    </#if>
</div>
</#macro>

<#macro page_footer>
<footer class="page-footer fixed-bottom bg-light  font-small pt-4">
    <div class="container-fluid text-center  text-md-left">
        <h4 class="text-uppercase">
        stopka 
        <a href="/dashboard">Dashboard</a>
        </h4>
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
            <@page_navbar/>
            <@page_body/>
            <@page_footer/>
        </body>
    </html>
</#macro>
