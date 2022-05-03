<#macro login path button_name isRegistrationForm>
    <form action="${path}" method="post">
        <div class="mb-3 row">
            <label for="username" class="col-sm-2 col-form-label">Username</label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" id="username">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" id="password">
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <#if !isRegistrationForm><a href="/registration">Add new user</a></#if>
        <div><input class="btn btn-primary" type="submit" value="${button_name}"></div>
    </form>
</#macro>

<#macro logout>
    <div>
        <form action="/logout" method="post">
            <input class="btn btn-primary" type="submit" value="Logout">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</#macro>