<#macro common_page_head>
  <meta charset="utf-8">
</#macro>

<#macro page_head>
    <@common_page_head/>
    <title>Fryzjer Online</title>
</#macro>

<#macro page_body>
  <h1>Basic Page</h1>
  <p>This is the body of the page!</p>
</#macro>

<#macro page_navbar>
    <h2>
        <a href="/">Home</a> - nagłówek
    </h2>
    <hr>
</#macro>

<#macro page_footer>
    <div style="position:absolute;bottom:0px;left:0px;width:100%">
        <hr>
        <h4>stopka</h4>
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
