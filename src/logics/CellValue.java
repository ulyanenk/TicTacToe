package logics;

public enum CellValue {

    X("X"),
    O("O"),
    N(" ");


    CellValue(String tag){
        this.tag = tag;
    }

    private String tag;

    public String getTag(){
        return tag;
    }

}
