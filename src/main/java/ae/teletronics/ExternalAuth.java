package ae.teletronics;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kristian on 3/28/16.
 */
public abstract class ExternalAuth implements UserManagement{

    public void readInput() throws IOException {
        this.readInput(new BufferedReader(new InputStreamReader(System.in)));
    }

    public void setup(){}

    public void readInput(BufferedReader bufferedReader) throws IOException {
        commandInputLoop: while(true){
            byte[] lB = new byte[2];
            int startPos = 0;
            while (startPos < lB.length) {
                int ret = System.in.read(lB, startPos, (lB.length - startPos));
                if (ret < 0) {
                    break commandInputLoop;
                }
                startPos += ret;
            }
            int streamLen = System.in.available();
            byte[] rd = new byte[streamLen];
            startPos = 0;
            while (startPos < streamLen) {
                int ret = System.in.read(rd, startPos,(streamLen - startPos));
                if (ret < 0) {
                    break commandInputLoop;
                }
                startPos += ret;
            }

            String inputCommand = new String(rd, "ASCII");
            this.gotCommand(inputCommand);
        }
    }

    void gotCommand(String inputCommand) throws IOException {
        final InputCommand command = this.parseInput(inputCommand);
        if (command.commandType != CommandType.UNKNOWN){
            boolean status = this.runCommand(command);
            this.writeOutput(status);
        }
    }

    void writeOutput(boolean commandStatus) throws IOException {
        byte[] res = new byte[4];
        res[0] = 0;
        res[1] = 2;
        res[2] = 0;

        if (commandStatus) {
            res[3] = 1;
        } else {
            res[3] = 0;
        }
        System.out.write(res, 0, res.length);
        System.out.flush();
    }

    InputCommand parseInput(String input){
        final String[] args = input.split(":");
        if (args.length >= 3){
            final HashMap<String, String> commandsMap = new HashMap<String, String>();
            CommandType commandType = CommandType.fromString(args[0]);

            commandsMap.put("user", args[1]);
            commandsMap.put("server", args[2]);
            if (args.length == 4){
                commandsMap.put("password", args[3]);
            }
            return new InputCommand(commandType, commandsMap);

        }else{
            return new InputCommand(CommandType.UNKNOWN);
        }
    }

    private boolean runCommand(InputCommand command){
        boolean status = false;
        switch (command.commandType){
            case AUTHENTICATE:
                status = this.authenticate(command.inputParameters.get("user"), command.inputParameters.get("server"), command.inputParameters.get("password"));
                break;
            case EXISTS:
                status = this.exists(command.inputParameters.get("user"), command.inputParameters.get("server"));
                break;
            case SET_PASS:
                status = this.setPassword(command.inputParameters.get("user"), command.inputParameters.get("server"), command.inputParameters.get("password"));
                break;
            case TRY_REGISTER:
                status = this.register(command.inputParameters.get("user"), command.inputParameters.get("server"), command.inputParameters.get("password"));
                break;
            case REMOVE_USER:
                status = this.remove(command.inputParameters.get("user"), command.inputParameters.get("server"));
                break;
            case REMOVE_SAFELY:
                status = this.removeSafely(command.inputParameters.get("user"), command.inputParameters.get("server"), command.inputParameters.get("password"));
                break;
        }
        return status;
    }

}
