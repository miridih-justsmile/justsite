/// <reference path="../../common/js/global/global.ts" />
/// <reference path="./crud.d.ts" />
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var Crud;
(function (Crud) {
    var CrudAjax = /** @class */ (function (_super) {
        __extends(CrudAjax, _super);
        function CrudAjax(ajaxSetting) {
            return _super.call(this, 'crud', ajaxSetting) || this;
        }
        CrudAjax.prototype.getName = function () {
            return _super.prototype.getName.call(this) + "_" + this.getSetting().type;
        };
        return CrudAjax;
    }(DefaultAjaxSpec));
    function init() {
        $('#btn-save').on('click', save);
        $('#btn-update').on('click', update);
        $('#btn-delete').on('click', del);
    }
    Crud.init = init;
    function runAjax(ajaxData) {
        GlobalAjax.get(new CrudAjax({
            type: ajaxData.type,
            url: ajaxData.url,
            dataType: 'json',
            contentType: 'application/json',
            scriptCharset: 'utf-8',
            data: ajaxData.reqData ? JSON.stringify(ajaxData.reqData) : undefined
        })).done(function (res) {
            if (!!ajaxData.callback) {
                ajaxData.callback(res);
            }
        });
    }
    function list() {
        var data = {
            url: '/api/v1/posts/list',
            type: 'get',
            callback: function (res) {
                console.log(res);
            }
        };
        runAjax(data);
    }
    Crud.list = list;
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
})(Crud || (Crud = {}));
Crud.init();
