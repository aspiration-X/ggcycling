<#include "header.ftl">
<div id="main">
    <div class="container">
        头像: <img alt="头像" src="${user.headUrl!}?x-oss-process=image/resize,w_100"/><br>
        用户名: ${user.name!} <br>

        <!--<ul>
        <li class="js-sendMsg"><a href="javascript:void(0);">发站内信</a></li>
        </ul>-->

    </div>
</div>
<#include "footer.ftl">
