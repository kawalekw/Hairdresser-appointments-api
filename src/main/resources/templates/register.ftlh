<#include "base.ftlh">

<#macro page_body>
    <div class="container-fluid text-center text-md-left">
        <br>
        <div class="col col-sm-8 col-md-6 col-lg-4 col-xl-4 mx-auto">
            <div class="card" style="max-width:480px">
                <div class="card-header"><h2>Rejestracja</h2></div>
                <div class="card-body">
                    <form action="" method="post" autocomplete="off">
                        <input class="form-control" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        Wybierz dzień:
                        <input id="daySelect" name="datestr" autocomplete="off"/>
                        <br>
                        <script>
                            $(document).ready(function() {
                                let mindate=new Date();
                                mindate.setDate(mindate.getDate()-1);
                                let maxdate=new Date();
                                maxdate.setDate(maxdate.getDate()+90);
                                $('#daySelect').datepicker({
                                    uiLibrary: 'bootstrap4',
                                    format: "yyyy-mm-dd",
                                    modal: true,
                                    minDate: mindate,
                                    maxDate: maxdate,
                                    locale: "pl-pl",
                                    disableDates: [
                                        <#list days as day>
                                            <#if day.open == false>"${day.date}",</#if>
                                        </#list>
                                    ],
                                });
                            });
                        </script>
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
