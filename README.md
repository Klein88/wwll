# 伟伟拉链项目（WWLL）

## 项目概述
伟伟拉链项目是一个由微信小程序前端（`wwllfront`）和 Spring Boot 后端（`wwllbackend`）组成的应用程序，主要用于拉链相关业务的管理和展示，如订单管理、报价展示、材料管理等。

## 项目结构

### 微信小程序前端（wwllfront）
微信小程序前端部分负责用户界面的展示和与用户的交互，使用微信小程序开发框架进行开发。

- **主要页面**：
  - **首页**：展示最近订单等信息。
  - **报价页**：用于查看相关报价信息。
  - **订单页**：管理订单相关操作。
  - **材料页**：管理材料相关信息。

- **主要文件和目录说明**：
  - `app.js`：小程序的入口文件，包含小程序的生命周期函数，如 `onLaunch` 用于初始化操作。
  - `app.json`：小程序的全局配置文件，配置了小程序的页面路径、窗口样式、底部导航栏等信息。
  - `pages` 目录：包含各个页面的代码文件，每个页面通常由 `.js`、`.json`、`.wxml` 和 `.wxss` 四个文件组成。
  - `project.config.json`：项目配置文件，配置了编译类型、库版本、打包选项等信息。

### Spring Boot 后端（wwllbackend）
Spring Boot 后端部分负责处理业务逻辑和与数据库的交互，提供 API 接口供前端调用。

- **主要配置文件**：
  - `application.properties`：Spring Boot 的配置文件，配置了数据库连接信息、MyBatis Plus 相关配置、服务端口等信息。
  - `mvnw.cmd`：Maven 包装器脚本，用于在 Windows 系统上执行 Maven 命令。

- **主要代码文件和目录说明**：
  - `WwllbackendApplication.java`：Spring Boot 应用的入口类，包含 `main` 方法，用于启动 Spring Boot 应用。
  - `src/main/java` 目录：包含主要的 Java 代码，如控制器、服务层、实体类等。
  - `src/test/java` 目录：包含单元测试代码。

## 项目运行

### 后端运行步骤
1. 确保已经安装了 Java JDK（版本 8 及以上）和 Maven。
2. 打开命令行工具，进入 `wwllbackend` 目录。
3. 配置 `application.properties` 文件中的数据库连接信息，确保数据库服务已启动。
4. 执行以下命令启动后端服务：
   - 使用 Maven 包装器（推荐）：
     ```sh
     ./mvnw spring-boot:run
