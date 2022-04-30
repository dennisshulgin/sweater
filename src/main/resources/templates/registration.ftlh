<#import "parts/common.ftlh" as c />
<#import "parts/login.ftlh" as l />
${message}
<@c.page>
    <@l.login "/registration" "Зарегистрироваться"/>
</@c.page>