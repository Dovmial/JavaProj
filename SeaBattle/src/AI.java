
public class AI extends Players{

    AI() {
        ships = new Ship[10];
        name = "Скайнет";
        scores = 0;
        randomGenerateShips();
    }

    public Point move() {
        return randomCell();
    }
}
