export class CustomerData{
  customer?: Customer
  token?: string
}

export class Customer{
  id?: number
  firstName?: string
  lastName?: string
  email?: string
  birth?: string
  idNumber?: string
  areaId?: number
  stateId?: number
  level?: number
  iconPath?: string
}

export class LoggedStatus{
  message?: string
  isSuccess?: boolean
}