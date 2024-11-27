import com.sun.source.tree.ArrayAccessTree;

import java.util.ArrayList;

public class CompetitiveSwimmer{
    ArrayList<Double> freestyle;
    ArrayList<Double> backstroke;
    ArrayList<Double> breaststroke;
    ArrayList<Double> butterfly;
    ArrayList<Double> medley;

    CompetitiveSwimmer(){
        freestyle=new ArrayList<>();
        backstroke=new ArrayList<>();
        breaststroke=new ArrayList<>();
        butterfly=new ArrayList<>();
        medley=new ArrayList<>();
    }

    public ArrayList<Double> updateFastestTime(ArrayList<Double> fastestTimes){
        fastestTimes.sort(null);
        System.out.println(fastestTimes);
        int listSize = fastestTimes.size();
        System.out.println(listSize);
        if (listSize > 5) fastestTimes.subList(5,listSize).clear();
        System.out.println(fastestTimes);
        return fastestTimes;
    }

    public void printMemberTimes(Member member){
        System.out.println(member.memberName+" hurtigeste tider");
        System.out.println(member.competitiveSwimmer.freestyle);
        System.out.println(member.competitiveSwimmer.backstroke);
        System.out.println(member.competitiveSwimmer.breaststroke);
        System.out.println(member.competitiveSwimmer.butterfly);
        System.out.println(member.competitiveSwimmer.medley);
    }

    public static void main(String[] args) {
        Member member = new Member(2,"test navn",20,true,true,true,true);
        member.competitiveSwimmer=new CompetitiveSwimmer();
        member.competitiveSwimmer.freestyle.add(5.33);
        member.competitiveSwimmer.freestyle.add(7.88);
        member.competitiveSwimmer.freestyle.add(6.66);
        member.competitiveSwimmer.freestyle.add(10.20);
        member.competitiveSwimmer.freestyle.add(4.44);
        member.competitiveSwimmer.freestyle.add(9.99);
        member.competitiveSwimmer.freestyle.add(8.45);
        member.competitiveSwimmer.freestyle.add(8.24);
        member.competitiveSwimmer.freestyle.add(7.38);
        member.competitiveSwimmer.freestyle.add(11.45);
        System.out.println(member.competitiveSwimmer.freestyle);
        member.competitiveSwimmer.updateFastestTime(member.competitiveSwimmer.freestyle);
        System.out.println("new"+member.competitiveSwimmer.freestyle);
        member.competitiveSwimmer.printMemberTimes(member);
    }

}
