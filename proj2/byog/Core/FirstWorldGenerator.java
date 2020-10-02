package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.HexWorld;

import java.util.Random;

public class FirstWorldGenerator {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;

    private static final int MINSIZE = 3;
    private static final int MAXSIZEX = 14;
    private static final int MAXSIZEY = 10;

    private static final long SEED = 1;
    private static final Random RANDOM = new Random(SEED);

    private static class Point {
        private final int x, y;

        public Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    private static void buildRoom(TETile[][] tiles, Point leftBottom, Point rightTop) {
        for (int i = leftBottom.x; i <= rightTop.x; i += 1) {
            tiles[i][leftBottom.y] = Tileset.WALL;
            tiles[i][rightTop.y] = Tileset.WALL;
        }
        for (int i = leftBottom.y; i <= rightTop.y; i += 1) {
            tiles[leftBottom.x][i] = Tileset.WALL;
            tiles[rightTop.x][i] = Tileset.WALL;
        }
        for (int i = leftBottom.x + 1; i < rightTop.x; i += 1) {
            for (int j = leftBottom.y + 1; j < rightTop.y; j += 1) {
                tiles[i][j] = Tileset.FLOOR;
            }
        }
    }

    

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Point P1 = new Point(20,20);
        Point P2 = new Point(40,40);

        buildRoom(world, P1, P2);

        ter.renderFrame(world);
    }

}
