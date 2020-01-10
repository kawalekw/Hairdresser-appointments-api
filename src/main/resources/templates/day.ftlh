<#include "base.ftlh">

<#macro page_body>
    <h4>Dzień ${day.date}</h4>
    Godziny otwarcia: ${day.fromTo}
    <#if day.appointments??>
        <ul>
            <#list day.appointments as app>
                <li>${app.fromTo} - ${app.name} - ${app.service.name} <a href="/day/disappoint/${app.id}">Usuń</a></li>
            </#list>
        </ul>
    </#if>
    <#if day.open><a href="/day/appoint/${day.date}">dodaj wizytę</a></#if>
    <br>
    <form action="/day/edit" method="post">
        Otwarte: <input type="checkbox" name="open" <#if day.open> checked</#if>><br>
        Od: <input type="text" class="hourInput" name="from" value="${day.openFromStr}"><br>
        Do: <input type="text" class="hourInput" name="to" value="${day.openToStr}"><br>
        <input type="hidden" name="datestr" value="${day.date}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Zapisz</button>
    </form>
</#macro>

<@display_page/>