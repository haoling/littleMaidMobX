package wrapper;

import java.util.UUID;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.passive.EntityTameable;

import com.mojang.authlib.GameProfile;


// バージョン差分吸収をおこなう。
// JAVAで#ifdef って使えないの？

public class W_Common
{
	public static void setOwner(EntityTameable entity, String name)
	{
		// 1.7.10
		entity.func_152115_b(name);
		// 1.7.2
//		entity.setOwner(name);
	}
	
	public static String getOwnerName(IEntityOwnable entity)
	{
		// 1.7.10
		return entity.func_152113_b();
		// 1.7.2
//		return entity.getOwnerName();
	}
	
	public static GameProfile newGameProfile(String UUIDid, String name)
	{
		// 1.7.10
		// TODO ★
		return new GameProfile(UUID.randomUUID(), name);
		// 1.7.2
//		return new GameProfile(UUIDid, name);
	}
	
	
	public static void notifyAdmins(ICommandSender p_152374_0_, ICommand p_152374_1_, int p_152374_2_, String p_152374_3_, Object ... p_152374_4_)
	{
		// 1.7.10
		CommandBase.func_152374_a(p_152374_0_, p_152374_1_, p_152374_2_, p_152374_3_, p_152374_4_);
		// 1.7.2
//		CommandBase.notifyAdmins(p_152374_0_, p_152374_2_, p_152374_3_, p_152374_4_);
	}
}
