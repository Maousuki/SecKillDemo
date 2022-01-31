# SecKillDemo
简单的商品秒杀项目

## 项目框架

SpringBoot

## 相关技术栈

Thymeleaf，MyBatis-Plus，Redis，RabbitMQ，MySQL 8

## 功能开发

- 商品列表
- 商品详情
- 秒杀
- 订单详情

## 分布式会话

1. 用户登录
   - 明文密码二次MD5加密
   - 参数校验+全局异常处理
2. 共享Session
   - Redis实现

## 系统测压

1. JMeter
2. 自定义变量模拟多用户
3. 测压对象
   - 商品列表
   - 秒杀

## 页面优化

1. 页面缓存+URL缓存+对象缓存
2. 页面静态化（可用前后端分离）
3. 静态资源优化（未实现）
4. CDN优化（未实现）

## 接口优化

1. Redis预减库存减少数据库访问
2. 内存标记减少Redis的访问
3. RabbitMQ异步下单

## 安全优化

1. 秒杀接口地址隐藏
2. 接口防刷
3. 增加验证码（未实现）

## 学习链接

感谢老师的讲解！

[【完整项目实战】半天带你用-springBoot、Redis轻松实现Java高并发秒杀系统-我们要能够撑住100W级压力_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1sf4y1L7KE)

