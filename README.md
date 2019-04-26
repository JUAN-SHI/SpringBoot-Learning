# SpringBoot-Learning
# springboot
## Spring Boot 基础笔记
### 基础入门
- chapter1：[引入web模块，完成一个简单的RESTful API 使用idea中的Spring Initializr来快速构建Spring Boot工程](https://github.com/JUAN-SHI/SpringBoot-Learning/tree/master/chapter1)
### 工程配置
- chapter2：[配置文件详解：自定义属性、随机数、多环境配置等](https://github.com/JUAN-SHI/SpringBoot-Learning/tree/master/chapter2)
1. 自定义属性与加载
```
 在application.properties中直接定义，然后通过@Value("${属性名}")注解来加载对应的配置属性
```
2. 参数间的引用 
- 在application.properties中的各个参数之间也可以直接引用来使用
```
com.springboot.name=程序猿
com.springboot.title=Spring Boot 教程
com.springboot.desc=${com.springboot.name}正在努力写《${com.springboot.title}》
```
3. 使用随机数
在一些情况下，有些参数需要希望它不是一个固定的值，比如密钥、服务端口等。SpringBoot的属性配置文件中可以通过
${random}来产生int值、long 值或者String字符串，来支持属性的随机数。
```
#随机字符串
com.springboot.value=${random.value}
#随机int
com.springboot.number=${random.int}
#随机long
com.springboot.bignumber=${random.long}
#10以内的随机数
com.springboot.test1=${random.int[10]}
#10-20的随机数
com.springboot.test2=${random.int[10,20]}
```
4. 通过命令行设置属性值
```
java -jar xxx.jar --server.port=8888，通过使用–server.port属性来设置xxx.jar应用的端口为8888
在命令行运行时，连续的两个减号--就是对application.properties中的属性值进行赋值的标识。所以，java -jar xxx.jar --server.port=8888命令，
等价于我们在application.properties中添加属性server.port=8888，该设置在样例工程中可见，读者可通过删除该值或使用命令行来设置该值来验证
```
5. 多环境配置
```
对于多环境的配置，各种项目构建工具或是框架的基本思路是一致的，通过配置多份不同环境的配置文件，再通过打包命令指定需要打包的内容之后进行区分打包，Spring Boot也不例外，或者说更加简单。
在Spring Boot中多环境配置文件名需要满足application-{profile}.properties的格式，其中{profile}对应你的环境标识，比如：
application-dev.properties：开发环境
application-test.properties：测试环境
application-prod.properties：生产环境
至于哪个具体的配置文件会被加载，需要在application.properties文件中通过spring.profiles.active属性来设置，其值对应{profile}值。
如：spring.profiles.active=test就会加载application-test.properties配置文件内容
* 测试不同配置的加载
执行java -jar xxx.jar，可以观察到服务端口被设置为1111，也就是默认的开发环境（dev）
执行java -jar xxx.jar --spring.profiles.active=test，可以观察到服务端口被设置为2222，也就是测试环境的配置（test）
执行java -jar xxx.jar --spring.profiles.active=prod，可以观察到服务端口被设置为3333，也就是生产环境的配置（prod）

* 按照上面的实验，可以如下总结多环境的配置思路：
application.properties中配置通用内容，并设置spring.profiles.active=dev，以开发环境为默认配置
application-{profile}.properties中配置各个环境不同的内容
通过命令行方式去激活不同环境的配置
```

### Web开发
- chapter3-1-1：[构建一个较为复杂的RESTful API以及单元测试](https://github.com/JUAN-SHI/SpringBoot-Learning/tree/master/chapter3-1-1)
- RESTful API具体设计如下：

|  请求类型 |    URL    | 功能说明        |
| :-------------: |-------------:| -----:|
|   GET    | /Users    | 查询用户列表     |
|   POST   | /Users    | 创建一个用户     |
|    GET   | /Users/id | 根据id查询一个用户|
|   PUT    | /Users/id | 根据id更新一个用户|
|  DELETE  | /Users/id | 根据id删除一个用户|
```
@Controller：修饰class，用来创建处理http请求的对象
@RestController：Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，
 如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。
@RestController：Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。
@RequestMapping：配置url映射
```
- chapter3-1-2：[统一异常处理](https://github.com/JUAN-SHI/SpringBoot-Learning/tree/master/chapter3-1-2)
1. 创建全局异常处理类：通过使用@ControllerAdvice定义统一异常处理类，而不是在每个Controller中逐个定义，@ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中
2. 返回JSON格式（@ExceptionHandler之后加入@ResponseBody，就能让处理函数return的内容转换为JSON格式）

### 安全管理
- chapter4-1-1：[使用Security进行安全控制](https://github.com/JUAN-SHI/SpringBoot-Learning/tree/master/chapter4-1-1)
```
创建Spring Security的配置类WebSecurityConfig,具体如下：
* 通过@EnableWebSecurity注解开启Spring Security的功能
* 继承 WebSecurityConfigurerAdapter,并重写它的方法来设置一些web安全的细节
* configure(HttpSecurity http)方法
   *通过authrizeRequests()定义哪些URL需要被保护、哪些不需要被保护。例如以上代码指定了/和/home不需要任何任何认证就可以访问，
   其他的路径都必须通过身份认证。
   * 通过formLogin()定义当需要用户登录时候，转到的登录页面。
* configureGlobal(AuthenticationManagerBuilder auth)方法，在内存中创建了
一个用户，该用户的名称为user,密码为password，用户角色为USER。
```
### 数据访问
- chapter5-1-1：[使用JdbcTemplate](https://github.com/JUAN-SHI/SpringBoot-Learning/tree/master/chapter5-1-1)
```
数据源配置
    为了连接数据库需要引入jdbc支持，在pom.xml中引入如下配置：
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>

连接生产数据源
   <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.21</version>
</dependency>

在src/main/resources/application.properties中配置数据源信息
   spring.datasource.driver-class-name=com.mysql.jdbc.Driver
   spring.datasource.url=jdbc:mysql://localhost:3306/test
   spring.datasource.username=root
   spring.datasource.password=111111
```
- chapter5-1-2：[使用Spring-data-jpa简化数据访问层（推荐）](https://github.com/JUAN-SHI/SpringBoot-Learning/tree/master/chapter5-1-2)
```
配置：
    
   <dependency
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>
   
在src/main/resources/application.properties中配置数据源信息
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/test
    spring.datasource.username=root
    spring.datasource.password=111111
    spring.jpa.properties.hibernate.hbm2ddl.auto=update
    
 spring.jpa.properties.hibernate.hbm2ddl.auto是hibernate的配置属性，其主要作用是：自动创建、更新、验证数据库表结构。该参数的几种配置如下：
    create:每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次都没有任何改变也要这样执行，
           这就是导致数据库表数据丢失的一个重要原因。
    create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory-关闭，表就自动删除。
    update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库),以后加载hibernate时根据model类自动更新表
            结构，即使表结构改变了但表中的行仍然存在不会删除以前的行，要注意的是当部署到服务器后，表结构域是不会被马上被建立起来的，是要等应用第
            一次运行起来后才会。
    validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。
    update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库),以后加载hibernate时根据model类自动更新表 
```
- chapter5-2-1：[多数据源配置（一）：JdbcTemplate](https://github.com/JUAN-SHI/SpringBoot-Learning/tree/master/chapter5-2-1)
```
 多数据源配置：
    创建一个Spring配置类，定义两个DataSource用来读取application.properties中的不同配置。如下例子中，主数据源配置为spring.datasource.primary开头的配置，第二数据源配置为spring.datasource.secondary开头的配置
对应的application.properties配置如下：
spring.datasource.primary.url=jdbc:mysql://localhost:3306/test
spring.datasource.primary.username=root
spring.datasource.primary.password=111111
spring.datasource.primary.driver-class-name=com.mysql.jdbc.Driver

spring.datasource.secondary.url=jdbc:mysql://localhost:3306/test1 
spring.datasource.secondary.username=root
spring.datasource.secondary.password=111111
spring.datasource.secondary.driver-class-name=com.mysql.jdbc.Driver
```
- chapter5-2-2：[多数据源配置（二）：Spring-data-jpa](https://github.com/JUAN-SHI/SpringBoot-Learning/tree/master/chapter5-2-2)
```
新增对第一数据源的JPA配置，注意两处注释的地方，用于指定数据源对应的Entity实体和Repository定义位置，用@Primary区分主数据源
```
- chapter5-3-1：[使用NoSQL数据库（一）：Redis](https://github.com/JUAN-SHI/SpringBoot-Learning/tree/master/chapter5-3-1)
```
Spring Boot提供的数据访问框架Spring Data Redis基于Jedis.详细操作查看Redis官方文档
```
- chapter5-4-1：[整合MyBatis](https://github.com/JUAN-SHI/SpringBoot-Learning/tree/master/chapter5-4-1) ·
```
整合Mybaties在pom.xml中引入依赖：
 <dependency>
     <groupId>org.mybatis.spring.boot</groupId>
     <artifactId>mybatis-spring-boot-starter</artifactId>
     <version>1.1.1</version>
	</dependency>
 引入连接MySQL的必要依赖mysql-connector-java
 引入整合mybaties的核心依赖mybatis-spring-boot-starter
 这里不引入spring-boot-starter-jdbc依赖，是由于mybatis-spring-boot-starter中已经包含了此依赖
```
- chapter5-4-2：[MyBatis注解配置详解](https://github.com/JUAN-SHI/SpringBoot-Learning/tree/master/chapter5-4-2)
```
* 传参方式：
    使用@Param
    	@Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
	int insert(@Param("name") String name, @Param("age") Integer age);
   @Param中定义的name对应了SQL中的#{name},age对应了SQL中的#{age} 
用注解来简化XML配置的时候，@Param注解的作用是给参数命名，参数命名后就能根据名字得到参数值，正确的将参数传入SQL语句中
使用@Param注解来声明参数，如果使用#{}或${}的方法都可以。当不适用@Param来注解声明参数时，必须使用#{}方式。如果使用${}
的方式，会报错。不适用它注解的时候，参数只能有一个，如果有多个参数(int,String)的话，必须用map取值，用索引取值，麻烦且
不清晰。注意：使用了@Param注解的话在mapper.xml不加paramterType,因为在接口就声明了类型，加上@Param后自动识别
在SQL语句里可以引用JavaBean的属性，而且只能引用JavaBean的属性。【这里age和name是user的属性】
@Insert("insert into user(name,age) values(#{name},#{age})")
int insertByUser(User user);

*使用Map(使用Map对象来作为传递参数的容器)
@Insert("insert into user(name,age) values(#{name,jdbcType=VARCHAR},#{age,jdbcType=INTEGER})")
int  insertByMap(Map<String,Object> map);
对于insert语句中需要的参数，我们只需要在map中填入同名的内容即可
Map<String, Object> map = new HashMap<>();
map.put("name", "CCC");
map.put("age", 40);
userMapper.insertByMap(map);

*使用对象
除了map对象，我们也可直接使用普通的Java对象来作为查询条件的传参，比如可以直接使用User对象
@Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
int insertByUser(User user);

*返回结果的绑定
对于查询的结果需要返回一个与数据库实体不同的包装类，可以通过@Results和@Result注解来进行绑定。
@Results({
    @Result(property = "name", column = "name"),
    @Result(property = "age", column = "age")
})
@Select("SELECT name, age FROM user")
List<User> findAll();
```
