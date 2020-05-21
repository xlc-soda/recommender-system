# Recommender-System

介绍就只说推荐部分了

推荐算法：实现了因子分解机（fm）和非负矩阵分解（mf）

## 推荐相关的类：

推荐相关的基本上都简单写了下注释

### 接口
service.controller.recommender里有的接口，简单写了下注释，不过基本上看名字也能看懂作用

### 接口实现
实现方法实现在controller对应的service类里

## tips
方法写的参数sessionId其实是从X-Librecmall-Token中取得的用户token，名字懒得改了

防止忘了X-Librecmall-Token是干啥的：README_API中规定从这个header中取用户id

因为一直没等到一起测试的那一天，其他部分主要是面向毕设编程，看得不爽的地方见谅

防止可能用得到，先说下生成数据的在service.streaming这里