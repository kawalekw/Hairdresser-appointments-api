<#include "base.ftlh">

<#macro page_body>
<div class="container-fluid text-center text-md-left">
    <div class="card">
    <div class="card-header">
    <h2>Lista definicji</h2>
    </div>
    <div class="card-body">
    <ul class="list-group">
        <#list definitions as def>
            <li class="list-group-item"> ${def.appliesFrom} <a href="/openhours/${def.appliesFrom}">Wczytaj</a> <#if (def.id>2)><a href="/openhours/del/${def.id}">Usuń</a></#if></li>
        </#list>
        <li class="list-group-item"><a href="/openhours/edit">Nowa definicja</a></li>
    </ul>
    </div>
    </div>
    <br>
    <div class="card">
    <div class="card-header">
    <h2>Godziny otwarcia</h2>
    </div>
    <div class="card-body">
    <ul class="list-group">
        <li class="list-group-item">Niedziela: ${openhours.fromTo(0)}</li>
        <li class="list-group-item">Poniedziałek: ${openhours.fromTo(1)}</li>
        <li class="list-group-item">Wtorek: ${openhours.fromTo(2)}</li>
        <li class="list-group-item">Środa: ${openhours.fromTo(3)}</li>
       <li class="list-group-item">Czwartek: ${openhours.fromTo(4)}</li>
        <li class="list-group-item">Piątek: ${openhours.fromTo(5)}</li>
        <li class="list-group-item">Sobota: ${openhours.fromTo(6)}</li>
    </ul>
    Obowiązuje od: ${openhours.appliesFrom}
    </div>
        </div>
</div>
</#macro>

<@display_page/>