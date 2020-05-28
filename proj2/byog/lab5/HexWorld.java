package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
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

    private static TETile randomTile(TETile[][] tiles, Point p, int size) {
        ArrayList<TETile> types = new ArrayList<>();
        for (Point i : getNeighbourPoints(p, size)) {
            TETile temp = getBiome(tiles, i, size);
            if (temp != Tileset.NOTHING) {
                types.add(temp);
            }
        }
        int inherit = RANDOM.nextInt(3); //change to inheritRate
        if (inherit != 0) {
            int fromNeighbour = RANDOM.nextInt(types.size());
            return types.get(fromNeighbour);
        }
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.TREE;
            case 3: return Tileset.WATER;
            case 4: return Tileset.MOUNTAIN;
            default: return Tileset.NOTHING;
        }
    }

    public static void addHexagon(TETile[][] tiles, Point p, int size, TETile tile) {
        for (int y = 0; y < size; y += 1) {
            for (int x = size - 1 - y; x < 2*size - 1 + y; x += 1) {
                tiles[p.x + x][p.y + y] = tile;
            }
        }
        for (int y = size; y < 2*size; y += 1) {
            for (int x = y - size; x < 4*size - 2 - y; x += 1) {
                tiles[p.x + x][p.y + y] = tile;
            }
        }
    }

    public static TETile getBiome(TETile[][] tiles, Point p, int size) {
        return tiles[p.x + size - 1][p.y + size - 1];
    }

    public static Point[] getNeighbourPoints(Point p, int size) {
        Point[] points = new Point[6];
        points[0] = new Point(p.x, p.y - size * 2);
        points[1] = new Point(p.x + size * 2 - 1, p.y - size);
        points[2] = new Point(p.x + size * 2 - 1, p.y + size);
        points[3] = new Point(p.x, p.y + size * 2);
        points[4] = new Point(p.x - size * 2 + 1, p.y + size);
        points[5] = new Point(p.x - size * 2 + 1, p.y - size);
        return points;
    }

    public static void drawNeighbours(TETile[][] tiles, Point p, int size) {
        for (Point i : getNeighbourPoints(p, size)) {
            if (getBiome(tiles, i, size) == Tileset.NOTHING) {
                addHexagon(tiles, i, size, randomTile(tiles, i, size));
            }
        }
    }

    public static void biomeGenerator(TETile[][] tiles, Point center, int size, int worldSize) {
        if (worldSize == 1) {
            return;
        }
        drawNeighbours(tiles, center, size);
        for (Point i : getNeighbourPoints(center, size)) {
            biomeGenerator(tiles, i, size, worldSize - 1);
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

        Point center = new Point(20,20);

        addHexagon(world, center, SIZE, Tileset.GRASS);
        biomeGenerator(world, center, SIZE, 5);

        ter.renderFrame(world);
    }
}
