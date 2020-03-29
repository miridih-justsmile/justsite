/// <reference path="../../../typings/index.d.ts"/>

interface AjaxData {
    title : string;
    author : string;
    content : string;
}

namespace Main {
    export function init() {
        $('#btn-save').on('click', _=>{
            save();
        });
        console.log('init 완료');
    }
    function save() {
        const data : AjaxData = {
            title : $('#title').val(),
            content : $('#content').val(),
            author : $('#author').val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json',
            scriptCharset : 'utf-8',
            data : JSON.stringify(data)
        }).done(_=>{
            alert('글이 등록되었습니다.');
            window.location.href = '/crud'
        }).fail((err) =>{
            console.error(err);
        });
    }
}
Main.init();