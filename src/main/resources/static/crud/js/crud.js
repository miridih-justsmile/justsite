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
    /**
     * @param ajaxData : CrudAjaxData
     * @param isInitRun : boolean 무조건 새로운 호출이 필요한가?
     */
    function runAjax(ajaxData, isInitRun) {
        if (isInitRun === void 0) { isInitRun = false; }
        var runModule = isInitRun ? GlobalAjax.run : GlobalAjax.get;
        runModule(new CrudAjax({
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
            type: 'GET',
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
                title: domValidity('#title').val(),
                content: domValidity('#content').val(),
                author: domValidity('#author', 2).val()
            },
            callback: function () {
                alert('글이 작성되었습니다.');
                window.location.href = '/crud';
            }
        };
        runAjax(data, true);
    }
    function update() {
        var data = {
            type: 'PUT',
            url: "/api/v1/posts/" + $('#id').val(),
            reqData: {
                title: domValidity('#title').val(),
                content: domValidity('#content').val()
            },
            callback: function () {
                alert('글이 수정되었습니다.');
                window.location.href = '/crud';
            }
        };
        runAjax(data, true);
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
        runAjax(data, true);
    }
    function domValidity(selectorName, minLength) {
        if (minLength === void 0) { minLength = 5; }
        var $selector = $(selectorName);
        if (!StringUtil.isValidity($selector.val(), { minLength: minLength })) {
            var errMsg = selectorName + "\uC744(\uB97C) \uCD5C\uC18C " + minLength + "\uAE00\uC790 \uC774\uC0C1\uC785\uB825\uD574\uC8FC\uC138\uC694.";
            alert(errMsg);
            throw Error(errMsg);
        }
        return $selector;
    }
})(Crud || (Crud = {}));
Crud.init();
console.log('crud script 실행ㅇㅇㅇ');
