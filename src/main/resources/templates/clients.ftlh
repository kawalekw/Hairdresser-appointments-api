<#include "base.ftlh">

<#macro page_body>
    Klienci
    <ul>
        <#list clients as client>
            <#if client.phone!="0">
                <li>${client.phone} <#if client.blocked><a  href="/config/clients/unlock/${client.id}">Odblokuj</a><#else><a href="/config/clients/block/${client.id}">Zablokuj</a></#if></li>
            </#if>
        </#list>
    </ul>
</#macro>

<@display_page/>