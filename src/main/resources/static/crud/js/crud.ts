/// <reference path="../../common/js/global/global.ts" />
/// <reference path="./crud.d.ts" />

namespace Crud {
    class CrudAjax extends DefaultAjaxSpec {
        constructor(ajaxSetting: JQueryAjaxSettings) {
            super('crud', ajaxSetting)
        }
        getName(): string {
            return `${super.getName()}_${this.getSetting().type}`;
        }
    }

    export function init() : void {
        $('#btn-save').on('click', save);
        $('#btn-update').on('click', update);
        $('#btn-delete').on('click', del);
    }

    /**
     * @param ajaxData : CrudAjaxData
     * @param isInitRun : boolean 무조건 새로운 호출이 필요한가?
     */
    function runAjax(ajaxData: CrudAjaxData, isInitRun : boolean = false) {
        const runModule : Function = isInitRun ? GlobalAjax.run : GlobalAjax.get;
        runModule(new CrudAjax({
            type: ajaxData.type,
            url: ajaxData.url,
            dataType: 'json',
            contentType: 'application/json',
            scriptCharset: 'utf-8',
            data: ajaxData.reqData ? JSON.stringify(ajaxData.reqData) : undefined
        })).done(res => {
            if (!!ajaxData.callback) {
                ajaxData.callback(res);
            }
        });
    }

    export function list() {
        const data: CrudAjaxData = {
            url: '/api/v1/posts/list',
            type: 'GET',
            callback: (res) => {
                console.log(res)
            }
        };
        runAjax(data);
    }

    function save() {
        const data: CrudAjaxData = {
            url: '/api/v1/posts',
            type: 'POST',
            reqData: {
                title: domValidity('#title').val(),
                content: domValidity('#content').val(),
                author: domValidity('#author', 2).val()
            },
            callback: () => {
                alert('글이 작성되었습니다.');
                window.location.href = '/crud'
            }
        };
        runAjax(data, true);
    }

    function update() {
        const data: CrudAjaxData = {
            type: 'PUT',
            url: `/api/v1/posts/${$('#id').val()}`,
            reqData: {
                title: domValidity('#title').val(),
                content: domValidity('#content').val()
            },
            callback: () => {
                alert('글이 수정되었습니다.');
                window.location.href = '/crud'
            }
        };
        runAjax(data, true);
    }

    function del() {
        const data: CrudAjaxData = {
            type: 'DELETE',
            url: `/api/v1/posts/${$('#id').val()}`,
            callback: () => {
                alert('글이 삭제되었습니다.');
                window.location.href = '/crud'
            }
        };
        runAjax(data, true);
    }

    function domValidity(selectorName : string, minLength : number = 5) : JQuery {
        const $selector = $(selectorName);
        if(!StringUtil.isValidity($selector.val(), {minLength : minLength})) {
            const errMsg = `${selectorName}을(를) 최소 ${minLength}글자 이상입력해주세요.`;
            alert(errMsg);
            throw Error(errMsg);
        }
        return $selector;
    }
}
Crud.init();
console.log('crud script 실행ㅇㅇㅇ');
