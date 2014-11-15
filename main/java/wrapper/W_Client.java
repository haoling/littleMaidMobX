package wrapper;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;

// バージョン差分吸収をおこなう。
// JAVAで#ifdef って使えないの？

public class W_Client
{
	public static void renderSkeletonHead(TileEntitySkullRenderer skullRenderer,
			float p_147530_1_, float p_147530_2_, float p_147530_3_,
			int p_147530_4_, float p_147530_5_, int p_147530_6_,
			String p_147530_7_)
	{
		// 1.7.10
		// TODO ★
		skullRenderer.func_152674_a(p_147530_1_, p_147530_2_, p_147530_3_, p_147530_4_, p_147530_5_, p_147530_6_, null);
		// 1.7.2
//		skullRenderer.func_147530_a(p_147530_1_, p_147530_2_, p_147530_3_, p_147530_4_, p_147530_5_, p_147530_6_, p_147530_7_);
	}
}
