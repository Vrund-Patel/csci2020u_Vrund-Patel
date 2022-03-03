public abstract class VideoGame {
    abstract void startGame();
    abstract void endGame();

    //template method
    public final void play(){

        //start game
        startGame();

        //end game
        endGame();
    }
}