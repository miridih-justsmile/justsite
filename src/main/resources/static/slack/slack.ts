/// <reference path="../common/js/global/global.ts" />

namespace SlackAPIModule {
    export function init() {
        console.log('SlackAPIModule init 시작');
        $('.js-msg-send').on('click', function() {
            SlackAPI.sendMsg($('.js-slack-msg').val(), $('.js-target-name').val());
        });

    }

    class SlackAjax extends DefaultAjaxSpec {
        constructor(ajaxSetting: JQueryAjaxSettings) {
            super('slack', ajaxSetting)
        }
        getName(): string {
            return `${super.getName()}_${this.getSetting().type}`;
        }
    }

    class SlackAPI {
        public static sendMsg(msg : string, targetName : string) {
            GlobalAjax.run(new SlackAjax({
                url : "/slack/msg",
                data: {msg: msg, target:targetName},
                type: 'POST'
            })).done(res=>{
                console.log(res)
            }).fail(err=> {
                console.error(err);
            })

        }
    }
}
SlackAPIModule.init();