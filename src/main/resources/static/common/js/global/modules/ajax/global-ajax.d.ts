/// <reference path="./class/AjaxSpec.ts"/>

interface GlobalAjax {
    get(data : string | AjaxSpec) : JQueryPromise<Response>;
    run(ajaxSpec : AjaxSpec) : JQueryPromise<Response>;
}