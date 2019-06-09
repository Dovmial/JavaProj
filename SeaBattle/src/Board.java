public class Board {
    private Field fieldLeft; //поле игрока (открытое)
    private Field fieldRight; //поле компа
    private boolean over = false; //Завершение игры

    Board(Field fieldLeft, Field fieldRight) {

        setFieldLeft(fieldLeft);
        setFieldRight(fieldRight);
    }

    private void setFieldLeft(Field field) {
        fieldLeft = field;
        field.initCellsState(true);
    }

    private void setFieldRight(Field field) {
        fieldRight = field;
    }

    public void game() {
        Point currentShoot;

        show();
        System.out.println("Да начнется игра!\n");
        Players player1, player2;
        Field fieldOfPlayer1, fieldOfPlayer2;

        if (Math.random() > 0.5) { //кто ходит первым?
            player1 = fieldLeft.getPlayer();
            fieldOfPlayer1 = fieldLeft;
            player2 = fieldRight.getPlayer();
            fieldOfPlayer2 = fieldRight;
        } else {
            player1 = fieldRight.getPlayer();
            fieldOfPlayer1 = fieldRight;
            player2 = fieldLeft.getPlayer();
            fieldOfPlayer2 = fieldLeft;
        }
        for (int i = 0; !over; i++) {
            System.out.println("Ход №" + (i + 1));
            System.out.println(player1.name + " стреляет!");
            do {
                currentShoot = player1.move();
            } while (checkShootedCell(currentShoot.x - 1, currentShoot.y - 1, fieldOfPlayer2) && !over);
            if(over)
                break;
            System.out.println(player2.name + " стреляет!");
            while (true) {
                currentShoot = player2.move();
                if (!checkShootedCell(currentShoot.x - 1, currentShoot.y - 1, fieldOfPlayer1)) {
                    break;
                }
            }
            System.out.println();
        }

        System.out.println("Игра окончена");
    }


    /**
     * TODO отрисовка полей
     */
    private void show() {
        System.out.println("Корабли Игрока");
        fieldLeft.show();
        System.out.println("\nКорабли компа");
        fieldRight.show();
    }

    //public void showMenu() {}

    private boolean checkShootedCell(int x, int y, Field field) {
        if (field.cells[x][y].isShooting) {
            return true;
        } else {
            if (field.cells[x][y].isShip) {
                System.out.println("В яблочко!");

                field.cells[x][y].isShooting = true;
                field.cells[x][y].isShipDestroyed = true;
                field.cells[x][y].state();
                currentShipStatus(x + 1, y + 1, field);
                field.getPlayer().increaseScores();
                if(field.getPlayer().getScores() == 20){
                    System.out.println(field.getPlayer().name + " проиграл! Все корабли потоплены.");
                    over = true;
                }
                show();
                return true;
            } else {
                System.out.println("Промах..");
                field.cells[x][y].isShootingMiss = true;
                field.cells[x][y].isShooting = true;
                field.cells[x][y].state();
                show();
                return false;
            }
        }
    }

    /**
     * Проверка корабля на полное уничтожение
     *
     * @param x     строка выстрела
     * @param y     столбец выстрела
     * @param field поле игрока
     */
    private void currentShipStatus(int x, int y, Field field) {
        for (int i = 0; i < 10; i++) {
            if ((y >= field.getPlayer().ships[i].leftUp.y && y <= field.getPlayer().ships[i].rightDown.y
                    && x == field.getPlayer().ships[i].rightDown.x) //поиск корабля по попаданию (горизонтальный)
                    || (x >= field.getPlayer().ships[i].leftUp.x && x <= field.getPlayer().ships[i].rightDown.x // вертикальный
                    && y == field.getPlayer().ships[i].rightDown.y)) {
                field.getPlayer().ships[i].reduceHp();
                if (field.getPlayer().ships[i].getHp() == 0) {
                    System.out.println("Корабль потоплен!");
                    setShipAuraMissing(field.getPlayer().ships[i], field);
                }
                return;
            }
        }
    }

    /**
     * установка ауры промахов вокруг потопленного корабля
     *
     * @param ship  - потопленный корабль
     * @param field - проверяемое игровое поле
     */
    private void setShipAuraMissing(Ship ship, Field field) {
        int x = ship.leftUp.x-1;
        int y = ship.leftUp.y-1;
        int m, n; //коэффициенты смещения для ауры
        if (ship.isHorizontal) {
            m = ship.length;
            n = 1;
        } else {
            m = 1;
            n = ship.length;
        }
        for (int i = x - 1; i < x + 1 + n; i++) {
            for (int j = y - 1; j < y + 1 + m; j++) {
                if ((i >= 0 && i < 10) && (j >= 0 && j < 10) && !field.cells[i][j].isShooting) {
                    field.cells[i][j].isShootingMiss = true;
                    field.cells[i][j].isShooting = true;
                    field.cells[i][j].state();
                }
            }
        }
    }
}
