<#macro login path button_name>
    <form action="${path}" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="${button_name}"/></div>
    </form>
</#macro>

<#macro logout>
    <div>
        <form action="/logout" method="post">
            <input type="submit" value="Выйти"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</#macro>