import { Customer, CustomerData, LoggedStatus } from "../entity/customer/CustomerData";
import { IResult } from "../entity/IResult";
import { HttpUtil } from "./HttpUtil";
import JSON from "@ohos.util.json";
import { ArkTSUtils } from "@kit.ArkTS";

export class CustomerService{
  private static instance: CustomerService
  preUrl: string = '/account';
  customerData?: CustomerData;

  private constructor() {
  }

  public static getInstance(): CustomerService{
    if(!CustomerService.instance){
      CustomerService.instance = new CustomerService();
    }
    return CustomerService.instance;
  }

  async login(username: string,password: string): Promise<LoggedStatus>{
    let url: string = this.preUrl + "/login?nameOrEmail=" + username +"&password=" + password;
    let json: string;
    json = await HttpUtil.post(url)
    console.log(json!);
    let result: IResult<CustomerData> = JSON.parse(json) as IResult<CustomerData>
    console.log(result.message)
    this.customerData = result.data;
    HttpUtil.setToken(this.customerData?.token!)
    return {
      message: result.message,
      isSuccess: result.status === '200'
    }
  }

  getCustomerInfo(): Customer{
    return this.customerData!.customer!;
  }
}