package com.example.helloandroid18ct2;

public class Song {
    private String Title;
    private int File;

    public Song(String title, int file){
        Title = title;
        File = file;
    }

    public String getTitle() {
        return Title;
    }

    public int getFile() {
        return File;
    }
}
