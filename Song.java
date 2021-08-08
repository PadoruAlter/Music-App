package com.example.p14musicstream;

public class Song {
    private String id;
    private String title;
    private String artiste;
    private String fileLink; //link to play the song
    private double songLength; // duration of the song
    private int drawable; // for the cover image of the song

    public Song(String _id, String _title, String _artiste,String _fileLink, double _songLength, int _drawable){
    this.id = _id;
    this.title = _title;
    this.artiste = _artiste;
    this.fileLink = _fileLink;
    this.songLength = _songLength;
    this.drawable = _drawable;
    }
    //get methods allow us to retrieve the value of the attributes
    public String getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getArtiste(){
        return this.artiste;
    }
    public String getFileLink(){
        return this.fileLink;
    }
    public double getSongLength(){
        return this.songLength;
    }
    public int getDrawable(){
        return this.drawable;
    }
    //set methods allows us to assign/set a value to the attribute
    public void setId(String _id){
        this.id = _id;
    }
    public void setTitle(String _title){
        this.title = _title;
    }
    public void setArtiste(String _artiste){
        this.artiste = _artiste;
    }

    public void setFileLink(String _fileLink) {
        this.fileLink = _fileLink;
    }

    public void setSongLength(double _songLength) {
        this.songLength = _songLength;
    }

    public void setDrawable(int _drawable) {
        this.drawable = _drawable;
    }
}
