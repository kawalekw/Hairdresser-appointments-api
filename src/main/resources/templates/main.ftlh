<#include "base.ftlh">

<#macro page_body>
<div class="container-fluid text-center text-md-left">
    <h4>Info o salonie</h4>
    <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
    <br>
    <h4>Godziny otwarcia</h4>
    <ul>
        <li>Poniedziałek: ${open.fromTo(1)}</li>
        <li>Wtorek: ${open.fromTo(2)}</li>
        <li>Środa: ${open.fromTo(3)}</li>
        <li>Czwartek: ${open.fromTo(4)}</li>
        <li>Piątek: ${open.fromTo(5)}</li>
        <li>Sobota: ${open.fromTo(6)}</li>
        <li>Niedziela: ${open.fromTo(0)}</li>
    </ul>
</#macro>
</div>
<@display_page/>