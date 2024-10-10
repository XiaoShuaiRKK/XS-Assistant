import request from "@/static/js/Util/request.js";

export function getArticleHotTop(params){
    return request({
        url: '/search/query/get/orderHot',
        method: 'get',
        params: params
    })
}