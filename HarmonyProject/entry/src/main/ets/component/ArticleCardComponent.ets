import { Article } from '../entity/article/Article'

@Component
@Preview
export struct ArticleCardComponent{
  @State article: Article = {
    id: 'XSA000',
    context: 'context,context,context,context,context,context,context,context,context,context,context',
    title: 'Title',
    subTitle: 'SubTitle',
    authorId: 'AuthorId',
    authorName: 'Author Name',
    stateName: 'adda',
    image: '',
    background: '',
    description: 'description',
    stateId: 0
  }
  build() {
    Column(){
      Text(this.article.title)
        .fontSize(24)
        .fontWeight(FontWeight.Bold)
        .fontColor(Color.Black)
        .alignSelf(ItemAlign.Start)
      Text(this.article.subTitle)
        .fontSize(16)
        .fontWeight(FontWeight.Bold)
        .alignSelf(ItemAlign.Start)
      Row(){
        Text(this.article.description)
          .fontSize(10)
          .alignSelf(ItemAlign.Start)
        Text(this.article.authorName)
          .fontSize(12)
      }
      .justifyContent(FlexAlign.SpaceBetween)
      .width('100%')
      Row(){
        Text(this.article.context)
          .maxLines(2)
          .fontSize(14)
          .textOverflow({
            overflow: TextOverflow.Ellipsis
          })
      }
      .margin({
        top: 15
      })
    }
    .backgroundColor('#ff00d0ff')
    .borderRadius(20)
    .shadow({
      radius: 40,
      color: Color.Black,
      offsetX: 5,
      offsetY: 10
    })
    .padding({
      top: 20,
      left: 15,
      right: 15,
      bottom: 15
    })
  }
}