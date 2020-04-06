/// <reference path="./AjaxSpec.d.ts" />

/**
 * 기본적인 AjaxSpec.
 */
abstract class DefaultAjaxSpec implements AjaxSpec{
    protected name : string;
    protected ajaxSetting : JQueryAjaxSettings;

    protected constructor(name : string, ajaxSetting : JQueryAjaxSettings){
        this.name = name;
        this.ajaxSetting = ajaxSetting;
    }

    public getSetting(){
        return this.ajaxSetting;
    }

    public getName() {
        return this.name;
    }
}