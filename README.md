# CactusTools

一款专为 Minecraft 1.20.1 Paper 服务器设计的插件，用于解决服务器卡顿导致的仙人掌生长后方块状态更新异常问题。
> **注意**：本插件由 AI 开发。

## 问题描述

在高负载的 Minecraft 服务器中，由于服务器卡顿可能导致仙人掌生长后周围方块的碰撞判定异常，使得方块状态更新不及时。这会导致依靠仙人掌生长碰撞特性的经验熔炉等红石机器失效。

## 解决方案

CactusTools 通过监听仙人掌生长事件（`BlockGrowEvent`），在仙人掌生长后延迟执行状态检查，确保仙人掌与周围固体方块的碰撞判定正常工作。

## 功能特性

- ✅ **智能检测**: 仅监听仙人掌生长事件，避免不必要的性能开销
- ✅ **延迟处理**: 可配置的延迟检查时间，确保方块状态完全更新
- ✅ **高效算法**: 只检查水平四个方向，符合仙人掌碰撞机制
- ✅ **配置灵活**: 支持配置文件自定义各项参数
- ✅ **调试友好**: 可选的调试日志输出
- ✅ **命令支持**: 支持热重载配置文件

## 安装方法

1. 从 [GitHub](https://github.com/NSrank/CactusTools ) 下载 `CactusTools.jar` 文件
2. 将文件放入服务器的 `plugins` 文件夹
3. 重启服务器或使用 `/reload` 命令
4. 插件将自动生成配置文件

## 配置文件

配置文件位于 `plugins/CactusTools/config.yml`：

```yaml
# CactusTools 配置文件
# 用于解决服务器卡顿导致的仙人掌生长后方块状态更新异常问题

# 是否启用调试日志输出
debug-logging: false

# 延迟检查时间（单位：tick，1秒=20tick）
# 建议值：1-3，过高可能导致检查失效，过低可能影响性能
check-delay-ticks: 1

# 是否在控制台输出碰撞检测信息
log-collision-detection: true

# 插件版本信息（请勿修改）
config-version: 1.0
```

### 配置说明

- `debug-logging`: 启用后会输出详细的调试信息，用于排查问题
- `check-delay-ticks`: 仙人掌生长后延迟检查的时间，建议保持默认值 1
- `log-collision-detection`: 是否记录碰撞检测结果到控制台

## 命令使用

| 命令 | 别名 | 权限 | 描述 |
|------|------|------|------|
| `/cactustools` | `/ct`, `/ctools` | `cactustools.use` | 显示插件信息 |
| `/cactustools reload` | `/ct reload` | `cactustools.reload` | 重载配置文件 |

## 权限节点

- `cactustools.use`: 使用基本命令（默认：管理员）
- `cactustools.reload`: 重载配置文件（默认：管理员）

## 技术原理

1. **事件监听**: 监听 `BlockGrowEvent` 事件，过滤出仙人掌生长事件
2. **延迟检查**: 使用 `BukkitRunnable` 延迟 1-3 tick 执行状态检查
3. **碰撞检测**: 检查仙人掌周围四个水平方向是否有固体方块
4. **自然掉落**: 发现碰撞时调用 `breakNaturally()` 模拟原版行为

## 性能优化

- 仅处理仙人掌生长事件，不影响其他方块
- 使用高效的方向数组，避免检查不必要的方向
- 延迟执行避免在事件处理期间进行重复检查
- 可配置的日志输出，减少不必要的字符串操作

## 兼容性

- **Minecraft 版本**: 1.20.1
- **服务端**: Paper（推荐）、Spigot
- **Java 版本**: Java 8+

## 开发信息

- **作者**: NSrank & Augment
- **版本**: 1.0
- **开源协议**: MIT License

## 故障排除

### 插件不工作？

1. 检查服务器版本是否为 Paper 1.20.1
2. 确认插件已正确加载（查看启动日志）
3. 启用 `debug-logging` 查看详细信息

### 性能问题？

1. 适当调整 `check-delay-ticks` 值
2. 关闭 `log-collision-detection` 减少日志输出
3. 关闭 `debug-logging`

### 仍然有问题？

1. 尝试调整 `check-delay-ticks` 为 2-3
2. 检查是否有其他插件冲突
3. 联系开发者获取支持 [GitHub Issues](https://github.com/NSrank/CactusTools/issues)

## 更新日志

### v1.0
- 初始版本发布
- 基础仙人掌碰撞检测功能
- 配置文件支持
- 命令系统
- 调试日志功能

---

# CactusTools (English)

A Minecraft 1.20.1 Paper server plugin designed to fix cactus growth block state update issues caused by server lag.

> **Note**: This plugin is developed by AI.

## Problem Description

In high-load Minecraft servers, server lag can cause abnormal collision detection around cacti after growth, leading to untimely block state updates. This causes experience furnace machines that rely on cactus growth collision characteristics to malfunction.

## Solution

CactusTools listens to cactus growth events (`BlockGrowEvent`) and performs delayed state checks after cactus growth to ensure proper collision detection between cacti and surrounding solid blocks.

## Features

- ✅ **Smart Detection**: Only monitors cactus growth events, avoiding unnecessary performance overhead
- ✅ **Delayed Processing**: Configurable delay check time to ensure complete block state updates
- ✅ **Efficient Algorithm**: Only checks horizontal four directions, complying with cactus collision mechanics
- ✅ **Flexible Configuration**: Supports configuration file customization of various parameters
- ✅ **Debug Friendly**: Optional debug log output
- ✅ **Command Support**: Supports hot-reloading configuration files

## Installation

1. Download `CactusTools.jar` file from [GitHub](https://github.com/NSrank/CactusTools)
2. Place the file in your server's `plugins` folder
3. Restart the server or use `/reload` command
4. The plugin will automatically generate configuration files

## Configuration

Configuration file located at `plugins/CactusTools/config.yml`:

```yaml
# CactusTools Configuration File
# Used to fix cactus growth block state update issues caused by server lag

# Enable debug logging
debug-logging: false

# Delay check time (in ticks, 1 second = 20 ticks)
# Recommended: 1-3, too high may cause check failure, too low may affect performance
check-delay-ticks: 1

# Whether to output collision detection information to console
log-collision-detection: true

# Plugin version info (do not modify)
config-version: 1.0
```

### Configuration Explanation

- `debug-logging`: When enabled, outputs detailed debug information for troubleshooting
- `check-delay-ticks`: Delay time for checking after cactus growth, recommended to keep default value 1
- `log-collision-detection`: Whether to log collision detection results to console

## Commands

| Command | Aliases | Permission | Description |
|---------|---------|------------|-------------|
| `/cactustools` | `/ct`, `/ctools` | `cactustools.use` | Show plugin information |
| `/cactustools reload` | `/ct reload` | `cactustools.reload` | Reload configuration file |

## Permissions

- `cactustools.use`: Use basic commands (default: administrators)
- `cactustools.reload`: Reload configuration file (default: administrators)

## Technical Principles

1. **Event Listening**: Listen to `BlockGrowEvent` events, filtering cactus growth events
2. **Delayed Check**: Use `BukkitRunnable` to delay 1-3 ticks for state checking
3. **Collision Detection**: Check four horizontal directions around cactus for solid blocks
4. **Natural Drop**: Call `breakNaturally()` when collision detected to simulate vanilla behavior

## Performance Optimization

- Only processes cactus growth events, doesn't affect other blocks
- Uses efficient direction array, avoids checking unnecessary directions
- Delayed execution prevents duplicate checks during event processing
- Configurable log output reduces unnecessary string operations

## Compatibility

- **Minecraft Version**: 1.20.1
- **Server**: Paper (recommended), Spigot
- **Java Version**: Java 8+

## Development Information

- **Authors**: NSrank & Augment
- **Version**: 1.0
- **License**: MIT License

## Troubleshooting

### Plugin not working?

1. Check if server version is Paper 1.20.1
2. Confirm plugin is properly loaded (check startup logs)
3. Enable `debug-logging` to view detailed information

### Performance issues?

1. Adjust `check-delay-ticks` value appropriately
2. Turn off `log-collision-detection` to reduce log output
3. Turn off `debug-logging`

### Still having issues?

1. Try adjusting `check-delay-ticks` to 2-3
2. Check for conflicts with other plugins
3. Contact developers for support [GitHub Issues](https://github.com/NSrank/CactusTools/issues)

## Changelog

### v1.0
- Initial release
- Basic cactus collision detection functionality
- Configuration file support
- Command system
- Debug logging functionality
