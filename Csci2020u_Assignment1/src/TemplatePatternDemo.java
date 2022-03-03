public class TemplatePatternDemo {
    public static void main(String[] args) {

        VideoGame nintendoGame = new MarioCart();
        nintendoGame.play();
        System.out.println();
        nintendoGame = new Pokemon();
        nintendoGame.play();
    }
}