<#include "base.ftlh">

<#macro page_body>
    <div class="card">
    <div class="card-header">
    <h2>Dzień ${day.date}</h2>
    </div>
    <div class="card-body">
    <ul class="list-group">
    Godziny otwarcia: ${day.fromTo}
    <#if day.appointments??>

            <#list day.appointments as app>
                <li class="list-group-item">${app.fromTo} - ${app.name} - ${app.service.name} <a href="/day/disappoint/${app.id}">Usuń</a></li>
            </#list>

    </#if>
    </ul>
    <br>
    <#if day.open><a href="/day/appoint/${day.date}">dodaj wizytę</a></#if>
    <br>
    <form action="/day/edit" method="post" class="form-control">
        Otwarte: <input type="checkbox" name="open" <#if day.open> checked</#if>><br>
        Od: <input type="text" class="hourInput" name="from" value="${day.openFromStr}"><br>
        Do: <input type="text" class="hourInput" name="to" value="${day.openToStr}"><br>
        <input type="hidden" name="datestr" value="${day.date}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Zapisz</button>
    </form>
    </div>
</#macro>

<@display_page/>