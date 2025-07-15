package org.plugin.cactustools;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public final class CactusTools extends JavaPlugin implements Listener {

    private boolean debugLogging;
    private int checkDelayTicks;
    private boolean logCollisionDetection;

    @Override
    public void onEnable() {
        // 保存默认配置文件
        saveDefaultConfig();

        // 加载配置
        loadConfiguration();

        // 注册事件监听器
        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("===================================");
        getLogger().info("CactusTools v1.0 已加载");
        getLogger().info("作者：NSrank & Augment");
        getLogger().info("延迟检查时间: " + checkDelayTicks + " tick(s)");
        getLogger().info("调试日志: " + (debugLogging ? "启用" : "禁用"));
        getLogger().info("===================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("CactusTools 已卸载");
    }

    /**
     * 加载配置文件
     */
    private void loadConfiguration() {
        reloadConfig();
        debugLogging = getConfig().getBoolean("debug-logging", false);
        checkDelayTicks = getConfig().getInt("check-delay-ticks", 1);
        logCollisionDetection = getConfig().getBoolean("log-collision-detection", true);

        // 验证配置值
        if (checkDelayTicks < 1) {
            getLogger().warning("check-delay-ticks 值无效，已重置为默认值 1");
            checkDelayTicks = 1;
        }

        if (debugLogging) {
            getLogger().info("配置已加载 - 延迟: " + checkDelayTicks + "tick, 碰撞日志: " + logCollisionDetection);
        }
    }

    @EventHandler
    public void onCactusGrow(BlockGrowEvent event) {
        // 只处理仙人掌生长事件
        if (event.getNewState().getType() != Material.CACTUS) {
            return;
        }

        // 获取新生长的仙人掌方块
        Block newCactusBlock = event.getNewState().getBlock();

        if (debugLogging) {
            getLogger().info("检测到仙人掌生长事件，位置: " +
                newCactusBlock.getX() + ", " + newCactusBlock.getY() + ", " + newCactusBlock.getZ());
        }

        // 延迟指定tick数执行状态更新，确保方块状态已完全更新
        new BukkitRunnable() {
            @Override
            public void run() {
                updateCactusState(newCactusBlock);
            }
        }.runTaskLater(this, checkDelayTicks);
    }

    /**
     * 更新仙人掌状态，检查周围方块并处理碰撞
     * @param cactusBlock 需要检查的仙人掌方块
     */
    private void updateCactusState(Block cactusBlock) {
        // 确保方块仍然是仙人掌
        if (cactusBlock.getType() != Material.CACTUS) {
            if (debugLogging) {
                getLogger().info("方块已不再是仙人掌，跳过检查");
            }
            return;
        }

        if (debugLogging) {
            getLogger().info("开始检查仙人掌状态，位置: " +
                cactusBlock.getX() + ", " + cactusBlock.getY() + ", " + cactusBlock.getZ());
        }

        // 检查仙人掌周围的四个水平方向（不包括上下）
        BlockFace[] horizontalFaces = {
            BlockFace.NORTH, BlockFace.SOUTH,
            BlockFace.EAST, BlockFace.WEST
        };

        for (BlockFace face : horizontalFaces) {
            Block adjacentBlock = cactusBlock.getRelative(face);

            // 如果相邻方块是固体方块，仙人掌应该掉落
            if (adjacentBlock.getType().isSolid()) {
                if (logCollisionDetection) {
                    getLogger().info("检测到仙人掌与固体方块碰撞，位置: " +
                        cactusBlock.getX() + ", " + cactusBlock.getY() + ", " + cactusBlock.getZ() +
                        ", 碰撞方向: " + face + ", 碰撞方块: " + adjacentBlock.getType());
                }

                // 自然掉落仙人掌，模拟原版行为
                cactusBlock.breakNaturally();
                return; // 找到一个碰撞就足够了
            }
        }

        if (debugLogging) {
            getLogger().info("仙人掌周围无碰撞，状态正常");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("cactustools")) {
            if (args.length == 0) {
                sender.sendMessage("§6CactusTools v1.0");
                sender.sendMessage("§7用法: /cactustools reload - 重载配置文件");
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("cactustools.reload")) {
                    sender.sendMessage("§c你没有权限执行此命令！");
                    return true;
                }

                loadConfiguration();
                sender.sendMessage("§a配置文件已重载！");
                getLogger().info("配置文件已被 " + sender.getName() + " 重载");
                return true;
            }
        }

        return false;
    }
}
