<#include "base.ftlh">

<#macro page_body>
    <h1>Godziny otwarcia</h1>
    <form action="/openhours/edit" method="post">
        <ul>
            <li>Niedziela:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(0)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(0)}"></li>
            </li>
            <li>Poniedziałek: 
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(1)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(1)}">
            </li>
            <li>Wtorek:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(2)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(2)}">
            </li>
            <li>Środa:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(3)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(3)}">
            </li>
            <li>Czwartek:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(4)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(4)}">
            </li>
            <li>Piątek:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(5)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(5)}">
            </li>
            <li>Sobota:
                <input type="text" class="hourInput" name="from[]" value="${openhours.fromStr(6)}">
                <input type="text" class="hourInput" name="to[]" value="${openhours.toStr(6)}">
            </li>
            
        </ul>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        Obowiązuje od: <input type="text" name="appliesfrom" value="${appliesfrom}">
        <br>
        <button type="Submit">Zapisz</button>
    </from>
    <br>
</#macro>

<@display_page/>