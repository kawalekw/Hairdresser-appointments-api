<#include "base.ftlh">

<#macro page_body>
<<<<<<< HEAD
<div class="container-fluid text-center text-md-left">
    <h1>Godziny otwarcia <a href="">Edytuj</a></h1>
=======
    <h2>Lista definicji</h2>
>>>>>>> master
    <ul>
        <#list definitions as def>
            <li>${def.appliesFrom} <a href="/config/openhours/${def.appliesFrom}">Wczytaj</a> <#if (def.id>2)><a href="/config/openhours/del/${def.id}">Usuń</a></#if></li>
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
</div>
</#macro>

<@display_page/>