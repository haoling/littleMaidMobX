package littleMaidMobX.mmm.lib;

import java.util.Map;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.inventory.GuiContainer;

// ダミー
public class BaseMod
{

	public String getName() {
		return null;
	}

	public void addRenderer(Map map) {
	}

	public String getVersion() {
		return null;
	}

	public String getPriorities() {
		return null;
	}

	public GuiContainer getContainerGUI(EntityClientPlayerMP var1, int var2, int var3, int var4, int var5) {
		return null;
	}

	public void serverCustomPayload(NetServerHandler var1, Packet250CustomPayload var2) {
	}

	public void clientCustomPayload(NetClientHandler var1, Packet250CustomPayload var2) {
	}
}
