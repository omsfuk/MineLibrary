# 文档

## 未解决问题
### redis单点并发问题
可能的解决方案：使得session里的状态尽可能的少，若能做到所有状态均为只读状态，则解决问题

## 说明
若无特别说明，request和response均为json格式。
## 通用状态吗
```
200 请求成功
300 请求失败
401 未授权
403 禁止访问
```

## 用户相关
登录
```
req:
{
    username
    password
}

resp:
[ok]
    token: uuid
```
注册
```
req:
{
    username
    password
    email
    phone
    qq
}

```

## :
