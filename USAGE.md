# CactusTools 使用指南

## 快速开始

### 1. 安装插件

1. 下载 `CactusTools-1.0-SNAPSHOT.jar` 文件
2. 将文件放入服务器的 `plugins` 文件夹
3. 重启服务器

### 2. 验证安装

服务器启动后，在控制台中应该看到类似以下的信息：

```
[INFO] ===================================
[INFO] CactusTools v1.0 已加载
[INFO] 作者：NSrank & Augment
[INFO] 延迟检查时间: 1 tick(s)
[INFO] 调试日志: 禁用
[INFO] ===================================
```

## 配置调优

### 基础配置

编辑 `plugins/CactusTools/config.yml` 文件：

```yaml
# 推荐的生产环境配置
debug-logging: false
check-delay-ticks: 1
log-collision-detection: false
```

### 调试配置

如果需要排查问题，可以启用调试模式：

```yaml
# 调试模式配置
debug-logging: true
check-delay-ticks: 1
log-collision-detection: true
```

### 性能优化配置

对于高负载服务器：

```yaml
# 高性能配置
debug-logging: false
check-delay-ticks: 2
log-collision-detection: false
```

## 命令使用

### 基本命令

```bash
# 显示插件信息
/cactustools

# 重载配置文件
/cactustools reload
```

### 命令别名

```bash
# 使用别名
/ct
/ctools

# 重载配置
/ct reload
```

## 测试验证

### 1. 建造测试装置

建造一个简单的仙人掌农场来测试插件功能：

```
[沙子] [仙人掌] [石头]
```

### 2. 观察日志

启用 `log-collision-detection: true`，然后观察控制台输出：

```
[INFO] 检测到仙人掌与固体方块碰撞，位置: 100, 64, 200, 碰撞方向: EAST, 碰撞方块: STONE
```

### 3. 验证功能

- 仙人掌应该正常掉落
- 经验熔炉等红石机器应该正常工作
- 服务器性能不应受到明显影响

## 故障排除

### 常见问题

#### Q: 插件加载失败
A: 检查以下项目：
- 服务器版本是否为 Paper 1.20.1
- Java 版本是否为 8 或更高
- 插件文件是否完整

#### Q: 仙人掌仍然不掉落
A: 尝试以下解决方案：
- 增加 `check-delay-ticks` 到 2-3
- 启用调试日志查看详细信息
- 检查是否有其他插件冲突

#### Q: 服务器性能下降
A: 优化配置：
- 关闭 `debug-logging`
- 关闭 `log-collision-detection`
- 适当增加 `check-delay-ticks`

### 调试步骤

1. **启用调试日志**
   ```yaml
   debug-logging: true
   log-collision-detection: true
   ```

2. **重载配置**
   ```bash
   /cactustools reload
   ```

3. **观察日志输出**
   - 检查是否检测到仙人掌生长事件
   - 确认碰撞检测是否正常工作

4. **分析结果**
   - 如果没有检测到生长事件，可能是其他插件干扰
   - 如果检测到但没有碰撞，检查延迟设置

## 性能监控

### 监控指标

- **TPS**: 插件不应显著影响服务器TPS
- **内存使用**: 插件内存占用应该很小
- **日志频率**: 避免过多的日志输出

### 性能建议

1. **生产环境**：关闭所有调试日志
2. **测试环境**：可以启用部分日志用于验证
3. **高负载服务器**：适当增加延迟时间

## 高级配置

### 多世界配置

插件会在所有世界中工作，如果需要限制特定世界，可以考虑：

1. 使用权限插件限制命令使用
2. 联系开发者添加世界过滤功能

### 与其他插件的兼容性

- **WorldEdit**: 兼容，不会干扰建筑操作
- **红石插件**: 兼容，有助于红石机器正常工作
- **保护插件**: 兼容，遵循保护区域规则

## 更新维护

### 更新插件

1. 下载新版本
2. 停止服务器
3. 替换插件文件
4. 启动服务器
5. 检查配置文件是否需要更新

### 备份配置

定期备份 `plugins/CactusTools/config.yml` 文件，以防配置丢失。

## 技术支持

如果遇到问题，请提供以下信息：

1. 服务器版本和插件版本
2. 相关的错误日志
3. 配置文件内容
4. 问题的详细描述

联系方式：
- 开发者：NSrank & Augment
- 项目地址：[GitHub Repository]
