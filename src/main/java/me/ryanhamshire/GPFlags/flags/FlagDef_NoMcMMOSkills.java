package me.ryanhamshire.GPFlags.flags;

import com.gmail.nossr50.events.skills.abilities.McMMOPlayerAbilityActivateEvent;
import com.gmail.nossr50.events.skills.secondaryabilities.SubSkillEvent;
import com.gmail.nossr50.events.skills.unarmed.McMMOPlayerDisarmEvent;
import me.ryanhamshire.GPFlags.Flag;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.message.Message;
import me.ryanhamshire.GPFlags.message.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.Arrays;
import java.util.List;

//import com.gmail.nossr50.events.skills.secondaryabilities.SecondaryAbilityEvent;

public class FlagDef_NoMcMMOSkills extends FlagDefinition {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerDisarm(McMMOPlayerDisarmEvent event) {
        this.handleEvent(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerAbility(McMMOPlayerAbilityActivateEvent event) {
        this.handleEvent(event.getPlayer(), event);
    }

    //@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    //public void onPlayerSecondaryAbility(SecondaryAbilityEvent event) {
       // this.handleEvent(event.getPlayer(), event);
    //}

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerSecondaryAbility(SubSkillEvent event) {
        this.handleEvent(event.getPlayer(), event);
    }

    private void handleEvent(Player player, Cancellable event) {
        Flag flag = this.GetFlagInstanceAtLocation(player.getLocation(), player);
        if (flag != null) {
            event.setCancelled(true);
        }
    }

    public FlagDef_NoMcMMOSkills(FlagManager manager, GPFlags plugin) {
        super(manager, plugin);
    }

    @Override
    public String getName() {
        return "NoMcMMOSkills";
    }

    @Override
	public Message getSetMessage() {
        return Messages.NO_MCMMO_SKILLS_ENABLE;
    }

    @Override
    public Message getUnSetMessage() {
        return Messages.NO_MCMMO_SKILLS_DISABLE;
    }

    @Override
    public List<FlagType> getFlagType() {
        return Arrays.asList(FlagType.CLAIM, FlagType.WORLD, FlagType.SERVER);
    }

}
