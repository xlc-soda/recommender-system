# Recommender-System

介绍就只说推荐部分了

推荐算法：实现了因子分解机（fm）和非负矩阵分解（mf）

## 推荐相关的类：

推荐相关的基本上都简单写了下注释

### 接口
service.controller.recommender里有的接口，简单写了下注释，不过基本上看名字也能看懂作用

### 接口实现
实现方法实现在controller对应的service类里

### 评分矩阵

#### 默认评分
非负矩阵分解的这个算法要求每一列至少有一个非零项，所以在NonNegativeMatrixFactorization类中的增量学习方法trainIncr中设置了一个默认项，评分为3

#### 评分体系
采用了五级分制，1是讨厌，3是中性，5是喜欢

### 非负矩阵分解
算法具体实现见NonNegativeMatrixFactorization类，在代码最上方写了一些用于讲解成员意义的注释

## tips
方法写的参数sessionId其实是从X-Librecmall-Token中取得的用户token，名字懒得改了

防止忘了X-Librecmall-Token是干啥的：README_API中规定从这个header中取用户id

因为一直没等到一起测试的那一天，其他部分主要是面向毕设编程，看得不爽的地方见谅

防止可能用得到，先说下生成数据的在service.streaming这里