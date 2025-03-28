# dtpay聚合支付系统业务和技术架构介绍

**dtpay支付系统**是一套开源聚合支付系统，以聚合支付为核心业务，融合了商户收款管理、支付网关、电子钱包、报表产品生态矩阵等为一体的智能聚合支付系统。 可为有聚合支付业务需求的客户提供一套完整的支付解决方案。欢迎加入开源项目，让我们一起把项目从0到1进行开发完善。

**dtpay支付系统**是由一个支付网关，运营商、商户、报表等多个平台组成的综合性收款平台。商户可借本系统中的商户管理子系统完成商家支付管理、会员营销管理等业务。

## 1 系统构成

![dtpay-系统构成.png](https://note.youdao.com/yws/api/personal/file/WEB6398d12221dbca6c368fab77ac53accd?method=download&shareKey=02a0602e7427a3f151a1a9e2a3da0743)

## 2 dtpay支付功能架构

![dtpay系统功能.png](https://gitee.com/itbeien/base/raw/master/images/dtpay业务和技术架构-知识星球.png)

## 3 业务模块子系统

![运营平台.png](https://gitee.com/itbeien/base/raw/master/images/dtpay运营管理平台功能模块-知识星球.png)

![商户平台.png](https://note.youdao.com/yws/api/personal/file/WEBe6d4288f2adf5f502cd76376b6135ac2?method=download&shareKey=83af62fe19a8bb8076646fcf5ca91c40)

## 4 dtpay2.0版本开发

基于JDK17/21和SpringBoot3开发dtpay聚合支付系统2.0版本，以下为具体的进度表(实时更新)，代码见分支JDK17

| 模块           | 说明         | 进度   | 优先级 |
| -------------- | ------------ | ------ | ------ |
| dtpay-merchant | 商户管理后台 | 进行中 | 高     |
| dtpay-admin    | 运营管理后台 | 未开始 | 高     |
| dtpay-payment  | 支付交易系统 | 进行中 | 高     |

## 5 技术栈介绍

dtpay服务端基于JDK17/21开发，开发工具IDEA2024.3.5，使用Maven3.9.9构建

| 框架                | 描述                        | 版本     | 官方网站                                                     |
| :------------------ | :-------------------------- | :------- | ------------------------------------------------------------ |
| JDK                 | 基础环境                    | 17/21    | https://bell-sw.com/pages/downloads/#jdk-17-lts \| https://bell-sw.com/pages/downloads/#jdk-21-lts |
| SpringBoot          | 基础环境                    | 3.4.4    | https://spring.io/projects/spring-boot                       |
| SpringSecurity      | 安全权限框架                | 6.4.4    | https://spring.io/projects/spring-security                   |
| SpringFramework     | 基础环境                    | 6.2.5    | https://spring.io/projects/spring-framework                  |
| SpringCloud         | 微服务版本基础环境          | 2024.0.1 | https://spring.io/projects/spring-cloud                      |
| Redis               | 分布式缓存                  | Latest   | https://redis.io/                                            |
| Nacos               | 注册中心/配置中心           | Latest   | https://nacos.io/                                            |
| MySQL               | 数据库                      | 8.4.4    | https://dev.mysql.com/downloads/mysql/                       |
| RocketMQ            | 消息中间件                  | Latest   | https://rocketmq.apache.org/zh/                              |
| Vue                 | 运营和商户平台前端框架      | Latest   | https://cn.vuejs.org/                                        |
| Vue Router          | 路由管理库                  | Latest   | https://router.vuejs.org/zh/                                 |
| Pinia               | 状态管理库                  | Latest   | https://pinia.vuejs.org/zh/                                  |
| Vite                | 前端构建工具                | Latest   | https://cn.vite.dev/                                         |
| Uni-app             | 前端小程序框架              | Latest   | https://zh.uniapp.dcloud.io/                                 |
| Knife4j             | api文档生成框架             | Latest   | https://github.com/xiaoymin/knife4j                          |
| ShardingJDBC        | 分库分表框架                | Latest   | https://shardingsphere.apache.org/index_zh.html              |
| Mockito             | Java Mock框架               | Latest   | https://github.com/mockito/mockito                           |
| MyBatis             | 持久层框架                  | 3.5.19   | https://github.com/mybatis/mybatis-3                         |
| Spring Data Redis   | Redis客户端框架             | 3.4.4    | https://spring.io/projects/spring-data-redis                 |
| JWT                 | JWT是用于创建令牌的开放标准 | Latest   | https://jwt.io/ \| https://github.com/jwtk/jjwt              |
| Seata               | 分布式事务框架              | Latest   | https://seata.apache.org/zh-cn/                              |
| Sentinel            | 限流框架                    | Latest   | https://sentinelguard.io/zh-cn/index.html                    |
| XXL-JOB             | 分布式定时任务              | Latest   | https://github.com/xuxueli/xxl-job                           |
| Quartz              | 定时任务框架                | Latest   | https://github.com/quartz-scheduler/quartz                   |
| Mybatis-Spring-Boot | SpringBoot整合MyBatis组件   | 3.0.4    | https://github.com/mybatis/spring-boot-starter               |

## 6 服务端模块介绍

| 子模块名称     | 模块描述               | 端口 |
| :------------- | :--------------------- | :--- |
| dtpay-common   | 核心工具、各模块通用类 |      |
| dtpay-service  | 公共服务类             |      |
| dtpay-payment  | 支付交易系统           | 9028 |
| dtpay-admin    | 运营平台               | 9029 |
| dtpay-merchant | 商户平台               | 9030 |
| dtpay-task     | 定时任务(佣金，对账)   | 9031 |
| dtpay-bi       | 报表系统               | 9032 |
| dtpay-ewallet  | 电子钱包               | 9033 |

## 7 加入我的社群

![](https://gitee.com/itbeien/base/raw/master/images/贝恩聊架构-知识星球-含知识星球二维码new.png)