<#import "parts/common.ftlh" as c />
<@c.page>
    <form method="post" action="/user">
        <input type="hidden" name="id" value="${user.id}"/>
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <input type="text" name="username" value="${user.username}"/><br/>
        <#list roles as role>
            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")} />${role}</label><br/>
        </#list>
        <button type="submit">Save</button>
    </form>
</@c.page>