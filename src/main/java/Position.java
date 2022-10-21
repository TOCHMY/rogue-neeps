public record Position(int row,int col) {
    public Position {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Position cannot be negative");
        }
    }

    public Position newPosition(Direction dir) {
        switch (dir) {
            case RIGHT -> {
                return new Position(row, col + 1);
            }
            case DOWN -> {
                return new Position(row + 1, col);
            }
            case UP -> {
                return new Position(row -1 , col);
            }
            case LEFT -> {
                return new Position(row, col - 1);
            }
            default -> {
                throw new IllegalArgumentException("Must be a valid direction");
            }
        }

    }

    @Override
    public int row() {
        return row;
    }

    @Override
    public int col() {
        return col;
    }
}
