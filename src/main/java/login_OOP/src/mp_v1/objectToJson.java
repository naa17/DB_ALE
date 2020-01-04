package login_OOP.src.mp_v1;//converts input (a list of Verification objects) to Json format. Outputs a json string
import com.google.gson.Gson;

import java.util.List;

public class objectToJson {
    public static void  objectToJson(List<Verification> listname){
        Gson gson = new Gson();
        String json = gson.toJson(listname);
    }
}
