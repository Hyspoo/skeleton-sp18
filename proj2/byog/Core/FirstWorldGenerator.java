package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class FirstWorldGenerator {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;
    private static final int SIZE = 2;
    //private static final double inheritRate = 0.5;

    private static final long SEED = 100;
    private static final Random RANDOM = new Random(SEED);

    private static class Point {
        private final int x, y;

        public Point(int a, int b) {
            x = a;
            y = b;
        }
    }

}
