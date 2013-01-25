SpringReferenceToChm
====================
CHM format Spring Reference Document.

Spring的官方参考文档是html格式的,并且没有目录树,用它本身的跳转功能,跳来跳去经常把头给跳晕了!
最重要的一个缺点是没有全文搜索,于是一生气就做了一个CHM格式的有目录,带全文搜索的最新Spring3.2官方参考文档,好东西不敢独享,特分享给大家!
透露个小秘密:Spring3.2参考里有1400多个目录,我不可能一个一个输入,而是利用Jodd里的类似jQuery的select语法写程序生成的.再次证明"懒人是推动世界前进的动力".

文件说明:
dist\Spring3.2 Reference.chm  生成的CHM文件
src\java\Spring3.XCatalog.html Spring参考目录里的index.html文件
src\java\SpringChmParse.java 生成目录的Java源代码
src\WinCHM\Spring3参考指南.wcp WinCHM格式的项目文件.
