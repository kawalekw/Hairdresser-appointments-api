<#include "base.ftlh">

<#macro page_body>
<div class="container-fluid text-center text-md-left">
  <div class="col mx-auto">
    <div class="card">

   <div class="card-header">Dashboard</div>
   <div class="card-body">
    <!--nawigacja-->
    <a href="/dashboard/${dmw}"><<</a>
    <a href="/dashboard/${dmd}"><</a>
    <a href="/dashboard/${dpd}">></a>
    <a href="/dashboard/${dpw}">>></a>
    
    <table class="table" border="1">
    <thead class="thead-dark">
        <tr>
            <#list week as day>
            <th><a href="/day/${day.date}">edytuj</a></th>
            </#list>
        </tr>
        <tr>
            <#list week as day>
                <th>${day.date.dayOfWeek}</th>
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
    </thead>
    <tbody>
        <tr>
            <#list week as day>
            <th>
                <#if day.appointments??>

                        <#list day.appointments as app>
                           <li class="list-group-item"> ${app.fromTo} <br> ${app.name} <br> ${app.service.name}</li>
                        </#list>

                    </#if>
            </th>
            </#list>
        </tr>
    </tbody>
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
</div>
</div>
</div>
</#macro>

<@display_page/>