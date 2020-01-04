<#include "base.ftlh">

<#macro page_body>
    <h1>Siema ${user.name}</h1>
    <h2>Twój numer tel. - ${user.phone}</h2>
</#macro>

<@display_page/>