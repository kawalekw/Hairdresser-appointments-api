<#include "base.ftlh">

<#macro page_body>
    <div class="card">
    <div class="card-header">
        <h2>Klienci</h2>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table" border="1">
                <thead class="thead-dark">
                <tr>
                    <th>nr Tel.</th>
                    <th>Imię i Nazwisko</th>
                    <th>Akcje</th>
                </tr>
                </thead>
                <tbody>
                <#list clients as client>
                    <#if client.phone!="0">
                        <tr>
                            <td> ${client.phone}</td><td>${client.lastName}</td><td><#if client.blocked><a  href="/clients/unlock/${client.id}">Odblokuj</a><#else><a href="/clients/block/${client.id}">Zablokuj</a></#if></td>
                        </tr>
                    </#if>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</#macro>

<@display_page/>