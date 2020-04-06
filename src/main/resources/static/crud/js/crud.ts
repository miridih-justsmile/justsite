/// <reference path="../../../typings/index.d.ts"/>
/// <reference path="crud.d.ts" />
namespace Main {
    export function init() {
        $('#btn-save').on('click', save);
        $('#btn-update').on('click', update);
        $('#btn-delete').on('click', del);
        console.log('init 완료');
    }

    function runAjax(ajaxData: AjaxData) {
        $.ajax({
            type: ajaxData.type,
            url: ajaxData.url,
            dataType: 'json',
            contentType: 'application/json',
            scriptCharset: 'utf-8',
            data: ajaxData.reqData ? JSON.stringify(ajaxData.reqData) : undefined
        }).done(_ => {
            if (!!ajaxData.callback) {
                ajaxData.callback();
            }
        }).fail((err) => {
            console.error(err.responseJSON);
        });
    }

    function save() {
        const data: AjaxData = {
            url: '/api/v1/posts',
            type: 'POST',
            reqData: {
                title: $('#title').val(),
                content: $('#content').val(),
                author: $('#author').val()
            },
            callback: function () {
                alert('글이 작성되었습니다.');
                window.location.href = '/crud'
            }
        };
        runAjax(data);
    }

    function update() {
        const data: AjaxData = {
            type: 'PUT',
            url: `/api/v1/posts/${$('#id').val()}`,
            reqData: {
                title: $('#title').val(),
                content: $('#content').val()
            },
            callback: function () {
                alert('글이 수정되었습니다.');
                window.location.href = '/crud'
            }
        };
        runAjax(data);
    }

    function del() {
        const data: AjaxData = {
            type: 'DELETE',
            url: `/api/v1/posts/${$('#id').val()}`,
            callback: function () {
                alert('글이 삭제되었습니다.');
                window.location.href = '/crud'
            }
        };
        runAjax(data);
    }
}
Main.init();