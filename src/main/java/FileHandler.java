import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static List<TimeHolder> getMemberTimeFromJson() {
        ObjectMapper objectMapper = JsonMapper.builder().findAndAddModules().build();
        try {
            List<TimeHolder> test = objectMapper.readValue(
                    new File("src/main/resources/TimeList.json"),
                    new TypeReference<List<TimeHolder>>() {});
            test.forEach(x -> System.out.println(x.toString()));
            return test;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeMemberTimeToJson(ArrayList tempList){
        ObjectMapper objectMapper = JsonMapper.builder().findAndAddModules().build();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // enables standard indentation ("pretty printing")
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS); // to allow serialization of "empty" POJOs (no properties to serialize, without this setting, an exception is thrown in those cases)
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // to write java.util.Date, Calendar as number (timestamp):

        try {
            // System.out.println(tempList);
            // String jsonString = objectMapper.writeValueAsString(tempList);
            // System.out.println(jsonString);
            objectMapper.writeValue(new File("src/main/resources/TimeList.json"), tempList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ArrayList<Member> memberList = MemberHandler.loadMembersFromTextFile();
        System.out.println("Member List Loaded");
        System.out.println(memberList);
        ChangeActivityStatus.changeActivityStatus(memberList);
        System.out.println("Activity Status Changed");
        Member testMember = memberList.get(1);
        System.out.println(testMember);
        System.out.println("Member Printed");
        System.out.println(testMember.competitiveSwimmer);
        ArrayList<TimeHolder> testList = testMember.competitiveSwimmer.personalTimes;
        testList.add(new TimeHolder(Discipline.BUTTERFLY,200, Duration.parse("pt4m20s"), LocalDate.now(),true,"Test Meet"));
        testList.add(new TimeHolder(Discipline.FREESTYLE,50,Duration.parse("pt45.69s"),LocalDate.now(),true,"Meet Test"));
        System.out.println(testMember.competitiveSwimmer);
        System.out.println(testList);
        System.out.println("testList printed");
        writeMemberTimeToJson(memberList);
        System.out.println("written to json");
        List<TimeHolder> test = getMemberTimeFromJson();
        System.out.println("read from json");
        System.out.println(test);
    }
}
