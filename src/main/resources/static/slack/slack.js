/// <reference path="../common/js/global/global.ts" />
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
var SlackAPIModule;
(function (SlackAPIModule) {
    function init() {
        console.log('SlackAPIModule init 시작');
        $('.js-send').on('click', function () {
            SlackAPI.sendMsg($('.js-slack-msg').val());
        });
    }
    SlackAPIModule.init = init;
    var SlackAjax = /** @class */ (function (_super) {
        __extends(SlackAjax, _super);
        function SlackAjax(ajaxSetting) {
            return _super.call(this, 'slack', ajaxSetting) || this;
        }
        SlackAjax.prototype.getName = function () {
            return _super.prototype.getName.call(this) + "_" + this.getSetting().type;
        };
        return SlackAjax;
    }(DefaultAjaxSpec));
    var SlackAPI = /** @class */ (function () {
        function SlackAPI() {
        }
        SlackAPI.sendMsg = function (msg) {
            GlobalAjax.run(new SlackAjax({
                url: "/slack/msg",
                data: { msg: msg },
                type: 'POST'
            })).done(function (res) {
                console.log(res);
            }).fail(function (err) {
                console.error(err);
            });
        };
        return SlackAPI;
    }());
})(SlackAPIModule || (SlackAPIModule = {}));
SlackAPIModule.init();
