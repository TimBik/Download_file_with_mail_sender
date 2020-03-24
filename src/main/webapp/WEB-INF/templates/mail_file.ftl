<#include "base.ftl">
<#macro content>
    <div>
        <h1> Hi, ${user.login}!</h1>
        This is your uploaded file:
        <h5>
            link: ${file}
            ...
        </h5>
        <br>
    </div>
</#macro>

<@main/>