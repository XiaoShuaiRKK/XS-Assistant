import { JSON, List } from "@kit.ArkTS";
import { Article } from "../entity/article/Article";
import { IResult } from "../entity/IResult";
import { HttpUtil } from "./HttpUtil";
import { promptAction } from "@kit.ArkUI";

export class ArticleService{
  private static instance: ArticleService
  private constructor() {
  }
  public static getInstance(){
    if(ArticleService.instance == null){
      ArticleService.instance = new ArticleService();
    }
    return ArticleService.instance
  }
  preUrl: string = '/article'
  async getArticles(page: number,size: number): Promise<Article[]>{
    let url: string = this.preUrl + "/search/get/page?page=" + page + "&size=" + size
    let json = await HttpUtil.get(url,HttpUtil.getToken())
    console.log(json)
    let result: IResult<Article[]> = JSON.parse(json) as IResult<Article[]>
    if(result.status === '200'){
      return result.data!
    }
    promptAction.showToast({
      message: result.message
    })
    return []
  }
}