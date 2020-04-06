/// <reference path="../../../typings/index.d.ts"/>
/// <reference path="crud.d.ts" />
var Main;
(function (Main) {
    function init() {
        $('#btn-save').on('click', save);
        $('#btn-update').on('click', update);
        $('#btn-delete').on('click', del);
        console.log('init 완료');
    }
    Main.init = init;
    function runAjax(ajaxData) {
        $.ajax({
            type: ajaxData.type,
            url: ajaxData.url,
            dataType: 'json',
            contentType: 'application/json',
            scriptCharset: 'utf-8',
            data: ajaxData.reqData ? JSON.stringify(ajaxData.reqData) : undefined
        }).done(function (_) {
            if (!!ajaxData.callback) {
                ajaxData.callback();
            }
        }).fail(function (err) {
            console.error(err.responseJSON);
        });
    }
    function save() {
        var data = {
            url: '/api/v1/posts',
            type: 'POST',
            reqData: {
                title: $('#title').val(),
                content: $('#content').val(),
                author: $('#author').val()
            },
            callback: function () {
                alert('글이 작성되었습니다.');
                window.location.href = '/crud';
            }
        };
        runAjax(data);
    }
    function update() {
        var data = {
            type: 'PUT',
            url: "/api/v1/posts/" + $('#id').val(),
            reqData: {
                title: $('#title').val(),
                content: $('#content').val()
            },
            callback: function () {
                alert('글이 수정되었습니다.');
                window.location.href = '/crud';
            }
        };
        runAjax(data);
    }
    function del() {
        var data = {
            type: 'DELETE',
            url: "/api/v1/posts/" + $('#id').val(),
            callback: function () {
                alert('글이 삭제되었습니다.');
                window.location.href = '/crud';
            }
        };
        runAjax(data);
    }
})(Main || (Main = {}));
Main.init();
