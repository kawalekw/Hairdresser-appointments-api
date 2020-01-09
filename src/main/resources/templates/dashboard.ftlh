<#include "base.ftlh">

<#macro page_body>
    <h1>Dashboard</h1>
    <ul>
        <#list week as day>
            <li>
                ${day.date} ${day.date.dayOfWeek}<br>
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
    <a href="/dashboard/${dmw.year?c}/${dmw.monthValue?c}/${dmw.dayOfMonth?c}"><<</a>
    <a href="/dashboard/${dmd.year?c}/${dmd.monthValue?c}/${dmd.dayOfMonth?c}"><</a>
    <a href="/dashboard/${dpd.year?c}/${dpd.monthValue?c}/${dpd.dayOfMonth?c}">></a>
    <a href="/dashboard/${dpw.year?c}/${dpw.monthValue?c}/${dpw.dayOfMonth?c}">>></a>
    
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
           <li><a href="/config/services">Usługi</a></li>
           <li><a href="/config/openhours">Godziny otwarcia</a></li>
           <li><a href="/config/clients">Klienci</a></li>
        </ul
    </div>
</#macro>

<@display_page/>