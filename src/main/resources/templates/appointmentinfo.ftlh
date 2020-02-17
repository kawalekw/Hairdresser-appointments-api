<#include "base.ftlh">

<#macro page_body>
    <div class=" col col-sm-6 col-md-4 col-lg-4 col-xl-4 mx-auto">
        <div class="card">
            <div class="card-header text-center"><h2>Gratulacje</h2></div>
            <div class="card-body">
                <div class="text-center">
                    <h4>Umówiono na:</h4> ${appointment.day.date} ${appointment.startsAtStr} ${appointment.service.name}
                </div>
            </div>
        </div>
    </div>
</#macro>

<@display_page/>

