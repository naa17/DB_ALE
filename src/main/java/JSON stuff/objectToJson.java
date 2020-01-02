//converts input (a list) to Json format
import com.google.gson.Gson;
import java.util.List;

public class objectToJson {
    public objectToJson(List<Verification> listname){
        Gson gson = new Gson();
        String json = gson.toJson(listname);
    }
}
