import request from "@/static/js/Util/request.js";

export function register(data){
    return request({
        url: '/account/register',
        method: 'post',
        data: data
    })
}

export function registerSendCode(params){
    return request({
        url: '/account/sendCode',
         method: 'post',
         params: params
    })
}