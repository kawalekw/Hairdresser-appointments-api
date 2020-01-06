<#include "base.ftlh">

<#macro page_body>
    <h1>Godziny otwarcia <a href="">Edytuj</a></h1>
    <ul>
        <li>Poniedziałek: ${openhours.fromTo(1)}</li>
        <li>Wtorek: ${openhours.fromTo(2)}</li>
        <li>Środa: ${openhours.fromTo(3)}</li>
        <li>Czwartek: ${openhours.fromTo(4)}</li>
        <li>Piątek: ${openhours.fromTo(5)}</li>
        <li>Sobota: ${openhours.fromTo(6)}</li>
        <li>Niedziela: ${openhours.fromTo(0)}</li>
    </ul>
    Obowiązuje od: ${openhours.appliesFrom}
</#macro>

<@display_page/>