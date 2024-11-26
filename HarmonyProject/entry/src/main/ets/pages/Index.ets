import UserPrivacyDialog from '../dialog/UserPrivacyDialog';
import { common } from '@kit.AbilityKit';
import data_preferences from '@ohos.data.preferences';
import { promptAction, router } from '@kit.ArkUI';
import { CustomerService } from '../service/CustomerService';
import { LoggedStatus } from '../entity/customer/CustomerData';

//定义常量存储首选项中的键
const H_STORE: string = "XS_ASSISTANT"
const IS_PRIVACY: string = "isPrivacy"

@Entry
@Component
struct Index {
  customerService: CustomerService = CustomerService.getInstance();
  context: common.UIAbilityContext = getContext(this) as common.UIAbilityContext;
  dialogController: CustomDialogController = new CustomDialogController({
    builder: UserPrivacyDialog({
      cancel: () => {this.exitApp()},
      confirm: () => {this.onConfirm()}
    })
  })
  @State message: string = 'Hello';
  @State loginTitle: string = '登录';
  @State icon: string = 'http://120.24.88.92:9000/xs-assistant-bucket/icon-2.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20241106%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20241106T092309Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=15aea3a1d63c7235bce8e13436c57427129da1739f050104f1e62c16f409a4e3';
  @State username: string = '';
  @State password: string = '';

  onConfirm(){
    let preferences = data_preferences.getPreferences(this.context,H_STORE)
    preferences.then((res) => {
      res.put(IS_PRIVACY, true).then(() => {
        res.flush()
      })
    })
  }

  exitApp(){
    this.context.terminateSelf()
  }

  aboutToAppear(): void {
    let preferences = data_preferences.getPreferences(this.context,H_STORE)
    preferences.then((res) => {
      res.get(IS_PRIVACY, false).then((isPrivate) => {
        if(isPrivate === false){
          this.dialogController.open()
        }else{

        }
      })
    })
  }

  jumpToMain(){
    setTimeout(() => {
      router.replaceUrl({
        url: 'pages/MainIndex'
      },2000)
    })
  }

  aboutToDisappear(): void {
    clearTimeout()
  }

  build() {
    // RelativeContainer() {
    //   Text(this.message)
    //     .id('HelloWorld')
    //     .fontSize(50)
    //     .fontWeight(FontWeight.Bold)
    //     .alignRules({
    //       center: { anchor: '__container__', align: VerticalAlign.Center },
    //       middle: { anchor: '__container__', align: HorizontalAlign.Center }
    //     })
    //
    //   Button(this.loginTitle)
    //     .id('login_btn')
    //     .fontSize(20)
    // }
    // .height('100%')
    // .width('100%')
    Column({ space: 15 }){
      Image(this.icon)
        .borderRadius('100%')
        .clip(true)
        .width(50)
        .margin({
          bottom: 50,
          top: 50
        })
      TextInput({
        placeholder: '请输入邮箱'
      })
        .onChange((value) => {
          this.username = value
        })
      TextInput({
        placeholder: '请输入密码'
      }).type(InputType.Password)
        .onChange((value) => {
          this.password = value
        })

      Button(this.loginTitle)
        .width('100%')
        .margin({
          top: 30
        })
        .onClick(async () => {
          let logged = await this.customerService.login(this.username,this.password)
          console.error("=================" + logged.message)
          promptAction.showToast({
            message: logged.message
          })
          if(logged.isSuccess){
            router.replaceUrl({
              url: 'pages/MainIndex'
            })
          }
        })
      Row({ space: 15 }){
        Text('前往注册')
        Text('忘记密码')
        Text('用户条款')
          .onClick(() => {
            this.dialogController.open()
          })
      }
    }
    .width('100%')
    .padding(30)
  }
}