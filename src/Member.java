public class Member {
    int memberId;
    String memberName;
    int memberAge;
    String memberPhoneNumber;
    boolean isActiveMember;
    boolean isCompeting;

    Member(int Id, String name, int age, String phoneNum, String active, String competing){
        memberId = Id;
        memberName = name;
        memberPhoneNumber = phoneNum;
        memberAge = age;
        isActiveMember = active.equalsIgnoreCase("active");
        isCompeting = competing.equalsIgnoreCase("competing");
    }
}
