var SlackAPIModule;
(function (SlackAPIModule) {
    function sendMsg(msg) {
        SlackAPI.sendMsg(msg);
    }
    SlackAPIModule.sendMsg = sendMsg;
    var SlackAPI = /** @class */ (function () {
        function SlackAPI() {
        }
        SlackAPI.sendMsg = function (msg) {
            // TODO. AJAX 구현
        };
        return SlackAPI;
    }());
})(SlackAPIModule || (SlackAPIModule = {}));
