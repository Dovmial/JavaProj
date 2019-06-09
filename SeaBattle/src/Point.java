public class Point {
    int x;
    int y;
    boolean isShooting; //был ли выстрел в ячейку
    boolean isShip; //корабль тут
    boolean isShipDestroyed; //утоплен
   // boolean isShipDamaged; //попадание
    boolean isShootingMiss; //промах
    public char ch;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
        ch = (char) 75571;
        isShooting = false;
        isShip = false;
        isShipDestroyed = false;
        //isShipDamaged = false;
        isShootingMiss = false;
    }

    Point() {
        ch = (char) 75571;
    }

    public void state() {
        if (isShip) {
            if (isShipDestroyed)
                ch = (char) 75598;
            /*
            else if (isShipDamaged)
                ch = (char) 10060;
             */
            else ch = (char) 61230;
        }
        else if (isShootingMiss)
            ch = (char) 61751;
        /*
        else
            ch = (char)61751;*/
    }
}


/*
    public void show() {

        char ch = '*';

        System.out.print("  ");
        for (int i = 1; i < 11; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 1; i < 10; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < 10; j++) {
                System.out.printf("%s ", ch);
            }
            System.out.println();
        }
        System.out.print(10 + " ");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s ", ch);
        }
        System.out.println();
    }
       /*
        9949 10062 10060 75598! зачеркнутый квалрат
        57347 59193 61230! 61803 квадрат
        61751 75571! выстрел
         */

/*
        просмотр симаолов
        char ch = (char) (1);
        int step = 1;
        for (int i = step; i < step + 48; i++) {
            if (i % 15 != 0)
                System.out.printf(i + ":" + "\u001B[31m" + "%s" + "\u001B[0m" + "  ", (char) (ch + i)); //выделение красным
            else {
                System.out.println();
                System.out.printf(i + ":" + "\u001B[31m" + "%s" + "\u001B[0m" + "  ", (char) (ch + i));
            }
        }

        System.out.print("  1 2 3 4 ");
        System.out.printf("\n%s%s%s \n%s   %s\n%s%s%s",(char) 75163,(char) 75136, (char) 75164, (char)75148,(char) 75148, (char) 75161,(char) 75140,(char) 75167);

    }
    */