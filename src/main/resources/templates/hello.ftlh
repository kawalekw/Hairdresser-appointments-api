<#include "base.ftlh">

<#macro page_body>
    <h1>Siema ${user.name}</h1>
    <h2>Twój numer tel. - ${user.phone}</h2>
    
    <h4>Info o salonie</h4>
    <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
    <br>
    <div>Tu będzie formluarz do rejestracji</div>
</#macro>

<@display_page/>