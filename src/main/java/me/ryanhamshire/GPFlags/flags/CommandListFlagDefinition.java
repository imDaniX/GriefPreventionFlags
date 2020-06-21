package me.ryanhamshire.GPFlags.flags;

import me.ryanhamshire.GPFlags.CommandList;
import me.ryanhamshire.GPFlags.FlagManager;
import me.ryanhamshire.GPFlags.GPFlags;
import me.ryanhamshire.GPFlags.SetFlagResult;
import me.ryanhamshire.GPFlags.message.Messages;

import java.util.concurrent.ConcurrentHashMap;

public abstract class CommandListFlagDefinition extends FlagDefinition
{
    public CommandListFlagDefinition(FlagManager manager, GPFlags plugin)
    {
        super(manager, plugin);
    }
    
    private static ConcurrentHashMap<String, CommandList> commandListMap = new ConcurrentHashMap<String, CommandList>();
    
    protected boolean commandInList(String flagParameters, String commandLine)
    {
        String params = flagParameters;
        CommandList list = commandListMap.get(params);
        if(list == null)
        {
            list = new CommandList(params);
            commandListMap.put(params, list);
        }
        
        String command = commandLine.split(" ")[0];
        return list.Contains(command);
    }
    
    @Override
    public SetFlagResult ValidateParameters(String parameters)
    {
        if(parameters.isEmpty())
        {
            return new SetFlagResult(false, Messages.COMMAND_LIST_REQUIRED);
        }

        return new SetFlagResult(true, this.getSetMessage());
    }
}
