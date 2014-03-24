cmmi-pattern-archetype
======================

Java 服务工程模型 

运行install.bat将archetype安装到本地仓库。

再通过命令：
	mvn  archetype:generate -DarchetypeCatalog=local  	
选择你定义的工程archetype，同时通过交互输入需要的属性变量，就会默认生成好你定义的工程。
 
 有Bug请提交到lingang.chen@gmail.com
 
 注意：
  1.在repository包下的 mybatis对应xml 映射需修改  例如:#userId-->#{userId}。主要是archetype 安装后。对于xml下非字符类#{userId}-->#userId 或者修改archetype 插件
  2.report 产生点击模块无法关联到子模块中。需要修改插件代码
  

工具tools下bat介绍
clean.bat 工程清理

eclipse.bat 建立eclipse工程

initdb.bat 初始化数据库

apidoc.bat 接口文档

package.bat 打包

report.bat 项目整体报告 (缺少findbugs 与changelog 报告)

注意:使用jetty:run 则mvn路径在cmmi-server
