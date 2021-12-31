package com.company;

public enum CellValue {

    X("X"),
    O("O"),
    N(" ");


    CellValue(String tag){
        this.tag = tag;
    }

    private String tag;

    String getTag(){
        return tag;
    }

}
