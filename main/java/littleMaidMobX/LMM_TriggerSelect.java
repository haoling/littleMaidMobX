package littleMaidMobX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import littleMaidMobX.mmm.lib.MMM_Helper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * モード切り替え用トリガーアイテムのコンテナ。
 * マルチ対策用。
 * データの読み込みはIFFで行っている。
 */
public class LMM_TriggerSelect {

	public static List<String> selector = new ArrayList<String>();
	public static Map<String, Map<Integer, List<Integer>>> usersTrigger = new HashMap<String, Map<Integer,List<Integer>>>();
	public static Map<Integer, List<Integer>> defaultTrigger = new HashMap<Integer,List<Integer>>();


	public static Map<Integer, List<Integer>> getUserTrigger(String pUsername) {
		if (pUsername == null) {
			return defaultTrigger;
		}
		if (MMM_Helper.isLocalPlay()) {
			// シングル実行時は名称ブランクに。
			pUsername = "";
		}
		// 存在チェック、無かったら追加
		if (!usersTrigger.containsKey(pUsername)) {
			if (pUsername.isEmpty()) {
				// 名称がブランクの時はデフォルトのものへリンク。
				usersTrigger.put(pUsername, defaultTrigger);
			} else {
				Map<Integer, List<Integer>> lmap = new HashMap<Integer, List<Integer>>();
				lmap.putAll(defaultTrigger);
				usersTrigger.put(pUsername, lmap);
			}
		}
		
		return usersTrigger.get(pUsername);
	}

	public static List<Integer> getuserTriggerList(String pUsername, String pSelector) {
		if (!selector.contains(pSelector)) {
			selector.add(pSelector);
		}
		int lindex = selector.indexOf(pSelector);
		Map<Integer, List<Integer>> lmap = getUserTrigger(pUsername);
		List<Integer> llist;
		if (lmap.containsKey(lindex)) {
			llist = lmap.get(lindex);
		} else {
			llist = new ArrayList<Integer>();
			lmap.put(lindex, llist);
		}
		return llist;
	}


	/**
	 * ユーザー毎にトリガーアイテムを設定する。
	 */
	public static void appendTriggerItem(String pUsername, String pSelector, String pIndexstr) {
		// トリガーアイテムの追加
// TODO ★		appendWeaponsIndex(pIndexstr, getuserTriggerList(pUsername, pSelector));
	}

	/**
	 * トリガーアイテムを解析して登録。
	 */
	private static void appendWeaponsIndex(String indexstr, List<Item> indexlist) {
		if (indexstr.isEmpty()) return;
		String[] s = indexstr.split(",");
		for (String t : s) {
			Object o = Item.itemRegistry.getObject(t);
			if(o instanceof Item)
			{
				indexlist.add((Item)o);
			}
		}
	}

	/**
	 * アイテムが指定されたトリガーに登録されているかを判定
	 */
	public static boolean checkWeapon(String pUsername, String pSelector, ItemStack pItemStack) {
		if (!selector.contains(pSelector)) {
			return false;
		}
		if (MMM_Helper.isLocalPlay()) {
			return getuserTriggerList(null, pSelector).contains(pItemStack.getItem());
		}
		if (!usersTrigger.containsKey(pUsername)) {
			return false;
		}
		return getuserTriggerList(pUsername, pSelector).contains(pItemStack.getItem());
	}

}
