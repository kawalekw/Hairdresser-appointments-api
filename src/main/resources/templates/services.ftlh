<#include "base.ftlh">

<#macro page_body>
    <div>
        Dodaj nową usługę
        <form action="/config/services" method="POST">
            Nazwa usługi: <input type="text" name="name"><br>
            Czas trwania: <input type="decimal" name="duration"><br>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Dodaj">
        </form>
        <br>
        Lista usług:
        <ul>
            <#list services as service>
                <li>${service.name} - ${service.duration}min <a href="/config/services/delete/${service.id}">X</a></li>
            </#list>
        </ul>
    </div>
</#macro>

<@display_page/>