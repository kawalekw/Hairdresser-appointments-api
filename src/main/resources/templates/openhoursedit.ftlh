<#include "base.ftlh">

<#macro page_body>
    <div class="card">
    <div class="card-header"><h1>Godziny otwarcia</h1></card>
    <form action="/openhours/edit" method="post" class="form-control">
        <ul class="list-group">
            <li class="list-group-item">Niedziela:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(0)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(0)}"></li>
            </li>
            <li class="list-group-item">Poniedziałek:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(1)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(1)}">
            </li>
            <li class="list-group-item">Wtorek:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(2)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(2)}">
            </li>
            <li class="list-group-item">Środa:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(3)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(3)}">
            </li>
            <li class="list-group-item">Czwartek:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(4)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(4)}">
            </li>
            <li class="list-group-item">Piątek:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(5)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(5)}">
            </li>
            <li class="list-group-item">Sobota:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(6)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(6)}">
            </li>
            
        </ul>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <br>
        Obowiązuje od: <input type="text" name="appliesfrom" value="${appliesfrom}">
        <br>
        <br>
        <button type="Submit">Zapisz</button>
    </from>
    <br>
    </div>
</#macro>

<@display_page/>