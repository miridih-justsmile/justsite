/// <reference path="../../global/js/global.ts" />
/// <reference path="./crud.d.ts" />

namespace Crud {
    class CrudAjax extends DefaultAjaxSpec {
        constructor(ajaxSetting: JQueryAjaxSettings) {
            super("crud", ajaxSetting)
        }
    }

    export function init() {
        $('#btn-save').on('click', save);
        $('#btn-update').on('click', update);
        $('#btn-delete').on('click', del);
    }

    function runAjax(ajaxData: CrudAjaxData) {
        GlobalAjax.get(new CrudAjax({
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
            type: 'get',
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
                title: $('#title').val(),
                content: $('#content').val(),
                author: $('#author').val()
            },
            callback: () => {
                alert('글이 작성되었습니다.');
                window.location.href = '/crud'
            }
        };
        runAjax(data);
    }

    function update() {
        const data: CrudAjaxData = {
            type: 'PUT',
            url: `/api/v1/posts/${$('#id').val()}`,
            reqData: {
                title: $('#title').val(),
                content: $('#content').val()
            },
            callback: () => {
                alert('글이 수정되었습니다.');
                window.location.href = '/crud'
            }
        };
        runAjax(data);
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
        runAjax(data);
    }
}
Crud.init();

