package wrapper;

import java.util.UUID;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.passive.EntityTameable;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.common.Loader;


// バージョン差分吸収をおこなう。
// JAVAで#ifdef って使えないの？

public class W_Common
{
	private static W_ICommon instance = getInstance();
	
	private static W_ICommon getInstance()
	{
		if(Loader.MC_VERSION.equalsIgnoreCase("1.7.2"))
		{
			return new wrapper.mc172.W_CCommon();
		}
		else if(Loader.MC_VERSION.equalsIgnoreCase("1.7.10"))
		{
			return new wrapper.mc1710.W_CCommon();
		}
		return null;
	}
	
	public static void setOwner(EntityTameable entity, String name)
	{
		instance.setOwner(entity, name);
	}
	public static String getOwnerName(IEntityOwnable entity)
	{
		return instance.getOwnerName(entity);
	}
	
	public static GameProfile newGameProfile(String UUIDid, String name)
	{
		return instance.newGameProfile(UUIDid, name);
	}
	
	public static void notifyAdmins(ICommandSender sender, ICommand cmd, int p_152374_2_, String s, Object ... p_152374_4_)
	{
		instance.notifyAdmins(sender, cmd, p_152374_2_, s, p_152374_4_);
	}
}
