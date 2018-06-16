<#import "/spring.ftl" as spring>

<html>
    <h1>Persons</h1>
    <ul>
        <#list persons as person>
            <li>${person}</li>
        </#list>
    </ul>
    <br>
    <a href="/logout">Logout</a>
</html>