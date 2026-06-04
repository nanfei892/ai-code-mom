<div align="center">

# AI Code Mom

**AI 零代码应用生成平台**

用自然语言描述需求，AI 自动生成、预览、迭代、下载并部署 Web 应用。

[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-green?logo=springboot)](https://spring.io/projects/spring-boot)
[![Vue](https://img.shields.io/badge/Vue-3.5-42b883?logo=vuedotjs)](https://vuejs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.8-3178c6?logo=typescript)](https://www.typescriptlang.org/)
[![LangChain4j](https://img.shields.io/badge/LangChain4j-1.1-blue)](https://docs.langchain4j.dev/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479a1?logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Redis](https://img.shields.io/badge/Redis-6.0-dc382d?logo=redis&logoColor=white)](https://redis.io/)

[在线体验](https://code.nanfei.chat/) · [GitHub 仓库](https://github.com/nanfei892/ai-code-mom)

</div>

---

## 项目简介

AI Code Mom 是一个面向 C 端用户的 **AI 零代码应用生成平台**。用户输入一句应用需求，例如「帮我做一个个人博客」或「生成一个任务管理工具」，系统会自动创建应用、选择合适的代码生成模式，并通过流式对话生成可运行的 Web 项目。

平台支持 **HTML 单文件、多文件静态站、Vue 工程** 三种生成模式，生成后可在浏览器中实时预览，并支持一键部署、代码下载、应用精选、对话历史、后台管理等完整产品能力。

这个项目已经完成线上部署并开源，适合作为 **Java 全栈开发、AI 应用开发、工程化落地能力** 的项目展示。

---

## 项目介绍
1.智能代码生成：用户输入需求描述，AI 自动分析并选择合适的生成策略，通过工具调用生成代码文件，采用流式输出让用户实时看到 AI 的执行过程。
![]https://github.com/nanfei892/ai-code-mom/blob/master/images/1%E6%99%BA%E8%83%BD%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90.png


2.可视化编辑：生成的应用将实时展示，可以进入编辑模式，自由选择网页元素并且和 AI 对话来快速修改页面，直到满意为止。
![]https://github.com/nanfei892/ai-code-mom/blob/master/images/2%E5%8F%AF%E8%A7%86%E5%8C%96%E7%BC%96%E8%BE%91.png


3.一键部署分享：可以将生成的应用一键部署到云端并自动截取封面图，获得可访问的地址进行分享，同时支持完整项目源码下载。
![]https://github.com/nanfei892/ai-code-mom/blob/master/images/3%E9%83%A8%E7%BD%B2.png


精选案例
![]https://github.com/nanfei892/ai-code-mom/blob/master/images/4%E6%9F%A5%E7%9C%8B%E7%B2%BE%E9%80%89%E6%A1%88%E4%BE%8B.png
---

## 核心功能

| 功能 | 说明 |
| --- | --- |
| AI 应用生成 | 用户输入自然语言需求，AI 自动生成可运行 Web 应用 |
| 智能模式路由 | 创建应用时自动选择 HTML、多文件或 Vue 工程生成模式 |
| 流式对话生成 | 基于 SSE 实时展示 AI 输出过程，减少等待黑盒感 |
| 实时应用预览 | 生成完成后在页面右侧 iframe 中即时预览运行效果 |
| 多轮迭代修改 | 保存对话历史和上下文，支持继续让 AI 修改已有应用 |
| Vue 工程生成 | 通过 LangChain4j Tools 读写文件，生成完整 Vue 项目并构建 |
| 可视化编辑辅助 | 预览区可选择页面元素，将元素信息带入下一轮修改提示词 |
| 一键部署 | 将生成应用发布到公网访问地址，并自动生成应用封面 |
| 代码下载 | 应用创建者可下载生成代码压缩包 |
| 精选应用广场 | 展示优质应用，支持应用列表分页和详情查看 |
| 用户体系 | 支持注册、登录、会话保持、用户角色和权限校验 |
| 管理后台 | 支持用户、应用、对话历史等后台管理能力 |
| 限流保护 | 基于 Redisson 对 AI 生成接口做用户级限流 |
| 工作流实验 | 集成 LangGraph4j，探索素材收集、提示词增强、质量检查等节点化流程 |

---

## 项目亮点

### 1. 多生成模式自动路由

系统不是固定用一种方式生成代码，而是在创建应用时根据用户初始需求，通过独立的 AI 路由服务选择生成类型：

- `html`：适合单页活动页、展示页、轻量页面
- `multi_file`：适合结构清晰的静态站点，拆分 HTML / CSS / JS
- `vue_project`：适合复杂交互、多页面、组件化应用

这样可以在生成速度、代码结构和应用复杂度之间取得更好的平衡。

### 2. SSE 流式生成体验

后端通过 Reactor `Flux` + `ServerSentEvent` 输出 AI 生成内容，前端通过 `EventSource` 实时接收并展示生成过程。用户不需要等待完整结果返回，就能看到 AI 正在生成什么。

### 3. LangChain4j Agent 工程化实践

Vue 工程模式下，AI 不只是返回一段文本，而是通过工具调用完成文件级操作：

- 读取目录和文件
- 创建、修改、删除文件
- 多轮工具调用搭建工程结构
- 生成完成后自动执行构建

项目中还为不同应用维护独立对话记忆，避免多个应用之间上下文串扰。

### 4. 从生成到部署的完整闭环

平台覆盖了 AI 应用生成的完整链路：

```text
输入需求 -> 创建应用 -> AI 生成代码 -> 实时预览 -> 多轮修改 -> 下载代码 -> 一键部署 -> 自动截图封面
```

这不是一个单纯的 Demo，而是包含用户、权限、存储、限流、部署、后台管理的完整 Web 产品。

---

## 技术栈

### 后端

| 技术 | 用途 |
| --- | --- |
| Java 21 | 后端主要开发语言 |
| Spring Boot 3.5 | Web 服务、配置管理、依赖注入 |
| MyBatis-Flex | ORM、分页查询、条件构造 |
| MySQL | 用户、应用、对话历史等业务数据存储 |
| Redis / Spring Session | 登录态、会话、AI 对话记忆 |
| LangChain4j | AI 服务编排、流式模型、工具调用、Chat Memory |
| LangGraph4j | 工作流式 AI 代码生成实验 |
| Reactor | 流式响应处理 |
| Redisson | 分布式限流 |
| Caffeine | AI 服务实例本地缓存 |
| Knife4j | 后端接口文档 |
| Selenium | 应用部署后自动截图 |
| 腾讯云 COS | 应用封面等对象存储 |

### 前端

| 技术 | 用途 |
| --- | --- |
| Vue 3 | 前端框架 |
| TypeScript | 类型约束 |
| Vite | 前端构建工具 |
| Ant Design Vue | UI 组件库 |
| Pinia | 登录用户状态管理 |
| Vue Router | 页面路由 |
| Axios | HTTP 请求 |
| EventSource | SSE 流式消息接收 |
| Markdown-it / Highlight.js | AI 回复 Markdown 渲染和代码高亮 |

---

## 代码生成模式

| 模式 | 枚举值 | 生成结果 | 适用场景 |
| --- | --- | --- | --- |
| HTML 单文件 | `html` | `index.html` | 简单页面、落地页、展示页 |
| 多文件静态站 | `multi_file` | `index.html`、`style.css`、`script.js` | 结构更清晰的静态应用 |
| Vue 工程 | `vue_project` | 完整 Vue 项目源码和 `dist` 构建产物 | 多页面、组件化、复杂交互应用 |

---


## 作者

**nanfei892**

- GitHub：[@nanfei892](https://github.com/nanfei892)
- 邮箱：nanfei892@gmail.com

如果这个项目对你有帮助，欢迎 Star。
