<#include "base.ftlh">

<#macro page_body>
    <h1>Dashboard</h1>
    <div>Tu będzię się wyświetlać robota na przyszły tydzień</div>
    <ul>
        <#list week as day>
            <li>
                ${day.date} ${day.date.dayOfWeek}<br>
                ${day.fromTo}                
            </li>
        </#list>
    </ul>
    <!--nawigacja-->
    <a href="/dashboard/${dmw.year?c}/${dmw.monthValue?c}/${dmw.dayOfMonth?c}"><<</a>
    <a href="/dashboard/${dmd.year?c}/${dmd.monthValue?c}/${dmd.dayOfMonth?c}"><</a>
    <a href="/dashboard/${dpd.year?c}/${dpd.monthValue?c}/${dpd.dayOfMonth?c}">></a>
    <a href="/dashboard/${dpw.year?c}/${dpw.monthValue?c}/${dpw.dayOfMonth?c}">>></a>
    <br>
    <div>
       Ustawiena:
       <ul>
           <li><a href="/config/services">Usługi</a></li>
           <li><a href="/config/openhours">Godziny otwarcia</a></li>
        </ul
    </div>
</#macro>

<@display_page/>