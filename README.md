# SpringBookManager 📚

> 一个基于 **SSM（Spring MVC + Spring + MyBatis）** 的图书管理系统。  
> UI 十分甚至九分二次元（登录页、头像与素材风格），但功能定位依然是标准的「登录 / 角色分流 / 借阅管理」。

## 项目简介

`SpringBookManager` 是一个传统 Java Web（WAR）项目，使用 Spring Security 做认证与权限控制，支持管理员与普通用户两种角色：

- 管理员：查看借阅统计、管理图书（新增 / 删除）
- 普通用户：浏览可借图书、借阅与归还图书

前端模板使用 Thymeleaf，静态资源位于 `WEB-INF/static`，页面位于 `WEB-INF/template`。

---

## 功能概览 ✨

| 模块 | 说明 | 主要路由 |
| --- | --- | --- |
| 认证与会话 | 表单登录、注册、记住我（7 天） | `/page/auth/login` `/page/auth/register` |
| 管理员端 | 借阅总览、图书列表、新增图书、删除可借阅图书 | `/page/admin/index` `/page/admin/book` `/page/admin/add-book` |
| 用户端 | 浏览可借图书、查看我的借阅、借阅 / 归还 | `/page/user/index` `/page/user/book` |
| 异常页 | 全局异常捕获后跳转 500 页面 | `/error` |

---

## 技术栈

- **后端**：Spring MVC 5.3.14、Spring Security 5.5.3、Spring JDBC
- **持久层**：MyBatis 3.5.7 + MyBatis-Spring 2.0.6
- **数据库连接池**：HikariCP 3.4.5
- **模板引擎**：Thymeleaf 3.1（含 Spring Security 方言）
- **数据库**：MySQL 8 驱动（`mysql-connector-java 8.0.28`）
- **构建**：Maven（`war` 打包，Java 8 编译目标）

---

## 快速开始 🚀

### 1) 环境准备

- JDK **8**（项目 workflow 也使用 Java 8）
- Maven 3.6+
- MySQL 8.x
- Servlet 容器（如 Tomcat 9）用于部署 WAR

### 2) 获取项目并构建

```bash
git clone https://github.com/wgd20020109/SpringBookManager.git
cd SpringBookManager
bash mvnw clean package
```

构建产物默认位于：

```text
target/SpringBookManager-1.0-SNAPSHOT.war
```

### 3) 部署与访问

将 WAR 部署到 Tomcat 后，访问登录页：

```text
http://localhost:8080/SpringBookManager-1.0-SNAPSHOT/page/auth/login
```

> 如果你在容器中把上下文路径改成了 `/` 或其它值，请按实际路径访问。

---

## 配置说明 ⚙️

### 数据库连接（公开仓库推荐）

数据库连接写在：

- `/src/main/java/book/manager/config/RootConfiguration.java`

项目不再存储任何真实数据库凭据，数据库配置改为外部注入：

- `BOOK_DB_URL`（可选，默认：`jdbc:mysql://127.0.0.1:3306/bookmanage`）
- `BOOK_DB_DRIVER`（可选，默认：`com.mysql.cj.jdbc.Driver`）
- `BOOK_DB_USERNAME`（可选，默认：`bookmanager`）
- `BOOK_DB_PASSWORD`（必填，无默认值）

也支持等价的 JVM 参数：`-Dbook.db.url / -Dbook.db.driver / -Dbook.db.username / -Dbook.db.password`。

### 安全与权限

- 安全配置：`/src/main/java/book/manager/config/SecurityConfiguration.java`
- 放行：`/static/**`、`/page/auth/**`、`/api/auth/**`
- 角色访问：
  - `ROLE_admin` → `/page/admin/**`
  - `ROLE_user` → `/page/user/**`
- remember-me 有效期：`7 天`

> 使用 JDBC remember-me 时，需要数据库中存在 Spring Security 持久化 token 对应表（通常为 `persistent_logins`）。

---

## 项目结构

```text
SpringBookManager/
├─ pom.xml
├─ src/
│  ├─ main/
│  │  ├─ java/book/manager/
│  │  │  ├─ config/           # Spring / MVC / Security 配置
│  │  │  ├─ controller/       # 页面与 API 控制器
│  │  │  ├─ service/          # 业务层
│  │  │  ├─ mapper/           # MyBatis Mapper（注解 SQL）
│  │  │  ├─ entity/           # 实体模型
│  │  │  └─ initializer/      # Servlet / Security 初始化
│  │  └─ webapp/WEB-INF/
│  │     ├─ template/         # Thymeleaf 页面模板
│  │     └─ static/           # CSS / JS / 图片等静态资源
│  └─ test/
└─ .github/workflows/         # Maven 构建与 Azure 部署工作流
```

---

## 使用提示 💡

1. 这是经典 SSM + WAR 架构，推荐先跑通「注册 → 登录 → 借阅 / 归还 → 管理员查看借阅列表」主链路。  
2. 项目里有较多前端素材与动效文件，改样式时建议优先在 `WEB-INF/template` + `WEB-INF/static` 成对排查。  
3. Mapper 目前是注解 SQL 直写模式，后续若要扩展复杂查询，可再考虑拆分 XML。  

---

## 当前状态与说明

- 项目可用于学习 SSM、Spring Security 角色鉴权与 Thymeleaf 页面渲染流程。
- 仓库当前实现存在“配置硬编码”特征（尤其数据库连接），请勿直接用于生产环境。
- 在 **JDK 17** 环境下直接构建可能出现 `javax.annotation` 相关编译错误，建议按项目设定使用 **JDK 8**。

---

## 后续可扩展方向（建议）

- [ ] 将数据库连接迁移到外部配置（环境变量 / 配置中心）
- [ ] 增加数据库初始化脚本（表结构 + 测试数据）
- [ ] 完善测试（Service 层与 Controller 层）
- [ ] 增加图书搜索、分页与借阅历史筛选
- [ ] 统一异常处理返回格式（页面 + API 双通道）
