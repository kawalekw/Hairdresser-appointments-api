<#include "base.ftlh">

<#macro page_body>
<div class="container-fluid">
    <br>
    <div class="col-sm-10 col-lg-8 mx-auto">
        <div class="card">
            <div class="card-header">Salon xD</div>
            <div class="card-body">
                <div class="row">
                    <div class="col-12 col-lg-6">
                        <h4>Info o salonie</h4>
                        <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
                    </div>
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