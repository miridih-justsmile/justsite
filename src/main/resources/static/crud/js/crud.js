/// <reference path="../../../typings/index.d.ts"/>
var Main;
(function (Main) {
    function init() {
        $('#btn-save').on('click', function (_) {
            save();
        });
        console.log('hi');
        console.log('init 완료');
    }
    Main.init = init;
    function save() {
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
            author: $('#author').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json',
            scriptCharset: 'utf-8',
            data: JSON.stringify(data)
        }).done(function (_) {
            alert('글이 등록되었습니다.');
            window.location.href = '/crud';
        }).fail(function (err) {
            console.error(err);
        });
    }
})(Main || (Main = {}));
Main.init();
