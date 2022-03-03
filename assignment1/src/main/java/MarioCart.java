//Concrete Class
public class MarioCart extends VideoGame {

    @Override
    void endGame() {
        System.out.println("You came 1st Place in Mario Cart!");
    }

    @Override
    void startGame() {
        System.out.println("Started Mario Cart race");
    }
}
