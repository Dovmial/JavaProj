public class Main {
    public static void main(String[] args) {
/***
 * 9.04-17.04 2019
 *
 */
        AI alphaShips = new AI();
        Player player = new Player();

        Field fieldAI = new Field(alphaShips);
        Field fieldPlayer = new Field(player);

        Board seaBattle = new Board(fieldPlayer, fieldAI); //грузить поле игрока только слева для верной отрисовки, проверок нет
        seaBattle.game();
    }
}
