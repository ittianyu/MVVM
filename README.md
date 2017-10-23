## 简介 ##

### 背景 ###
这几年 MVP 架构在安卓界非常流行，几乎已经成为主流框架，它让业务逻辑 和 UI操作相对独立，使得代码结构更清晰。

![](http://upload-images.jianshu.io/upload_images/2036280-f4bf081e57dbdfff.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

MVVM 在前端火得一塌糊涂，而在安卓这边却基本没见到几个人在用，看到介绍 MVVM 也最多是讲 DataBinding 或 介绍思想的。偶尔看到几篇提到应用的，还是对谷歌官网的[Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html) 文章的翻译。

相信大家看别人博客或官方文档的时候，总会碰到一些坑。要么入门教程写得太复杂（无力吐槽，前面写一堆原理，各种高大上的图，然并卵，到实践部分一笔带过，你确定真的是入门教程吗）。要么就是简单得就是一个 hello world，然后就没有下文了（看了想骂人）。

![](http://upload-images.jianshu.io/upload_images/2036280-aedda65b339fb994.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

实在看不下去的我，决定插手你的人生。

### 目录 ###

《安卓-深入浅出MVVM教程》大致分两部分：应用篇、原理篇。
采用循序渐进方式，内容深入浅出，符合人类学习规律，希望大家用最少时间掌握 MVVM。

#### 应用篇： ####
- [01 Hello MVVM (快速入门)](http://www.jianshu.com/p/bcdb7c2a07eb)
- [02 Repository （数据仓库）](http://www.jianshu.com/p/6a1e32206dfc)
- [03 Cache （本地缓存）](http://www.jianshu.com/p/cf9482d71241)
- [04 State Lcee （加载/空/错误/内容视图）](http://www.jianshu.com/p/26de1ad0a423)
- [05 Simple Data Source （简单的数据源）](http://www.jianshu.com/p/246b54237e5d)
- [06 Load More （加载更多）](http://www.jianshu.com/p/7ace2a416587)
- [07 DataBinding （数据与视图绑定）](http://www.jianshu.com/p/dba2023b07e3)
- [08 RxJava2](http://www.jianshu.com/p/fcee079651d6)
- 09 Dragger2
- 10 Abstract （抽象）
- 11 Demo （例子）
- 12-n 待定（欢迎 github 提建议）

#### 原理篇 ####
- [01 MyLiveData（最简单的LiveData）](http://www.jianshu.com/p/74190725cf9c)
- 02-n 待定（并不是解读源码，那样太无聊了，打算带你从0撸一个 Architecture）


### 关于提问 ###
本人水平和精力有限，如果有大佬发现哪里写错了或有好的建议，欢迎在本教程附带的 [github仓库](https://github.com/ittianyu/MVVM) 提issue。
What？为什么不在博客留言？考虑到国内转载基本无视版权的情况，一般来说你都不是在源出处看到这篇文章，所以留言我也一般是看不到的。

### 项目说明 ###

应用篇放在 app 模块下，原理篇放在 implementation 模块下。
每一节代码采用不同包名，相互独立。


### 更新说明 ###

由于工作和学习的原因，很少时间来写博客，之前国庆长假，一口气写了 8 篇，然后在今天整理并发布出来。

今后尽量保持周更，但完全有可能周末特别忙，所以我能保证的是月更。

如果各位碰到什么问题，欢迎提 issue，对于后面待定章节的内容，也欢迎提供您宝贵的意见。
