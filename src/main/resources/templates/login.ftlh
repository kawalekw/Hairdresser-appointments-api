<#include "base.ftlh">

<#macro page_body>
    <form action="/login" method="post">
        Login: <input type="text" id="username" name="username"/><br>
        Hasło: <input type="password" id="password" name="password"/><br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    	<button type="submit">Log in</button>
    </form>
</#macro>

<@display_page/>