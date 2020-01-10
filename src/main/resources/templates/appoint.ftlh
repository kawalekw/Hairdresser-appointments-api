<#include "base.ftlh">

<#macro page_body>
    <h4>Dzień ${day.date}</h4>
    <form action="" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
        <button type="submit">Zarejestruj</button>
    </form>
    <script>
        var dateIs="${day.date}";
    </script>
</#macro>

<@display_page/>