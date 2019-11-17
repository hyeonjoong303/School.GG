package com.example.sunrin.schoolgg;

public class Board{
    String tittle,content;

    Board(){}
    Board(String title,String content){

        this.tittle =title;
        this.content=content;

    }
    public String gettitle(){return tittle;}
    public String getcontent(){return content;}



    public void settitle(String tittle){this.tittle=tittle;}
    public void setcontent(String content){this.content=content;}


}
