# 智能无人驾驶农机系统

本项目是一个基于Java Spring Boot框架开发的智能无人驾驶农机系统，集成了物联网、人工智能和自动化技术，支持多种农机设备协同作业。系统采用了多种设计模式，实现了高内聚、低耦合的模块化架构。

## 系统架构

系统采用前后端分离架构：
- 后端：Spring Boot框架，提供RESTful API
- 前端：基于HTML、CSS和JavaScript的Web界面
- 模拟运行：通过控制台输出模拟系统运行

## 核心功能

1. **农机设备管理**：创建和管理不同类型的农机设备
2. **农田环境监测**：监测和发布环境信息，农机对环境变化做出响应
3. **智能路径规划**：不同农机使用不同的路径规划策略
4. **安全事件处理**：通过责任链处理各种安全事件
5. **命令执行与控制**：通过命令模式执行和撤销操作
6. **高精度定位**：通过单例模式实现的定位系统

## 设计模式应用

本项目应用了多种设计模式，以下是主要的设计模式及其应用：

### 1. 观察者模式（Observer Pattern）

**应用场景**：环境信息采集模块作为被观察者，农机设备作为观察者。

**核心类**：
- `EnvironmentSubject`：环境主题接口
- `EnvironmentObserver`：环境观察者接口
- `EnvironmentMonitor`：环境监测器，实现环境主题接口
- `AgriculturalMachine`：农机设备，实现环境观察者接口

**实现方式**：
- 环境监测器收集环境数据，并通知已注册的农机设备
- 农机设备根据接收到的环境更新调整作业策略

### 2. 策略模式（Strategy Pattern）

**应用场景**：实现不同环境下的路径规划算法。

**核心类**：
- `PathPlanningStrategy`：路径规划策略接口
- `DefaultPathPlanningStrategy`：默认路径规划策略
- `PrecisionPathPlanningStrategy`：精确路径规划策略
- `EfficientPathPlanningStrategy`：高效路径规划策略

**实现方式**：
- 不同农机可以根据需要选择不同的路径规划策略
- 策略可以在运行时动态切换

### 3. 责任链模式（Chain of Responsibility Pattern）

**应用场景**：处理安全事件和紧急情况。

**核心类**：
- `SafetyHandler`：安全处理器接口
- `AbstractSafetyHandler`：抽象安全处理器
- `ObstacleHandler`：障碍物处理器
- `EquipmentFailureHandler`：设备故障处理器
- `BoundaryViolationHandler`：边界违规处理器
- `WeatherAlertHandler`：天气警报处理器
- `SafetyHandlerChain`：安全处理链

**实现方式**：
- 将安全事件沿着处理链传递，直到被适当的处理器处理
- 每个处理器只关注自己能处理的事件类型

### 4. 工厂方法模式（Factory Method Pattern）

**应用场景**：创建不同类型的农机设备。

**核心类**：
- `AgriculturalMachineFactory`：农机工厂接口
- `StandardAgriculturalMachineFactory`：标准农机工厂实现

**实现方式**：
- 通过工厂方法创建不同类型的农机设备
- 封装了对象的创建过程，客户端不需要了解具体的创建细节

### 5. 单例模式（Singleton Pattern）

**应用场景**：确保高精度定位与惯性导航系统只有一个实例。

**核心类**：
- `PositioningSystem`：高精度定位系统

**实现方式**：
- 使用双重检查锁定确保线程安全
- 全局只有一个定位系统实例，统一管理所有农机的位置信息

### 6. 命令模式（Command Pattern）

**应用场景**：封装农机操作指令。

**核心类**：
- `MachineCommand`：命令接口
- `MoveCommand`：移动命令
- `PerformTaskCommand`：执行任务命令
- `StopCommand`：停止命令
- `CommandInvoker`：命令调用者

**实现方式**：
- 将操作封装为命令对象
- 支持命令的执行和撤销
- 记录命令历史

### 7. 状态模式（State Pattern）

**应用场景**：管理农机设备的不同状态。

**核心类**：
- `MachineState`：状态接口
- `IdleState`：空闲状态
- `WorkingState`：工作状态
- `MovingState`：移动状态
- `ErrorState`：错误状态

**实现方式**：
- 将农机的状态封装为对象
- 不同状态下农机的行为不同
- 状态可以自动转换

### 8. 装饰器模式（Decorator Pattern）

**应用场景**：为农机设备动态添加功能。

**核心类**：
- `MachineDecorator`：装饰器抽象类
- `PrecisionFarmingDecorator`：精准农业装饰器

**实现方式**：
- 通过装饰器动态为农机添加精准农业功能
- 不改变原有类的结构，符合开闭原则

### 9. 适配器模式（Adapter Pattern）

**应用场景**：集成第三方气象服务。

**核心类**：
- `WeatherService`：气象服务接口
- `ThirdPartyWeatherService`：第三方气象服务
- `WeatherServiceAdapter`：气象服务适配器

**实现方式**：
- 将第三方气象服务适配到系统接口
- 处理数据格式和单位的转换

## 运行说明

1. 确保已安装Java 11或以上版本
2. 使用Maven构建项目：`mvn clean package`
3. 运行应用：`java -jar target/autonomous-farming-system-1.0-SNAPSHOT.jar`
4. 访问Web界面：`http://localhost:8080`

## API接口

系统提供以下RESTful API：

- `GET /api/machines`：获取所有农机设备
- `GET /api/machines/{id}`：获取指定农机设备
- `POST /api/machines/{id}/move`：移动农机设备
- `POST /api/environment/update`：发布环境更新
- `GET /api/system/status`：获取系统状态

## 技术栈

- Java 11
- Spring Boot 2.6.3
- Thymeleaf
- Lombok
- HTML/CSS/JavaScript

## 项目结构

```
src/main/java/com/agritech/autonomousfarming/
├── adapter/                  # 适配器模式实现
├── chainofresponsibility/    # 责任链模式实现
├── command/                  # 命令模式实现
├── controller/               # Web控制器
├── decorator/                # 装饰器模式实现
├── demo/                     # 系统演示
├── exception/                # 异常处理
├── factory/                  # 工厂方法模式实现
├── model/                    # 数据模型
├── observer/                 # 观察者模式实现
├── singleton/                # 单例模式实现
├── state/                    # 状态模式实现
├── strategy/                 # 策略模式实现
└── AutonomousFarmingApplication.java  # 应用入口
```

## 总结

本项目通过应用多种设计模式，实现了一个模块化、可扩展的智能无人驾驶农机系统。系统具有良好的可维护性和灵活性，能够适应不同的农业场景和需求。通过设计模式的应用，我们可以更好地理解和应用面向对象设计的原则，提高代码质量和系统架构。 