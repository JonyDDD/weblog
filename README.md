weblog-springboot/
├── weblog-module-common # 公共模块
├── weblog-module-admin # 管理后台模块
├── weblog-web # Web主模块
└── pom.xml # 父级POM

```
## 核心依赖
| 依赖项 | 版本 | 用途 |
|--------|------|------|
| `com.JonyD:weblog-module-common` | 0.0.1-SNAPSHOT | 公共工具类/实体 |
| `com.JonyD:weblog-module-admin` | 0.0.1-SNAPSHOT | 管理后台功能 |
| `org.springframework.boot:spring-boot-starter-web` | 2.6.3 | Web支持 |
| `com.fasterxml.jackson.core:jackson-databind` | 2.13.1 (传递依赖) | JSON处理 |

## 快速启动
### 1. 前置要求
- JDK 8+
- Maven 3.6+
- MySQL 5.7+

### 2. 初始化步骤
​```bash
# 安装公共模块到本地仓库
cd weblog-module-common/
mvn clean install

# 安装管理模块
cd ../weblog-module-admin/
mvn clean install
```

