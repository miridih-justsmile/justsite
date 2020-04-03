interface AjaxData {
    type: string;
    url: string;
    reqData?: {
        title: string;
        author?: string;
        content: string;
    };
    callback?: Function;
}
