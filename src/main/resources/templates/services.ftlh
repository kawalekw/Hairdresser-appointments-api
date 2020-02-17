<#include "base.ftlh">

<#macro page_body>
<div class="container-fluid text-center text-md-left">
    <div class="card">
    <div class="card-header"><h2>Dodaj nową usługę</h2></div>
    <div class="card-body">

        <form action="/services" method="POST" class="form-control">
            <label for="username">Nazwa usługi:</label>
            <input type="text" class="form-control" id="name" name="name"/><br>
            <label for="duration">Czas trwania:</label>
            <input type="decimal" class="form-control" id="duration" name="duration"/><br>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Dodaj">
        </form>
        <br>
        Lista usług:
        <ul>
            <#list services as service>
                <li>${service.name} - ${service.duration}min <a href="/services/delete/${service.id}">X</a></li>
            </#list>
        </ul>
    </div>
</div>
</#macro>

<@display_page/>