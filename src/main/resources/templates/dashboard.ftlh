<#include "base.ftlh">

<#macro page_body>
<div class="container-fluid text-center text-md-left">
  <div class="col mx-auto">
    <div class="card">

   <div class="card-header">Dashboard</div>
   <div class="card-body">
    <!--nawigacja-->
      <div class="row">
      <div class="col text-left">
      <a class="btn btn-primary" href="/dashboard/${dmw}">tydzień w tył</a>
      <a class="btn btn-primary" href="/dashboard/${dmd}">dzień w tył</a>
      </div>
      <div class="col text-right">
      <a class="btn btn-primary" href="/dashboard/${dpd}">dzień w przód</a>
      <a class="btn btn-primary" href="/dashboard/${dpw}">tydzień w przód</a>
      </div>
      </div>

    <div class="table-responsive">
    <table class="table" border="1">
    <thead class="thead-dark">
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
        <tr class="bg-dark">
                    <#list week as day>
                    <td class="text-center"><a class="text-white navbar-brand" href="/day/${day.date}">EDYTUJ</a></td>
                    </#list>
        </tr>
    </tbody>
    </table>
    </div>
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