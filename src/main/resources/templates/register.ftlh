<#include "base.ftlh">

<#macro page_body>
    <div class="container-fluid text-center text-md-left">
        <br>
        <div class="col col-sm-8 col-md-6 col-lg-4 col-xl-4 mx-auto">
            <div class="card" style="max-width:480px">
                <div class="card-header"><h2>Rejestracja</h2></div>
                <div class="card-body">
                    <form action="" method="post">
                        <input class="form-control" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        Wybierz dzień:
                        <select class="form-control" id="daySelect" name="datestr">
                            <#list days as day>
                                <#if day.open><option value="${day.date}">${day.date}</option></#if>
                            </#list>
                        </select><br>
                        Wybierz usługę:
                        <select class="form-control" id="serviceSelect" name="service">
                            <option></option>
                            <#list services as service>
                                <option value="${service.id}">${service.name}</option>
                            </#list>
                        </select>
                        <br>
                        Wybierz godzinę:
                        <select class="form-control" id="hourSelect" name="hour">
                            <option></option>
                        </select>
                        <br>
                        Imie i nazwisko klienta:
                        <input class="form-control" type="text" name="name"/>
                        <br>
                        Nr telefonu:
                        <input class="form-control" type="text" name="phone"/>
                        <br>
                        <button button class="btn btn-primary btn-block" type="submit">Zarejestruj</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</#macro>

<@display_page/>
