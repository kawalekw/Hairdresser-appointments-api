<#include "base.ftlh">

<#macro page_body>
<div class="container-fluid text-center text-md-left">
    <form action="/login" method="post">
       <div class="form-row">
       <div class="col-xs-2">
        <label for="username">Login</label>
        <input type="text" class="form-control" id="username" name="username"/><br>
        <label for="password">Hasło</label>
        <input type="password" class="form-control" id="password" name="password"/><br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    	<button type="submit">Log in</button>
        </div>
       </div>
    </form>
</div>
</#macro>

<@display_page/>