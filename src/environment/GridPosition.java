package environment;

public record GridPosition(int x, int y) {
  public String toString() {
    return "[" + Integer.toString(x) + ", " + Integer.toString(y) + "]";
  }
}
