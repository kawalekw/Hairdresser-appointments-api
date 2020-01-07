<#macro common_page_head>
  <meta charset="utf-8">
</#macro>

<#macro page_head>
    <@common_page_head/>
    <title>Fryzjer Online</title>
    <link href="/css/main.css" rel="stylesheet" type="text/css">
    <script src="/js/main.js"></script>
</#macro>

<#macro page_body>
    <h1>Basic Page</h1>
    <p>This is the body of the page!</p>
</#macro>

<#macro page_navbar>
    <h2>
        Fryzjer Online - <a href="/">Home</a> 
    </h2>
    <hr>
</#macro>

<#macro page_footer>
    <div class="footer">
        <hr>
        <h4>stopka <a href="/dashboard">Dashboard XDXD</a></h4>
    </div>
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
