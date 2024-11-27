public class CompetitiveSwimmer extends Swimmer{
    String discipline;
    Double bestTime;
        public CompetitiveSwimmer(String name, String discipline, int age, Double bestTime){
            super (name, age);
            this.discipline=discipline;
            this.bestTime= bestTime;

        }
}
