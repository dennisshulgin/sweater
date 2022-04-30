<#import "parts/common.ftlh" as c />
<#import "parts/login.ftlh" as l />
<@c.page>
    <@l.logout />
    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Введите текст" />
            <input type="text" name="tag" placeholder="Тэг" />
            <input type="hidden" name="_csrf" value="${_csrf.token}$">
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>
        <div>Список сообщений:</div>
        <form method="get">
            <input type="text" name="filter"/>
            <button type="submit">Найти</button>
        </form>
    </div>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <b>${message.authorName}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
        </div>
    </#list>
</@c.page>
