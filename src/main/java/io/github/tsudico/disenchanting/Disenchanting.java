package io.github.tsudico.disenchanting;

import io.github.tsudico.disenchanting.common.block.DisenchantTableBlock;
import io.github.tsudico.disenchanting.common.container.DisenchantTableContainer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Disenchanting implements ModInitializer {
	private static final Logger LOGGER = LogManager.getLogger();
	// An instance of our new block
	public static final Block DISENCHANT_TABLE = new DisenchantTableBlock(
			FabricBlockSettings.of(Material.STONE).materialColor(MaterialColor.QUARTZ).strength(5.0F, 1200.0F).build()
	);
	// Container for the block
	public static final Identifier DISENCHANT_CONTAINER = new Identifier("disenchanting", "disenchant_container");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// Block registration
		Registry.register(Registry.BLOCK, new Identifier("disenchanting", "disenchant_table"), DISENCHANT_TABLE);

		// Item registration
		Registry.register(Registry.ITEM, new Identifier("disenchanting", "disenchant_table"), new BlockItem(DISENCHANT_TABLE, new Item.Settings().itemGroup(ItemGroup.MISC)));

		// Container registration
		ContainerProviderRegistry.INSTANCE.registerFactory(Disenchanting.DISENCHANT_CONTAINER, (syncId, identifier, player, buf) -> {
			return new DisenchantTableContainer(syncId, player);
		});

		LOGGER.info("Initializing Disenchanting");
	}
}