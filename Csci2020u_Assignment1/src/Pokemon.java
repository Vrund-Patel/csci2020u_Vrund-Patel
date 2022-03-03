public class Pokemon extends VideoGame {

    @Override
    void endGame() {
        System.out.println("You became the elite 4 champion");
    }

    @Override
    void startGame() {
        System.out.println("Welcome to the World of Pokemon!");
    }
}