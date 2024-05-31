**dtpay支付系统**是一套开源聚合支付系统，以聚合支付为核心业务，融合了商户收款管理、支付网关、电子钱包、报表产品生态矩阵等为一体的智能聚合支付系统。 可为有聚合支付业务需求的客户提供一套完整的支付解决方案。欢迎加入开源项目，让我们一起把项目从0到1进行开发完善。

**dtpay支付系统**是由一个支付网关，运营商、商户、报表等多个平台组成的综合性收款平台。商户可借本系统中的商户管理子系统完成商家支付管理、会员营销管理等业务。

## 系统构成

![dtpay-系统构成.png](https://note.youdao.com/yws/api/personal/file/WEB6398d12221dbca6c368fab77ac53accd?method=download&shareKey=02a0602e7427a3f151a1a9e2a3da0743)

## dtpay支付功能架构

![dtpay系统功能.png](https://note.youdao.com/yws/api/personal/file/WEB22d3044a0b0d80f9837bd61d0e69ba77?method=download&shareKey=6ca1d1ede02b990204dbf7274dca6f0d)

## 业务模块子系统

![运营平台.png](https://note.youdao.com/yws/api/personal/file/WEB1257424ff3a4977729e59ce6e7eee50f?method=download&shareKey=91128238b55d34d4b181bf9cde6f20a9)

![商户平台.png](https://note.youdao.com/yws/api/personal/file/WEBe6d4288f2adf5f502cd76376b6135ac2?method=download&shareKey=83af62fe19a8bb8076646fcf5ca91c40)

dtpay服务端基于jdk1.8开发，开发工具IDEA2022.3，使用maven3.9.2构建

技术栈介绍

| 框架           | 描述                   | 版本                                       |
| :----------- | :------------------- | :--------------------------------------- |
| JDK          | Java运行环境             | 1.8                                      |
| SpringBoot   | 基于SpringBoot完成后端代码开发 | 2.7.18                                   |
| SpringCloud  | 微服务框架                | [2021.0.x](https://github.com/spring-cloud/spring-cloud-release/wiki/Spring-Cloud-2021.0-Release-Notes) aka Jubile |
| Redis        | 分布式缓存                | 6.2                                      |
| Nacos        | 注册中心/配置中心            | 2.3.2                                    |
| MySQL        | 数据库                  | 5.7.x                                    |
| RocketMQ     | 消息中间件                | 5.0                                      |
| Vue          | 运营和商户平台前端框架          | 3.x                                      |
| Uni-app      | 前端小程序框架              |                                          |
| Swagger      | api文档生成框架            |                                          |
| ShardingJDBC | 分库分表框架               |                                          |
| Mockito      | Java Mock框架          |                                          |
| MyBatis      | 持久层框架                |                                          |
| Redisson     | redis客户端框架           |                                          |
| Screw        | 数据库文档生成工具            |                                          |
| Seata        | 分布式事务框架              |                                          |
| Sentinel     | 限流框架                 |                                          |
| xxl-job      | 分布式定时任务              |                                          |

服务端模块介绍

| 子模块名称          | 模块描述        | 端口   |
| :------------- | :---------- | :--- |
| dtpay-core     | 核心工具类       |      |
| dtpay-service  | 公共服务类       |      |
| dtpay-gateway  | 支付网关        | 9028 |
| dtpay-manager  | 运营平台        | 9029 |
| dtpay-merchant | 商户平台        | 9030 |
| dtpay-task     | 定时任务(佣金，对账) | 9031 |
| dtpay-bi       | 报表系统        | 9032 |
| dtpay-ewallet  | 电子钱包        | 9033 |

##  加入我的社群

![](https://gitee.com/starqidian/images/raw/master/javabase/%E8%B4%9D%E6%81%A9%E8%81%8A%E6%9E%B6%E6%9E%84-%E7%9F%A5%E8%AF%86%E6%98%9F%E7%90%83-%E8%81%9A%E5%90%88%E6%94%AF%E4%BB%98%E7%9F%A5%E8%AF%86%E6%98%9F%E7%90%83_01.png)