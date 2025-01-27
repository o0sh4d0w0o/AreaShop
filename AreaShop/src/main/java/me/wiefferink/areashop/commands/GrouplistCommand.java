package me.wiefferink.areashop.commands;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.wiefferink.areashop.MessageBridge;
import me.wiefferink.areashop.managers.IFileManager;
import me.wiefferink.areashop.tools.Utils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class GrouplistCommand extends CommandAreaShop {

	@Inject
	private MessageBridge messageBridge;
	@Inject
	private IFileManager fileManager;
	
	@Override
	public String getCommandStart() {
		return "areashop grouplist";
	}

	@Override
	public String getHelp(CommandSender target) {
		if(target.hasPermission("areashop.grouplist")) {
			return "help-grouplist";
		}
		return null;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!sender.hasPermission("areashop.grouplist")) {
			messageBridge.message(sender, "grouplist-noPermission");
			return;
		}
		List<String> groups = fileManager.getGroupNames();
		if(groups.isEmpty()) {
			messageBridge.message(sender, "grouplist-noGroups");
		} else {
			messageBridge.message(sender, "grouplist-success", Utils.createCommaSeparatedList(groups));
		}
	}

	@Override
	public List<String> getTabCompleteList(int toComplete, String[] start, CommandSender sender) {
		return new ArrayList<>();
	}

}










