# KaKaCL Store门店系统

## 介绍
Store系统基于SpringBoot 2，完美整合springmvc + shiro + mybatis-plus + beetl!项目主要实现门店，店长店员和后台管理功能。

### KaKaCL v1.3.0更新内容

> * 新增店员查看应聘信息
> * 更新应聘信息查询条件
> * 列表增加公司名称

## jwt token鉴权机制
jwt token鉴权机制是指若需要请求服务器接口，必须通过AuthController获取一个请求令牌(jwt token)，持有jwt token的用户才可以访问服务器的其他资源，如果没有此令牌，则访问接口会直接忽略，请求获取jwt token时，需要携带credenceName和credenceCode(可以是账号密码，可以是手机号验证码等等)，校验credenceName和credenceCode成功后，会颁发给客户端一个jwt token还有一个随机字符串，用于传输过程中对数据进行签名用，签名机制请见下面介绍.基于token的鉴权机制类似于http协议也是无状态的，它不需要在服务端去保留用户的认证信息或者会话信息.这就意味着基于token认证机制的应用不需要去考虑用户在哪一台服务器登录了，这就为应用的扩展提供了便利.

## 签名机制
签名机制是指客户端向服务端传输数据中，对传输数据进行md5加密，并且加密过程中利用Auth接口返回的随机字符串进行混淆加密，并把md5值同时附带给服务端，服务端通获取数据之后对数据再进行一次md5加密，若加密结果和客户端传来的数据一致，则认定客户端请求的数据是没有被篡改的，若不一致，则认为被加密的数据是被篡改的


guns-1.2.5
