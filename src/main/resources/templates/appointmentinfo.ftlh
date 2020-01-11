<#include "base.ftlh">

<#macro page_body>
    <h1>Gratulacje</h1>
    <h4>Umówiono na:</h4>
    ${appointment.day.date}
    ${appointment.startsAtStr}
    ${appointment.service.name}
</#macro>

<@display_page/>

