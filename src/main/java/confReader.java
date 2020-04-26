import java.io.IOException;

import java.net.URL;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*;
import java.io.FileReader;

public class confReader {
    JSONObject data;

    public confReader(String jsonFile) throws Exception{

        ClassLoader classLoader=getClass().getClassLoader();
        URL resource =classLoader.getResource(jsonFile);
        Object obj = new JSONParser().parse(new FileReader(resource.getFile()));
        data = (JSONObject) obj;
    }

    public String get(String key){
        return (String) data.get(key);
    }
}