### 项目添加parent

pom.xml增加以下代码：

```
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-dependencies</artifactId>
			<version>2.0.5.RELEASE</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>


<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
			<version>2.0.5.RELEASE</version>
			<executions>
				<execution>
					<goals>
						<goal>repackage</goal>
					</goals>
				</execution>
			</executions>
		</plugin>
	</plugins>
</build>
```


### 自动执行sql文件

application.properties添加以下内容：

```

spring.datasource.initialization-mode=always|embedded|never

// 指定文件路径
spring.datasource.schema=classpath:schema.sql
spring.datasource.data=classpath:data.sql

// 设置schema账号
spring.datasource.schema-username=root
spring.datasource.schema-password=

// 设置data账号
spring.datasource.data-username=root
spring.datasource.data-password=


```


### 访问actuator/beans页面出现404的原因


```
management.endpoints.web.exposure.include=*
```


### 多数据源配置

> 源码：https://github.com/geektime-geekbang/geektime-spring-family/blob/master/Chapter%202/multi-datasource-demo


```
// 取消自动配置
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})
        
// 自定义多个数据源的自动配置
@Bean
@ConfigurationProperties("foo.datasource")
public DataSourceProperties fooDataSourceProperties() {
    return new DataSourceProperties();
}

@Bean
public DataSource fooDataSource() {
    DataSourceProperties dataSourceProperties = fooDataSourceProperties();
    log.info("foo datasource: {}", dataSourceProperties.getUrl());
    return dataSourceProperties.initializeDataSourceBuilder().build();
}

@Bean
@Resource
public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
    return new DataSourceTransactionManager(fooDataSource);
}

@Bean
@ConfigurationProperties("bar.datasource")
public DataSourceProperties barDataSourceProperties() {
    return new DataSourceProperties();
}

@Bean
public DataSource barDataSource() {
    DataSourceProperties dataSourceProperties = barDataSourceProperties();
    log.info("bar datasource: {}", dataSourceProperties.getUrl());
    return dataSourceProperties.initializeDataSourceBuilder().build();
}

@Bean
@Resource
public PlatformTransactionManager barTxManager(DataSource barDataSource) {
    return new DataSourceTransactionManager(barDataSource);
}


// application.properties的配置
foo.datasource.url=jdbc:h2:mem:foo
foo.datasource.username=sa
foo.datasource.password=

bar.datasource.url=jdbc:h2:mem:bar
bar.datasource.username=sa
bar.datasource.password=
```

### Hikari - DataSourceConfiguration