<#include "base.ftlh">

<#macro page_body>
    <h2>Lista definicji</h2>
    <ul>
        <#list definitions as def>
            <li>${def.appliesFrom} <a href="/config/openhours/${def.appliesFrom}">Wczytaj</a> <a href="/config/openhours/del/${def.id}">Usuń</a></li>
        </#list>
        <li><a href="/config/openhours/edit">Nowa definicja</a></li>
    </ul>
    <h2>Godziny otwarcia</h2>
    <ul>
        <li>Niedziela: ${openhours.fromTo(0)}</li>
        <li>Poniedziałek: ${openhours.fromTo(1)}</li>
        <li>Wtorek: ${openhours.fromTo(2)}</li>
        <li>Środa: ${openhours.fromTo(3)}</li>
        <li>Czwartek: ${openhours.fromTo(4)}</li>
        <li>Piątek: ${openhours.fromTo(5)}</li>
        <li>Sobota: ${openhours.fromTo(6)}</li>
    </ul>
    Obowiązuje od: ${openhours.appliesFrom}
</#macro>

<@display_page/>