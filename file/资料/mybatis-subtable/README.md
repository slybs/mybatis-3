## mybatis-subtable

MyBatis分表插件，对MyBatis代码无侵入，不改变对MyBatis的使用习惯。

## 使用示例

1、mybatis-config.xml配置文件添加插件

```xml
<plugins>
	<plugin interceptor="org.jwxa.mybatis.subtable.plugin.SubTablePlugin" />
</plugins>
```

2、需要分表的Mapper/Dao类添加`@SubTable`注解，使用`strategyClass`指定分表策略类

```java
// LongStrategy：自定义分表策略类
@SubTable(strategyClass=LongStrategy.class)
```

3、不需要分表的方法添加`@SubTableIgnore`注解

```java
@SubTableIgnore
List<User> getList();
```

## 分表策略

- 自定义分表策略需要实现`org.jwxa.mybatis.subtable.strategy.Strategy`接口，实现`getFinalTable`获取最终表名方法。
- `StrategyUtil`工具类提供常用分表策略方法。

**使用示例：**使用Long类型id分表，10张分表

```java
// 10张表，用id进行分表
public class LongStrategy implements Strategy {
	@Override
	public String getFinalTable(String baseTableName, Object params) {
		Long flag = -1l;
		if(params instanceof User) {
			flag = ((User)params).getId();
		}else if(params instanceof Long) {
			flag = (Long) params;
		}
		return StrategyUtil.getHashTable(baseTableName, "_", flag, 10);
	}
}
```

# 测试示例说明

1. 执行`src/test/resources/initsql.sql`初始化数据库
1. 运行MyBatis测试修改`mybatis-config.xml`数据库配置信息，测试类：`org.jwxa.mybatis.subtable.test.DeviceDaoTest`
1. 运行Spring-MyBatis测试修改`jdbc.properties`数据库配置信息，测试类：`org.jwxa.mybatis.subtable.test.spring.UserDaoTest`