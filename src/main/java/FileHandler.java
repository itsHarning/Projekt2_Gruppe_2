import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static List<Member> getListFromJson() {
        ObjectMapper objectMapper = JsonMapper.builder().findAndAddModules().build();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        try {
            return objectMapper.readValue(
                    new File("src/main/resources/TimeList.json"),
                    new TypeReference<List<Member>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeListToJson(ArrayList<Member> tempList){
        ObjectMapper objectMapper = JsonMapper.builder().findAndAddModules().build();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // enables standard indentation ("pretty printing")
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS); // to allow serialization of "empty" POJOs (no properties to serialize, without this setting, an exception is thrown in those cases)
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // to write java.util.Date, Calendar as number (timestamp):

        try {
            objectMapper.writeValue(new File("src/main/resources/TimeList.json"), tempList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
