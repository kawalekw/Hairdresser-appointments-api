<#include "base.ftl">

<#macro page_body>
    <ul>
        <#list services as service>
            <li>${service.name} - ${service.duration}min</li>
        </#list>
    </ul>
</#macro>

<@display_page/>