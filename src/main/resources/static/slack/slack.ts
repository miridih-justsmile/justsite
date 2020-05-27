/// <reference path="../common/js/global/global.ts" />

namespace SlackAPIModule {

    export function init() {
        console.log('SlackAPIModule init 시작');
        $('.js-send').on('click', function() {
            SlackAPI.sendMsg($('.js-slack-msg').val());
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
        public static sendMsg(msg : string) {
            GlobalAjax.run(new SlackAjax({
                url : "/slack/msg",
                data: {msg: msg},
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