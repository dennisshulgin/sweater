<#macro login path button_name isRegistrationForm>
    <form action="${path}" method="post">
        <div class="mb-3 row">
            <label for="username" class="col-sm-2 col-form-label">Username</label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" id="username">
            </div>
        </div>
        <#if isRegistrationForm>
        <div class="mb-3 row">
            <label for="email" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" id="email">
            </div>
        </div>
        </#if>
        <div class="mb-3 row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" id="password">
            </div>
        </div>
        <#if isRegistrationForm>
        <div class="mb-3 row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" id="password">
            </div>
        </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <#if !isRegistrationForm><a href="/registration">Add new user</a></#if>
        <div><input class="btn btn-primary" type="submit" value="${button_name}"></div>
    </form>
</#macro>

<#macro logout>
        <form action="/logout" method="post">
            <input class="btn btn-primary" type="submit" value="Logout">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
</#macro>