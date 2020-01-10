<#include "base.ftlh">

<#macro page_body>
<div class="container-fluid text-center text-md-left">
    <h1>Dashboard</h1>
    <ul>
        <#list week as day>
            <li>
                ${day.date} ${day.date.dayOfWeek} <a href="/day/${day.date}">edytuj</a><br>
                ${day.fromTo}   
                <#if day.appointments??>
                <ul>
                    <#list day.appointments as app>
                        <li>${app.fromTo} - ${app.name} - ${app.service.name}</li>
                    </#list>
                </ul>
                </#if>
            </li>
        </#list>
    </ul>
    
    <!--nawigacja-->
    <a href="/dashboard/${dmw}"><<</a>
    <a href="/dashboard/${dmd}"><</a>
    <a href="/dashboard/${dpd}">></a>
    <a href="/dashboard/${dpw}">>></a>
    
    <table border="1">
        <tr>
            <#list week as day>
                <td>${day.date.dayOfWeek}</td>
            </#list>
        </tr>
        <tr>
            <#list week as day>
                <th>${day.date}</th>
            </#list>
        </tr>
        <tr>
            <#list week as day>
                <th>${day.fromTo}</th>
            </#list>
        </tr>
        <tr>
            <#list week as day>
            <td>
                <#if day.appointments??>
                    Wizyty:
                    <ul>
                        <#list day.appointments as app>
                            <li>${app.fromTo} <br> ${app.name} <br> ${app.service.name}</li>
                        </#list>
                    </ul>
                    </#if>
                </td>
            </#list>
        </tr>
    </table>

    <br>
    <div>
       Ustawiena:
       <ul>
           <li><a href="/services">Usługi</a></li>
           <li><a href="/openhours">Godziny otwarcia</a></li>
           <li><a href="/clients">Klienci</a></li>
        </ul
    </div>
</div>
</#macro>

<@display_page/>