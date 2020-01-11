<#include "base.ftlh">

<#macro page_body>
    <form action="" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        Wybierz dzień:
        <select id="daySelect" name="day">
            <#list days as day>
                <#if day.open><option value="${day.date}">${day.date}</option></#if>
            </#list>
        </select><br>
        Wybierz usługę:
        <select id="serviceSelect" name="service">
            <option></option>
            <#list services as service>
                <option value="${service.id}">${service.name}</option>
            </#list>
        </select>
        <br>
        Wybierz godzinę:
        <select id="hourSelect" name="hour">
            <option></option>
        </select>
        <br>
        Imie i nazwisko klienta: 
        <input type="text" name="name"/>
        <br>
        Nr telefonu: 
        <input type="text" name="phone"/>
        <br>
        <button type="submit">Zarejestruj</button>
    </form>
</#macro>

<@display_page/>
