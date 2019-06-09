public class Field {
    Point[][] cells;
    private Players player;

    Field(Players player) {
        this.player = player;
        cells = new Point[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Point(i + 1, j + 1);
            }
        }
        setShips();
    }

    /**
     * Загрузить корабли игрока на поле
     */
    private void setShips() {
        for (int i = 0; i < 10; i++) {
            int x = player.ships[i].leftUp.x - 1;
            int y = player.ships[i].leftUp.y - 1;
            int shipLenght = player.ships[i].length;

            int y1 = player.ships[i].rightDown.y - 1;
            int x1 = player.ships[i].rightDown.x - 1;

            if (player.ships[i].isHorizontal) { //Горизонтальный корабль
                for (int k = y; k < y + shipLenght; k++) {
                    cells[x][k].isShip = true;
                }
            } else { //вертикальный корабль
                for (int k = x; k < x + shipLenght; k++) {
                    cells[k][y].isShip = true;
                }
            }
        }
    }

    public void initCellsState (boolean isVisible) {
        if (isVisible) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if(cells[i][j].isShip)
                        cells[i][j].state();
                }
            }
        }
    }
    public void show(){
        System.out.print("  ");
        for (int i = 1; i < 11; i++) {
            System.out.print(i + "  ");
        }

        System.out.println();
        for (int i = 0; i < 10; i++) {
            if(i<9)
                System.out.print((i+1) + "  ");
            else
                System.out.print((i+1) + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + cells[i][j].ch);
            }
            System.out.println();
        }
    }

    public Players getPlayer() {
        return player;
    }
}