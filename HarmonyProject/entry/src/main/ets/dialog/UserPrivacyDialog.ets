@Preview
@CustomDialog
export default struct UserPrivacyDialog{
  controller: CustomDialogController = new CustomDialogController({builder: ''})
  cancel: Function = () => {}
  confirm: Function = () => {}
  build() {
    Column({space: 10}){
      Text($r('app.string.welcome_title'))
        .fontSize(20)
        .fontWeight(FontWeight.Bolder)
      Text(){
        Span($r('app.string.welcome_span_zero'))
      }
      Text(){
        Span($r('app.string.welcome_span_one'))
        Span($r('app.string.xs_assistant_term_one'))
          .fontColor($r('app.color.term_background'))
        Span('和')
        Span($r('app.string.xs_assistant_term_two'))
          .fontColor($r('app.color.term_background'))
        Span($r('app.string.welcome_span_two'))
      }
      Button('同意')
        .fontColor(Color.White)
        .backgroundColor($r('app.color.button_background'))
        .width(150)
        .onClick(() => {
          this.confirm()
          this.controller.close()
        })
      Button('不同意')
        .backgroundColor($r('app.color.cancel_button_background'))
        .width(150)
        .onClick(() => {
          this.cancel()
          this.controller.close()
        })
    }
    .width('80%')
    .height('75%')
    .padding({
      top: 20
    })
  }
}