public class Ship {
    public int length;
    public Point leftUp;
    public Point rightDown;
    public boolean isHorizontal;
    private int hp; //очки здоровья (изначально равны длине корабля)

    public Ship(int length, Point pointShip, boolean isHorizontal) {

        hp = length;
        this.length = length;
        this.isHorizontal = isHorizontal;

        /*при горизонтаотной ориентации переданная точка становится носом, либо кормой (если при сложении с длиной
        выходит за границы поля 10*10)*/
        if (this.isHorizontal) {
            if (pointShip.y + length <= 10) {
                rightDown = new Point();
                leftUp = pointShip;
                rightDown.x = leftUp.x;
                rightDown.y = leftUp.y + this.length - 1;
            } else {
                leftUp = new Point();
                rightDown = pointShip;
                leftUp.x = rightDown.x;
                leftUp.y = rightDown.y - this.length + 1;
            }
        } else {
            if (pointShip.x + length <= 10) {
                rightDown = new Point();
                leftUp = pointShip;
                rightDown.x = leftUp.x + this.length - 1;
                rightDown.y = this.leftUp.y;
            } else {
                leftUp = new Point();
                rightDown = pointShip;
                leftUp.x = rightDown.x - this.length + 1;
                leftUp.y = rightDown.y;
            }
        }
    }

    /**
     * Проверка пересечений
     *
     * @param ship
     * @return
     */
    public boolean isIntersect(Ship ship) {
        if (ship.rightDown.y + 1 < leftUp.y) {//проверка слева
            return false;
        }
        if (ship.leftUp.y - 1 > rightDown.y) {//проверка справа
            return false;
        }
        if (ship.rightDown.x + 1 < leftUp.x) {//проверка сверху
            return false;
        }
        return !(ship.leftUp.x - 1 > rightDown.x);

    }

    /**
     * уменьшить Очки здоровья корабля
     */
    public void reduceHp(){
        hp--;
    }

    public int getHp() {
        return hp;
    }
}