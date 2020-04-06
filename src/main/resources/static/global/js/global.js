"use strict";
var DefaultAjaxSpec = (function () {
    function DefaultAjaxSpec(name, ajaxSetting) {
        this.name = name;
        this.ajaxSetting = ajaxSetting;
    }
    DefaultAjaxSpec.prototype.getSetting = function () {
        return this.ajaxSetting;
    };
    DefaultAjaxSpec.prototype.getName = function () {
        return this.name;
    };
    return DefaultAjaxSpec;
}());
var GlobalAjax;
(function (GlobalAjax) {
    var responseData = [];
    function get(data) {
        var deferred = $.Deferred();
        var instanceName = typeof data === "string" ? data : data.getName();
        if (!!responseData[instanceName]) {
            deferred.resolve(responseData[instanceName]);
            return deferred.promise();
        }
        if (typeof data !== "string") {
            init(data).done(function (res) {
                deferred.resolve(res);
            }).fail(function (err) {
                console.error(err);
                deferred.reject(err);
            });
        }
        return deferred.promise();
    }
    GlobalAjax.get = get;
    function init(ajaxSpec) {
        var deferred = $.Deferred();
        $.ajax(ajaxSpec.getSetting()).done(function (res) {
            responseData[ajaxSpec.getName()] = res;
            deferred.resolve(responseData[ajaxSpec.getName()]);
        }).fail(function (err) {
            deferred.reject(err);
        });
        return deferred.promise();
    }
    GlobalAjax.init = init;
})(GlobalAjax || (GlobalAjax = {}));
var DefaultAjaxSpec = (function () {
    function DefaultAjaxSpec(name, ajaxSetting) {
        this.name = name;
        this.ajaxSetting = ajaxSetting;
    }
    DefaultAjaxSpec.prototype.getSetting = function () {
        return this.ajaxSetting;
    };
    DefaultAjaxSpec.prototype.getName = function () {
        return this.name;
    };
    return DefaultAjaxSpec;
}());
//# sourceMappingURL=global.js.map