//Concrete Class
public class Pokemon extends VideoGame {

    @Override
    void endVideoGame() {
        System.out.println("You became the elite 4 champion");
    }

    @Override
    void startVideoGame() {
        System.out.println("Welcome to the World of Pokemon!");
    }
}
