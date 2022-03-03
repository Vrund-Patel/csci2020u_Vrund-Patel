// Abstract Class
public abstract class VideoGame {
    abstract void startGame();
    abstract void endGame();

    //template method
    public final void play(){

        //start video game
        startVideoGame();

        //end video game
        endVideoGame();
    }
}
