<#import "parts/common.ftlh" as c />
<@c.page>
<form action="/user/profile" method="post">
    <div class="mb-3 row">
        <label for="username" class="col-sm-2 col-form-label">Username</label>
        <div class="col-sm-6">
            <input type="text" name="username" disabled class="form-control" id="username" value="${username}">
        </div>
    </div>
    <div class="mb-3 row">
        <label for="email" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-6">
            <input type="email" name="email" class="form-control" id="email" value="${email!''}">
        </div>
    </div>
    <div class="mb-3 row">
        <label for="password" class="col-sm-2 col-form-label">Password</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" id="password">
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div><input class="btn btn-primary" type="submit" value="Save"></div>
</form>
</@c.page>