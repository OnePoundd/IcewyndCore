package Main;
//
//import java.util.UUID;
//
//import org.bukkit.Bukkit;
//import org.bukkit.Location;
//import org.bukkit.craftbukkit.v1_11_R1.CraftServer;
//import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
//import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.event.player.PlayerMoveEvent;
//import org.bukkit.event.player.PlayerTeleportEvent;
//import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
//
//import com.mojang.authlib.GameProfile;
//
//import net.minecraft.server.v1_11_R1.DataWatcher;
//import net.minecraft.server.v1_11_R1.EntityPlayer;
//import net.minecraft.server.v1_11_R1.MinecraftServer;
//import net.minecraft.server.v1_11_R1.PacketPlayOutEntityDestroy;
//import net.minecraft.server.v1_11_R1.PacketPlayOutEntityMetadata;
//import net.minecraft.server.v1_11_R1.PacketPlayOutNamedEntitySpawn;
//import net.minecraft.server.v1_11_R1.PacketPlayOutPlayerInfo;
//import net.minecraft.server.v1_11_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
//import net.minecraft.server.v1_11_R1.PlayerConnection;
//import net.minecraft.server.v1_11_R1.PlayerInteractManager;
//import net.minecraft.server.v1_11_R1.WorldServer;
//
public class NPC implements Listener{
//	private EntityPlayer npc;
//	
//	public NPC(String name, Player creator) {
//		MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
//		WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();;
//		npc = (new EntityPlayer(nmsServer, nmsWorld, new GameProfile(UUID.randomUUID(), name), new PlayerInteractManager(nmsWorld)));
//		npc.spawnIn(nmsWorld);
//        npc.playerInteractManager.a((WorldServer) npc.world);
//        npc.playerInteractManager.b(npc.getWorld().getWorldData().getGameType());
//		npc.teleportTo(creator.getLocation(), true);
//		nmsWorld.addEntity(npc);
//		showToAll();
//		System.out.println("[Additions] An NPC has been created by " + creator.getName() + "!");
//	}
//	
//	@EventHandler
//	public void onPlayerMove(PlayerMoveEvent event) {
//		if(event.getPlayer().getWorld().getName().equals(npc.getWorld().getWorldData().getName())){
//			double distance = event.getPlayer().getLocation().distance(new Location(Bukkit.getWorld(npc.getWorld().getWorldData().getName()), npc.getX(), npc.getY(), npc.getZ()));
//			if(distance < 50) {
//				showToPlayer(event.getPlayer());
//			}
//		}
//	}
//	
//
//	public EntityPlayer get() {
//		return npc;
//	}
//	
//	public void delete() {
//		for(Player player : Bukkit.getOnlinePlayers()) {
//			hideFromPlayer(player);
//		}
//		PlayerMoveEvent.getHandlerList().unregister(this);
//		System.out.println("[Additions] The NPC has successfully been deleted.");
//	}
//	
//	public void teleport(Location loc) {
//		npc.teleportTo(loc, false);
//	}
//	
//	private void showToPlayer(Player player) {
//		DataWatcher d = new DataWatcher(null);
//        PacketPlayOutEntityMetadata packet40 = new PacketPlayOutEntityMetadata(npc.getId(), d, true);
//        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet40);
//	}
//	
//	private void hideFromPlayer(Player player) {
//		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
//		connection.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));
//		connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
//	}
//	
//	private void showToAll() {
//		for(Player player : Bukkit.getOnlinePlayers()) {
//			PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
//			connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, npc));
//			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
//		}
//	}
}
