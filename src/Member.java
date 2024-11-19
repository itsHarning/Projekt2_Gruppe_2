public class Member {
    int memberID;
    String memberName;
    int memberAge;
    String memberPhoneNumber;
    boolean isActiveMember;
    boolean isCompeting;

    Member(int ID, String name, int age, String phoneNum, String active, String competing){
        memberID=ID;
        memberName=name;
        memberPhoneNumber=phoneNum;
        memberAge=age;
        isActiveMember = active.equalsIgnoreCase("active");
        isCompeting = competing.equalsIgnoreCase("competing");
    }
}
