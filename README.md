# mini-spring
A mini implementation of Spring ioc and aop

<h2>项目说明</h2>
mini-spring这个项目是我在学习Spring过程中自己根据Spring的思想而开发的，是一个mini版的Spring。Spring源码很多，真正读懂可能需要花费很长时间。mini-spring实现了spring ioc和aop的主要功能，有助于快速理清Spring的原理。
<h2>支持功能</h2>
<ol>
<li>支持通过xml方式配置。</li>
<li>支持singleton类型bean的创建，属性注入以及依赖注入。</li>
<li>支持接口代理和类代理，支持exposeProxy和proxyTargetClass两个配置参数。</li>
<li>支持aop常用通知类型（beforeMethod，afterReturning，afterThrowing，around）。</li>
<li>支持切面链（advisorChain），支持order配置参数指定切面的顺序。</li>
</ol>
