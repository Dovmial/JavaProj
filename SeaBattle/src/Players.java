import java.util.Random;

public abstract class Players {
    public String name;
    public Ship[] ships;
    protected int scores;

    protected void randomGenerateShips() {

        Point shipCell;
        boolean direct;
        int lenghtShip = 0;

        //Заполнение массива кораблей (4-3-3-2-2-2-1-1-1-1)
        for (int i = 0; i < 10; i++) {
            switch (i) {
                case 0:
                    lenghtShip = 4;
                    break;
                case 1:
                case 2:
                    lenghtShip = 3;
                    break;
                case 3:
                case 4:
                case 5:
                    lenghtShip = 2;
                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                    lenghtShip = 1;
                    break;
            }

            Create_New_Ship:
            while (true) {
                direct = Math.random() > 0.5;// горизонтальная ориентация, если больше 0.5
                shipCell = randomCell();
                ships[i] = new Ship(lenghtShip, shipCell, direct);

                //проверка на пересечение с другими кораблями
                if (i > 0) {
                    for (int j = 0; j < i; j++) {
                        if (ships[i].isIntersect(ships[j])) {
                            continue Create_New_Ship; // пересечение есть => новая попытка создания
                        }
                    }
                }
                //пересечений нет, корабль создан правильно => переход к созданию следующего
                break;
            }
        }
    }
    /**
     * рандомная ячейка. 1 <= (x, y) <= 10
     *
     * @return
     */
    protected Point randomCell() {
        Point shipCell = new Point();
        Random rnd = new Random();
        shipCell.x = 1 + rnd.nextInt(10);
        shipCell.y = 1 + rnd.nextInt(10);
        return shipCell;
    }

    public abstract Point move();

    public void increaseScores() {
       ++scores;
    }

    public int getScores() {
        return scores;
    }
}
