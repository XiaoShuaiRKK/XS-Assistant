import { Customer } from '../entity/customer/CustomerData';
import { CustomerService } from '../service/CustomerService';
import { Article } from '../entity/article/Article';
import { ArticleService } from '../service/ArticleService';
import { ArticleCardComponent } from '../component/ArticleCardComponent'

@Entry
@Component
@Preview
struct MainIndex{
  customerService: CustomerService = CustomerService.getInstance();
  articleService: ArticleService = ArticleService.getInstance();
  customer?: Customer;
  @State articlesList?: Article[] = []

  async aboutToAppear(): Promise<void> {
    this.customer = this.customerService.getCustomerInfo()
    this.articlesList = await this.articleService.getArticles(1,10)
  }

  build() {
    Column(){
      Row({space: 20}){
        Image(this.customer?.iconPath)
          .borderRadius("100%")
          .clip(true)
          .width(30)
        Text(this.customer?.firstName)
        Text(this.customer?.lastName)
      }
      Text("发现")
        .fontWeight(FontWeight.Bold)
        .fontSize(32)
        .fontColor(Color.Black)
        .alignSelf(ItemAlign.Start)
        .margin({
          bottom: 10
        })
      List({
        space: 20
      }){
        ForEach(this.articlesList,(item: Article,index) => {
          ListItem(){
            ArticleCardComponent({
              article: item
            })
          }
          .margin({
            right: 10
          })
        })
      }
    }
    .padding({
      left: 10,
      right: 10,
      top: 10
    })
  }
}