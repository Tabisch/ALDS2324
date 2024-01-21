public class PledgeAlgorithmus {

    /**
     * Hauptmethode zum Testen des Pledge-Algorithmus mit verschiedenen Labyrinthen.
     */
    public static void main(String[] args) {
    	
        // Labyrinth 1
        char[][] maze = {
            { 'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X' },
            { 'X','O','O','O','O','X','O','O','O','X','O','O','O','X','O','O','O','X','O','O','O','X','O','O','O','X','O','O','O','X','O','O','O','X','O','X','O','O','O','X','O','O','O','O','O','O','O','O','O','X' },
            { 'X','O','X','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','X','X','X','X','O','X','O','X' },
            { 'X','O','X','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','O','O','X','O','O','O','X','O','O','O','O','O','X','O','X' },
            { 'X','O','O','O','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','O','X','X','X','X','X','X','X','X','X','X','O','X','X','X','X','O','X' },
            { 'X','O','X','X','O','O','O','X','O','O','O','X','O','O','O','X','O','O','O','X','O','O','O','X','O','O','O','X','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X' },
            { 'X','O','X','X','O','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','O','X','X','X','X','X','X','X','X','X','X','X' },
            { 'X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X' },
            { 'X','O','X','X','O','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','O','X','O','X','X','X','X','X','O','X','O','X','X','X','X','X','X','X','X','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','X','O','O','O','X','O','O','O','O','O','X','O','O','O','X','O','O','O','X','O','X','O','O','O','O','O','X','O','X','O','X','O','O','O','O','O','O','O','X','O','E' },
            { 'X','O','O','O','O','X','O','X','O','X','O','X','O','X','O','O','O','O','O','X','O','X','O','X','O','X','O','X','O','X','X','X','X','X','O','X','O','O','O','X','O','X','O','O','O','X','O','X','O','X' },
            { 'X','O','X','X','O','X','O','X','O','X','O','X','O','X','O','O','O','O','O','X','O','X','O','X','O','X','O','X','O','X','O','O','O','O','O','X','X','X','X','X','O','X','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','X','O','X','O','X','O','X','O','O','O','O','O','X','O','X','O','X','O','X','O','X','O','X','O','X','X','X','O','O','O','X','O','O','O','X','O','X','O','O','O','X','O','X' },
            { 'X','O','O','O','O','X','O','X','O','X','O','X','O','X','O','O','O','O','O','X','O','X','O','X','O','X','O','X','O','X','O','O','O','O','O','X','O','O','O','X','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','X','O','X','O','X','O','X','O','O','O','O','O','X','O','X','O','X','O','X','O','X','O','X','X','X','X','X','X','X','X','X','X','X','O','X','X','X','X','X','O','X','O','X' },
            { 'X','O','X','X','O','X','O','X','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','O','O','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','O','O','O','X','O','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','O','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','O','X' },
            { 'X','O','X','X','X','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','X','X','X','X','X','X','X','X','O','O','O','O','X','X','X','O','O','O','O','X','X','X','X','X','X','X','X','X','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','X','O','O','O','X','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','X','O','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','X','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','X','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','X','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','X','O','O','O','X','O','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','X','X','X','X','X','O','O','O','O','O','O','O','X','X','X','O','O','X','O','X','X','X','X','X','X','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','X','X','X','X','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','X','O','X' },
            { 'S','O','O','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','X','O','X' },
            { 'X','X','X','X','X','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','O','O','O','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','X','X','X','X','O','O','O','O','X','X','X','X','X','X','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','X' },
            { 'X','O','X','X','O','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','O','X' },
            { 'X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X' },
            { 'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X' }
        };

        int[] startPosition = {-1,-1};

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if(maze[i][j] == 'S') {
                    startPosition[0] = i;
                    startPosition[1] = j;
                    maze[i][j] = 'O';
                }
            }
        }


      //Tests
      pledgeAlgorithmus(maze, startPosition);
    }

    /**
     * Implementiert den Pledge-Algorithmus.
     *
     * @param maze           Das Labyrinth als zweidimensionales Char-Array (X = Wand, O = Frei, E = Ende)
     * @param startPosition  Die Startposition des Roboters im Labyrinth.
     */

    
    static int stepCounter = 0;

    public static void pledgeAlgorithmus(char[][] maze, int[] startPosition) {
        int direction = 0;  // Aktuelle Richtung des Roboters (0: oben, 1: rechts, 2: unten, 3: links)
        int[] currentPosition = startPosition;
        int counter = 0;

        while (maze[currentPosition[0]][currentPosition[1]] != 'E') {
            // Labyrinth anzeigen
           //printMaze(maze, currentPosition[0], currentPosition[1]);

            if (counter == 0) {
                // Der Roboter folgt der Wand, bis eine Wand erreicht wird
                while (frontFree(maze, currentPosition, direction)) {
                    currentPosition = moveForward(maze, currentPosition, direction);
                    System.out.println("Geraderaus");
                }
                // Nach rechts drehen, wenn eine Wand erreicht wurde
                direction = turnRight(direction);
                counter--;
                System.out.println("Drehung nach rechts");
            } else {
                // Wenn links keine Wand ist, nach Links laufen
                if (leftFree(maze, currentPosition, direction)) {
                    direction = turnLeft(direction);
                    counter++;
                    currentPosition = moveForward(maze, currentPosition, direction);
                    System.out.println("Drehung nach links");
                    // Wenn der Weg vorne frei ist, geradeaus laufen
                } else if (frontFree(maze, currentPosition, direction)) {
                    currentPosition = moveForward(maze, currentPosition, direction);
                } else {
                    // Ist der Weg versperrt, nach Rechts drehen
                    direction = turnRight(direction);
                    counter--;
                    System.out.println("Drehung nach rechts");
                }
                
            }

            System.out.println("Umdrehungszaehler: " + counter);
        }

        System.out.println("Ziel erreicht");

        System.out.println("StepCounter " + stepCounter);
    }

    /**
     * Dreht den Roboter nach links.
     *
     * @param direction Die aktuelle Richtung des Roboters.
     * @return Die neue Richtung nach der Drehung nach links.
     */
    public static int turnLeft(int direction) {
        return (direction + 3) % 4;
    }

    /**
     * Dreht den Roboter nach rechts.
     *
     * @param direction Die aktuelle Richtung des Roboters.
     * @return Die neue Richtung nach der Drehung nach rechts.
     */
    public static int turnRight(int direction) {
        return (direction + 1) % 4;
    }

    /**
     * Bewegt den Roboter vorwärts in der aktuellen Richtung, wenn der Weg frei ist.
     *
     * @param maze      Das Labyrinth als zweidimensionales Char-Array.
     * @param position  Die aktuelle Position des Roboters im Labyrinth.
     * @param direction Die aktuelle Richtung des Roboters.
     * @return Die aktualisierte Position des Roboters nach vorne, wenn der Weg frei ist.
     */
    public static int[] moveForward(char[][] maze, int[] position, int direction) {
        int[] newPosition = calculateNewPosition(position, direction);
        // prüft ob die Bewegung gültig ist, annsonst bleibt die Position gleich
        stepCounter++;

        if (isValidMove(maze, newPosition)) {
            position[0] = newPosition[0];
            position[1] = newPosition[1];
        }
        return position;
    }

    /**
     * Überprüft, ob der Weg nach links frei ist.
     *
     * @param maze      Das Labyrinth als zweidimensionales Char-Array.
     * @param position  Die aktuelle Position des Roboters im Labyrinth.
     * @param direction Die aktuelle Richtung des Roboters.
     * @return True, wenn der Weg nach links frei ist, annsonsten False.
     */
    
    public static boolean leftFree(char[][] maze, int[] position, int direction) {
        int newDirection = turnLeft(direction);
        int[] newPosition = calculateNewPosition(position, newDirection);
        return isValidMove(maze, newPosition);
    }
    
    /**
     * Überprüft, ob der Weg nach vorne frei ist.
     *
     * @param maze      Das Labyrinth als zweidimensionales Char-Array.
     * @param position  Die aktuelle Position des Roboters im Labyrinth.
     * @param direction Die aktuelle Richtung des Roboters.
     * @return True, wenn der Weg nach vorne frei ist, annsonsten False.
     */
    public static boolean frontFree(char[][] maze, int[] position, int direction) {
        int[] newPosition = calculateNewPosition(position, direction);
        return isValidMove(maze, newPosition);
    }

    /**
     * Berechnet die neue Position des Roboters basierend auf der aktuellen Position und Richtung.
     *
     * @param position  Die aktuelle Position des Roboters im Labyrinth.
     * @param direction Die aktuelle Richtung des Roboters.
     * @return Die neue Position des Roboters nach Bewegung.
     */
    private static int[] calculateNewPosition(int[] position, int direction) {
        int[] newPosition = {position[0], position[1]};
        switch (direction) {
            case 0: //oben
                newPosition[0]--;
                break;
            case 1: //rechts
                newPosition[1]++;
                break;
            case 2: //unten
                newPosition[0]++;
                break;
            case 3: //links
                newPosition[1]--;
                break;
        }
        return newPosition;
    }

    /**
     * Überprüft, ob die neue Position im Labyrinth gültig ist (nicht außerhalb des Labyrinths und keine Wand).
     *
     * @param maze     Das Labyrinth als zweidimensionales Char-Array.
     * @param position Die Position, die überprüft werden soll.
     * @return True, wenn die Position gültig ist, andernfalls False.
     */
    private static boolean isValidMove(char[][] maze, int[] position) {
        return position[0] >= 0 && position[0] < maze.length && position[1] >= 0 && 
        		position[1] < maze[0].length && maze[position[0]][position[1]] != 'X';
    }

    /**
     * Zeigt das Labyrinth mit der aktuellen Position("V") auf der Console an.
     *
     * @param maze Das Labyrinth als zweidimensionales Char-Array.
     * @param row  Die Zeile der aktuellen Position des Roboters.
     * @param col  Die Spalte der aktuellen Position des Roboters.
     */
    
    //dient nur der Ausgabe auf der Console 
    public static void printMaze(char[][] maze, int row, int col) {
        if (maze[row][col] != 'E') {
            maze[row][col] = 'V';
        }

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("--------------------");
        maze[row][col] = 'O';
    }
}

