package ae.teletronics;

import java.util.Collection;
import java.util.Map;

/**
 * Created by kristian on 3/28/16.
 */
public class InputCommand {

    public InputCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    public InputCommand(CommandType commandType, Map<String, String> inputParameters) {
        this.commandType = commandType;
        this.inputParameters = inputParameters;
    }

    public CommandType commandType;
    public Map<String, String> inputParameters;
}
