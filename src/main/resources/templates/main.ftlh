<#include "base.ftlh">

<#macro page_body>
    <div class="container-fluid">
        <br>
        <div class="col-sm-10 col-lg-8 mx-auto">
            <div class="card">
                <div class="card-header"><h2>Salon</h2></div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12 col-lg-6">
                            <h4>Paulina Toakrczyk</h4>
                            <ul>
                                <li>Tel. 531852058</li>
                                <li>Szubin</li>
                                <li>ul. 3 Maja 16</li>
                            </ul>
                        </div>
                        <br>
                        <div class="col-12 col-lg-6">
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>
</div>
<@display_page/>