import request from "@/static/js/Util/request.js";

export function accountLogin(params){
    return request({
        url: '/account/login',
        method: 'post',
        params: params
    })
}