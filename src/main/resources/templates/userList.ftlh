<#import "parts/common.ftlh" as c />
<@c.page>
    <table>
        <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep> ,</#list></td>
            <td><a href="/user/${user.id}">Редактировать</a></td>
        </tr>
        </#list>
    </table>
</@c.page>