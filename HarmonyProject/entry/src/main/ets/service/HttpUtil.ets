import { http } from '@kit.NetworkKit'
import { JSON } from '@kit.ArkTS';

export class HttpUtil{
  static baseUrl: string = "http://172.16.10.109:20577/xs_assistant"
  private static token: string

  static async get(url: string,token?: string): Promise<string>{
    let req = http.createHttp();
    url = HttpUtil.baseUrl + url;
    let result: http.HttpResponse = await req.request(url,{
      method: http.RequestMethod.GET,
      readTimeout: 60000,
      connectTimeout: 60000,
      header: {
        "token": token
      }
    })
    return result.result as string;
  }

  static async post(url: string,token?: string,params?: Record<string,string>): Promise<string>{
    let req = http.createHttp();
    let result: http.HttpResponse;
    url = HttpUtil.baseUrl + url
    result = await req.request(url,{
      method: http.RequestMethod.POST,
      readTimeout: 60000,
      connectTimeout: 60000,
      header: {
        "token": token
      }
    })
    return result.result as string;
  }

  static setToken(token: string){
    HttpUtil.token = token
  }

  static getToken(){
    return HttpUtil.token
  }
}

