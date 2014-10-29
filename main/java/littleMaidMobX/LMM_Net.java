package littleMaidMobX;

import static littleMaidMobX.LMM_Statics.*;
import littleMaidMobX.mmm.lib.MMM_Helper;
import littleMaidMobX.mmm.lib.ModLoader;
import littleMaidMobX.mmm.lib.NetServerHandler;
import littleMaidMobX.mmm.lib.Packet250CustomPayload;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LMM_Net {
	
	/**
	 * 渡されたデータの先頭に自分のEntityIDを付与して全てのクライアントへ送信
	 */
	public static void sendToAllEClient(LMM_EntityLittleMaid pEntity, byte[] pData) {
		MMM_Helper.setInt(pData, 1, pEntity.getEntityId());
		
		// TODO ★音声にしか使用していないので後回し
//		((WorldServer)pEntity.worldObj).getEntityTracker().func_151248_b(pEntity, new Packet250CustomPayload("LMM|Upd", pData));
	}

	/**
	 * 渡されたデータの先頭に自分のEntityIDを付与して特定ののクライアントへ送信
	 */
	public static void sendToEClient(NetServerHandler pHandler, LMM_EntityLittleMaid pEntity, byte[] pData) {
		MMM_Helper.setInt(pData, 1, pEntity.getEntityId());
		ModLoader.serverSendPacket(pHandler, new Packet250CustomPayload("LMM|Upd", pData));
	}

	public static void sendToClient(NetServerHandler pHandler, byte[] pData) {
		ModLoader.serverSendPacket(pHandler, new Packet250CustomPayload("LMM|Upd", pData));
	}

	/**
	 * 渡されたデータの先頭にEntityIDを付与してサーバーへ送信。
	 * 0:Mode, 1-4:EntityID, 5-:Data
	 */
	public static void sendToEServer(LMM_EntityLittleMaid pEntity, byte[] pData) {
		MMM_Helper.setInt(pData, 1, pEntity.getEntityId());
		ModLoader.clientSendPacket(new Packet250CustomPayload("LMM|Upd", pData));
		LMM_LittleMaidMobX.Debug(String.format("LMM|Upd:send:%2x:%d", pData[0], pEntity.getEntityId()));
	}

	public static void sendToServer(byte[] pData) {
		ModLoader.clientSendPacket(new Packet250CustomPayload("LMM|Upd", pData));
		LMM_LittleMaidMobX.Debug(String.format("LMM|Upd:%2x:NOEntity", pData[0]));
	}

	/**
	 * サーバーへIFFのセーブをリクエスト
	 */
	public static void saveIFF() {
		sendToServer(new byte[] {LMN_Server_SaveIFF});
	}

	/**
	 * littleMaidのEntityを返す。
	 */
	public static LMM_EntityLittleMaid getLittleMaid(byte[] pData, int pIndex, World pWorld) {
		Entity lentity = MMM_Helper.getEntity(pData, pIndex, pWorld);
		if (lentity instanceof LMM_EntityLittleMaid) {
			return (LMM_EntityLittleMaid)lentity;
		} else {
			return null;
		}
	}

	// 受信パケットの処理
	
	public static void serverCustomPayload(NetServerHandler pNetHandler, Packet250CustomPayload pPayload) {
		// サーバ側の動作
		byte lmode = pPayload.data[0];
		int leid = 0;
		LMM_EntityLittleMaid lemaid = null;
		if ((lmode & 0x80) != 0) {
			leid = MMM_Helper.getInt(pPayload.data, 1);
			lemaid = getLittleMaid(pPayload.data, 1, pNetHandler.playerEntity.worldObj);
			if (lemaid == null) return;
		}
		LMM_LittleMaidMobX.Debug(String.format("LMM|Upd Srv Call[%2x:%d].", lmode, leid));
		byte[] ldata;
		int lindex;
		int lval;
		String lname;
		
		switch (lmode) {
		case LMN_Server_UpdateSlots : 
			// 初回更新とか
			// インベントリの更新
			lemaid.maidInventory.clearChanged();
			for (LMM_SwingStatus lswing : lemaid.mstatSwingStatus) {
				lswing.lastIndex = -1;
			}
			break;
			
		case LMN_Server_DecDyePowder:
			// カラー番号をクライアントから受け取る
			// インベントリから染料を減らす。
			int lcolor2 = pPayload.data[1];
			if (!pNetHandler.playerEntity.capabilities.isCreativeMode) {
				for (int li = 0; li < pNetHandler.playerEntity.inventory.mainInventory.length; li++) {
					ItemStack lis = pNetHandler.playerEntity.inventory.mainInventory[li];
					if (lis != null && lis.getItem() == Items.dye) {
						if (lis.getItemDamage() == (15 - lcolor2)) {
							MMM_Helper.decPlayerInventory(pNetHandler.playerEntity, li, 1);
						}
					}
				}
			}
			break;
			
		case LMN_Server_SetIFFValue:
			// IFFの設定値を受信
			lval = pPayload.data[1];
			lindex = MMM_Helper.getInt(pPayload.data, 2);
			lname = MMM_Helper.getStr(pPayload.data, 6);
			LMM_LittleMaidMobX.Debug("setIFF-SV user:%s %s(%d)=%d", MMM_Helper.getPlayerName(pNetHandler.playerEntity), lname, lindex, lval);
			LMM_IFF.setIFFValue(MMM_Helper.getPlayerName(pNetHandler.playerEntity), lname, lval);
			sendIFFValue(pNetHandler, lval, lindex);
			break;
		case LMN_Server_GetIFFValue:
			// IFFGUI open
			lindex = MMM_Helper.getInt(pPayload.data, 1);
			lname = MMM_Helper.getStr(pPayload.data, 5);
			lval = LMM_IFF.getIFF(MMM_Helper.getPlayerName(pNetHandler.playerEntity), lname);
			LMM_LittleMaidMobX.Debug("getIFF-SV user:%s %s(%d)=%d", MMM_Helper.getPlayerName(pNetHandler.playerEntity), lname, lindex, lval);
			sendIFFValue(pNetHandler, lval, lindex);
			break;
		case LMN_Server_SaveIFF:
			// IFFファイルの保存
			LMM_IFF.saveIFF(MMM_Helper.getPlayerName(pNetHandler.playerEntity));
			if (!MMM_Helper.isClient) {
				LMM_IFF.saveIFF("");
			}
			break;
			
		}
	}

	/**
	 * クライアントへIFFの設定値を通知する。
	 * @param pNetHandler
	 * @param pValue
	 * @param pIndex
	 */
	protected static void sendIFFValue(NetServerHandler pNetHandler, int pValue, int pIndex) {
		byte ldata[] = new byte[] {
				LMN_Client_SetIFFValue,
				0,
				0, 0, 0, 0
		};
		ldata[1] = (byte)pValue;
		MMM_Helper.setInt(ldata, 2, pIndex);
		sendToClient(pNetHandler, ldata);
	}

}
