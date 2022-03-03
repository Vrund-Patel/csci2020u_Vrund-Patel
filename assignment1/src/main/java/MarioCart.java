//Concrete Class
public class MarioCart extends VideoGame {

    @Override
    void endVideoGame() {
        System.out.println("You came 1st Place in Mario Cart!");
    }

    @Override
    void startVideoGame() {
        System.out.println("Started Mario Cart race");
    }
}
