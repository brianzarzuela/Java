public class Point {
    private int row;
    private int column;

    public Point(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public String toString(){
        return "row="+ row +", column="+ column;
    }
    public boolean equals(Point point){
        if (this.row ==point.getRow()){
            if (this.column ==point.getColumn()){
                return true;
            }
        }
        return false;
    }
}