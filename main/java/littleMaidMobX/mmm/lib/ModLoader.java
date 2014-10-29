package littleMaidMobX.mmm.lib;

import littleMaidMobX.LMM_EntityLittleMaid;
import littleMaidMobX.LMM_EntityMode_Test;
import littleMaidMobX.LMM_LittleMaidMobX;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.stats.Achievement;
import net.minecraft.world.biome.BiomeGenBase;

public class ModLoader
{
	public static void addAchievementDesc(Achievement ac_Contract, String string, String string2)
	{
	}

	public static void addCommand(LMM_EntityMode_Test lmm_EntityMode_Test) {}

	public static void addSpawn(Class<LMM_EntityLittleMaid> class1,
			int cfg_spawnWeight, int cfg_minGroupSize, int cfg_maxGroupSize,
			EnumCreatureType creature)
	{
	}

	public static void registerPacketChannel(
			LMM_LittleMaidMobX mod_LMM_littleMaidMob, String string)
	{
	}

	public static void addSpawn(Class<LMM_EntityLittleMaid> class1,
			int cfg_spawnWeight, int cfg_minGroupSize, int cfg_maxGroupSize,
			EnumCreatureType creature, BiomeGenBase[] dominateBiomes)
	{
	}

	public static void clientSendPacket(Packet250CustomPayload packet250CustomPayload)
	{
		
	}

	public static void serverSendPacket(NetServerHandler pHandler,
			Packet250CustomPayload packet250CustomPayload)
	{
	}
}
