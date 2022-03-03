// Abstract Class
public abstract class VideoGame {
    abstract void startVideoGame();
    abstract void endVideoGame();

    //template method
    public final void play(){

        //start video game
        startVideoGame();

        //end video game
        endVideoGame();
    }
}
