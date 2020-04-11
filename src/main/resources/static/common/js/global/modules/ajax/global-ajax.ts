/// <reference path="../../global.d.ts" />
/// <reference path="./class/AjaxSpec.ts"/>

module GlobalAjax {
    const responseData : {[key : string] : any} = [];

    /**
     * Ajax데이터만 가져와야 할 경우 string을 파라미터로, 혹은 AjaxSpec을 파라미터로 넣어주면 됨.
     * 만약, 기존에 Ajax를 통하여서 데이터를 가져온 적이 있으면 그 데이터를 가져온다. (모두)
     * 만약, 기존에 Ajax를 통하여 데이터를 가져온 적이 없으면, 새로 init하여 가져온다. (AjaxSpec 을 파라미터로 넣어준 경우에만.)
     * @param data
     */
    export const get = (data : string | AjaxSpec) : JQueryPromise<Response> => {
        const deferred = $.Deferred<Response>();
        const instanceName = typeof data === "string" ? data : data.getName();

        if(!!responseData[instanceName]) {
            deferred.resolve(responseData[instanceName]);
            return deferred.promise();
        }

        if (typeof data !== "string") {
            run(data).done((res : any) => {
                deferred.resolve(res)
            }).fail((err) => {
                console.error(err);
                deferred.reject(err);
            });
        }
        return deferred.promise();
    };

    /**
     * Ajax가 무조건 실행 되어야하는 경우. 실행하여 결과를 가져온다.
     * @param ajaxSpec
     */
    export const run = (ajaxSpec : AjaxSpec) : JQueryPromise<Response> => {
        const deferred = $.Deferred<Response>();

        $.ajax(ajaxSpec.getSetting()).done((res : JQueryPromiseCallback<Response>) => {
            responseData[ajaxSpec.getName()] = res;
            deferred.resolve(responseData[ajaxSpec.getName()]);
        }).fail(err =>{
            deferred.reject(err);
        });
        return deferred.promise();
    };
}

