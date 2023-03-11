package environment;

import Organism.Organism;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {

  private final Organism grid[][];
  private final int rows;
  private final int cols;

  public Grid(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.grid = new Organism[rows][cols];
  }

  public int rows() {
    return this.rows;
  }

  public int cols() {
    return this.cols;
  }

  public boolean isValidPos(int x, int y) {
    if (x < 0 || x >= rows || y < 0 || y >= cols) return false;
    return grid[x][y] == null;
  }

  public void setPos(int x, int y, Organism organism) {
    if (x < 0 || x >= rows || y < 0 || y >= cols) return;
    this.grid[x][y] = organism;
  }

  public Organism getPos(int x, int y) {
    if (x < 0 || x >= rows || y < 0 || y >= cols) return null;
    return this.grid[x][y];
  }

  public GridPosition move(GridPosition pos, int dir) {
    int x = pos.x();
    int y = pos.y();

    int newX = pos.x();
    int newY = pos.y();
    switch (dir) {
      case 0: newY--;
      case 1: newX++;
      case 2: newY++;
      case 3: newX++;
    }
    if (!isValidPos(newX, newY)) return pos;
    this.grid[newX][newY] = this.grid[x][y];
    this.grid[x][y] = null;
    return new GridPosition(newX, newY);
  }

  public void placeRandom(Organism o) {
    Random r = new Random();
    while (true) {
      int x = r.nextInt(cols);
      int y = r.nextInt(rows);
      if (isValidPos(x, y)) {
        setPos(x, y, o);
        return;
      }
    }
  }

  public List<Organism> getOrganisms() {
    List<Organism> organisms = new ArrayList<>();
    for (Organism[] col : grid) {
      for (Organism o : col) {
        if (o != null) organisms.add(o);
      }
    }
    return organisms;
  }
}
