package byog.lab5;

import java.util.Random;
import java.util.Scanner;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**
 * Draws a world that is mostly empty except for a small region.
 */
public class BoringWorldDemo {
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;
    private static final long SEED = 2873120;
    private static final Random RANDOM = new Random();

    private static final int startX = 33;
    private static final int startY = 5;

    private static void drawWorld(TETile[][] world, TERenderer ter) {
        ter.renderFrame(world);
    }

    private static TETile setTile(int tileNum) {
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.MOUNTAIN;
            case 3:
                return Tileset.TREE;
            case 4:
                return Tileset.SAND;
            case 5:
                return Tileset.GRASS;
            case 6:
                return Tileset.WATER;
            case 7:
                return Tileset.FLOOR;
            default:
                return Tileset.NOTHING;
        }
    }

    private static TETile[][] generateTETile(int width, int height) {
        TETile[][] world = new TETile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        return world;
    }

    private static int[] calculatePosition(int length, int x, int y) {
        int[] startpoint = new int[2];
        startpoint[0] = x - length + 1;
        startpoint[1] = y + length;
        return startpoint;
    }

    public static void addHexagon(int length, int x, int y, TETile[][] world, TERenderer ter) {
        int tileNum = RANDOM.nextInt(8);
        int added = 0;
        int subtracted = 0;
        for (int i = y; i < y + length; i++) {
            for (int j = x + subtracted; j < x + length + added + subtracted; j++) {
                world[j][i] = setTile(tileNum);
            }
            added += 2;
            subtracted--;
        }
        int[] startpoint = calculatePosition(length, x, y);

        added = 0;
        subtracted = 0;
        for (int i = startpoint[1]; i < startpoint[1] + length; i++) {
            for (int j = startpoint[0] + subtracted; j < startpoint[0] + 3 * length - 2 + added + subtracted; j++) {
                world[j][i] = setTile(tileNum);
            }
            added -= 2;
            subtracted++;
        }
        drawWorld(world, ter);
    }

    private static int[] topLeftPosition(int originalX, int originalY, int length) {
        int returnedValue[] = new int[2];
        returnedValue[0] = originalX - length * 2 + 1;
        returnedValue[1] = originalY + length;
        return returnedValue;
    }

    public static int[] topRightPosition(int originalX, int originalY, int length) {
        int returnedValue[] = new int[2];
        returnedValue[0] = originalX + length * 2 - 1;
        returnedValue[1] = originalY + length;
        return returnedValue;
    }

    public static int[] topPosition(int originalX, int originalY, int length) {
        int returnedValue[] = new int[2];
        returnedValue[0] = originalX;
        returnedValue[1] = originalY + 2 * length;
        return returnedValue;
    }

    public static void drawNHexagon(int length, int x, int y, TETile[][] world, TERenderer ter, int N) {
        for (int i = 0; i < N; i++) {
            addHexagon(length, x, y, world, ter);
            int[] newXY = topLeftPosition(x, y, length);
            x = newXY[0];
            y = newXY[1];
        }
    }

    public static int askForInput() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.print("Enter a number: ");
        int length = reader.nextInt(); // Scans the next token of the input as an int.
        reader.close();
        return length;
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
//        // initialize tiles
//        TETile[][] world = new TETile[WIDTH][HEIGHT];
//        for (int x = 0; x < WIDTH; x += 1) {
//            for (int y = 0; y < HEIGHT; y += 1) {
//                world[x][y] = Tileset.NOTHING;
//            }
//        }
//
//        // fills in a block 14 tiles wide by 4 tiles tall
//        for (int x = 20; x < 35; x += 1) {
//            for (int y = 5; y < 10; y += 1) {
//                world[x][y] = Tileset.WALL;
//            }
//        }
//
        // draws the world to the screen
        //ter.renderFrame(world);

        /*Initialise the world*/
        int length = askForInput();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = generateTETile(WIDTH, HEIGHT);
        /*Draw the 19 Hexagons*/
        drawNHexagon(length, startX, startY, world, ter, 3);
        int[] added = topRightPosition(startX, startY, length);
        drawNHexagon(length, added[0], added[1], world, ter, 4);
        added = topRightPosition(added[0], added[1], length);
        drawNHexagon(length, added[0], added[1], world, ter, 5);
        added = topPosition(added[0], added[1], length);
        drawNHexagon(length, added[0], added[1], world, ter, 4);
        added = topPosition(added[0], added[1], length);
        drawNHexagon(length, added[0], added[1], world, ter, 3);
    }

}
