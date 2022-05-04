<#import "parts/common.ftlh" as c />
<@c.page>
    <div class="form-row mb-3">
        <div class="form-group col-md-6">
            <form method="get">
                <div class="input-group mb-3">
                    <input type="text" name="filter" class="form-control" placeholder="Search by tag" aria-label="Search by tag" aria-describedby="button-addon2">
                    <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Search</button>
                </div>
            </form>
        </div>
    </div>
    <a class="btn btn-primary mb-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add message
    </a>
    <div class="collapse" id="collapseExample">
        <form method="post" enctype="multipart/form-data">
            <div class="row g-3">
                <div class="col-sm">
                    <input type="text" class="form-control" name="text" placeholder="Input text" aria-label="text" />
                </div>
                <div class="col-sm">
                    <input type="text" class="form-control" name="tag" placeholder="Tag" aria-label="tag"/>
                </div>
                <div class="col-sm">
                    <div class="mb-3">
                        <input class="form-control" name="file" type="file" id="formFile">
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="col-sm">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </div>
        </form>
    </div>
    <div>

    </div>
    <div class="row">
    <#list messages as message>
    <div class="col-sm-6">
        <div class="card my-3" style="width: 18rem;">
            <#if message.filePath??>
                <img src="/img/${message.filePath}" class="card-img-top"/>
            </#if>
            <div class="card-body">
                <h5 class="card-title">${message.authorName}</h5>
                <p class="card-text">${message.text}</p>
                <p class="card-text">${message.tag}</p>
            </div>
                <!--<a href="#" class="btn btn-primary">Go somewhere</a>-->
        </div>
    </div>
    <#else>
        No message
    </#list>
    </div>
</@c.page>
