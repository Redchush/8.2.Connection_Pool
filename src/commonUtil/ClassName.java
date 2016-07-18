package commonUtil;

public class ClassName {

    public static String getClassName(){
        try{
            throw new RuntimeException();
        } catch (RuntimeException e){
            return e.getStackTrace()[1].getClassName();
        }
    }
}
