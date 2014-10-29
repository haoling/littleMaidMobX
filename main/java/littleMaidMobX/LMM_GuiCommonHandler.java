package littleMaidMobX;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class LMM_GuiCommonHandler implements IGuiHandler
{
	// TODO 暫定、クリック対象を覚えておく
	// メイドさんを右クリックした時に呼ばれる interact はクライアントのほうが早いため、
	// 1:クライアント：右クリック対象のメイドを覚える
	// 2:サーバ：　　GUIを開く
	// 3:クライアント：サーバからの通知で覚えておいたメイドのインベントリGUIを開く
	//
	// 課題：1～3までの間にクライアントは別のメイドを右クリックする可能性がある。
	public static LMM_EntityLittleMaid maidClient = null;
	public static LMM_EntityLittleMaid maidServer = null;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		Object o = null;
		if(maidServer!=null)
		{
			o = new LMM_ContainerInventory(player.inventory, maidServer);
			maidServer = null;
		}
		return o;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		Object o = null;
		if(maidClient!=null)
		{
			o = new LMM_GuiInventory(player, maidClient);
			maidClient = null;
		}
		return o;
	}
}
