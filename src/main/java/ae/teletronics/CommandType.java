package ae.teletronics;

/**
 * Created by kristian on 3/28/16.
 */
public enum CommandType {
    AUTHENTICATE,
    EXISTS,
    SET_PASS,
    TRY_REGISTER,
    REMOVE_USER,
    REMOVE_SAFELY,
    UNKNOWN;
    public static CommandType fromString(String commandType){
        if(commandType.equals("auth")){
            return AUTHENTICATE;
        }else if(commandType.equals("isuser")){
            return EXISTS;
        }else if(commandType.equals("setpass")){
            return SET_PASS;
        }else if(commandType.equals("tryregister")){
            return TRY_REGISTER;
        }else if(commandType.equals("removeuser")){
            return REMOVE_USER;
        }else if(commandType.equals("removeuser3")){
            return REMOVE_SAFELY;
        }else{
            return UNKNOWN;
        }
    }
}
