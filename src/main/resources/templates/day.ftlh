<#include "base.ftlh">

<#macro page_body>
    <h4>Dzień ${day.date}</h4>
    Godziny otwarcia: ${day.fromTo}
</#macro>

<@display_page/>