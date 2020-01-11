<#include "base.ftlh">

<#macro page_body>
    <h4>Klienci</h4>
    <ul>
        <#list clients as client>
            <#if client.phone!="0">
                <li>${client.phone} <#if client.lastAppointment??>${client.lastAppointment.name}</#if> <#if client.blocked><a  href="/clients/unlock/${client.id}">Odblokuj</a><#else><a href="/clients/block/${client.id}">Zablokuj</a></#if></li>
            </#if>
        </#list>
    </ul>
</#macro>

<@display_page/>