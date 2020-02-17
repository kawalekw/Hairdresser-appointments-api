<#include "base.ftlh">

<#macro page_body>
    <div class="card">
    <div class="card-header">
    <h4>Klienci</h4>
    </div>
    <div class="card-body">
    <ul class="list-group">
        <#list clients as client>
            <#if client.phone!="0">
                <li class="list-group-item"> ${client.phone} <#if client.lastAppointment??>${client.lastAppointment.name}</#if> <#if client.blocked><a  href="/clients/unlock/${client.id}">Odblokuj</a><#else><a href="/clients/block/${client.id}">Zablokuj</a></#if></li>
            </#if>
        </#list>
    </ul>
    </div>
</#macro>

<@display_page/>