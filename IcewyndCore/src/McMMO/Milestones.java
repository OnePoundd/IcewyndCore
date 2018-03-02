package McMMO;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;

public class Milestones implements Listener{


	@EventHandler
	public void onMcMMOLevel(McMMOPlayerLevelUpEvent event) {
		if(event.getSkillLevel() %100 == 0) {

		}
	}


}
