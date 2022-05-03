<#import "parts/common.ftlh" as c />
<@c.page>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Введите текст" />
            <input type="text" name="tag" placeholder="Тэг" />
            <input type="file" name="file" />
            <input type="hidden" name="_csrf" value="${_csrf.token}">
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
            <#if message.filePath??>
                <img src="/img/${message.filePath}"/>
            </#if>
        </div>
    <#else>
        No message
    </#list>
</@c.page>
