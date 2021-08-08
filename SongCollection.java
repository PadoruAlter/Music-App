package com.example.p14musicstream;

public class SongCollection {
    public Song allSongs[] = new Song[4];

    public SongCollection(){
        Song theWayYouLookTonight = new Song("S1001", "The Way You Look Tonight",
                "Michael Buble", "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",
                4.66, R.drawable.michael_buble_collection);
        Song photograph = new Song("S1002",
                "Photograph",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/097c7b735ceb410943cbd507a6e1dfda272fd8a8?cid=2afe87a64b0042dabf51f37318616965",
        4.32, R.drawable.photograph );
        Song  billiejean= new Song("S1003","Billie Jean","Michael Jackson","https://p.scdn.co/mp3-preview/f504e6b8e037771318656394f532dede4f9bcaea?cid=2afe87a64b0042dabf51f37318616965"
                ,4.9,R.drawable.billie_jean);
        Song Daniela = new Song("S1004","Billie Jean","Daniela Andrade","https://p.scdn.co/mp3-preview/bfe3777177f5ccd7d38075cf64671548969e7428?cid=2afe87a64b0042dabf51f37318616965"
                ,3.93,R.drawable.wonder_wall);
        allSongs[0] = theWayYouLookTonight;
        allSongs[1] = photograph;
        allSongs[2] = billiejean;
        allSongs[3] = Daniela;
    }
    //method to retrieve the index number of the selected song
    public int searchSongById(String id){
        for(int index=0; index < allSongs.length; index++){
            Song tempSong= allSongs[index];
            if(tempSong.getId().equals(id)){
                return index; //returns the index number is ID is found
            }
        }
        return -1; //return an invalid index number is the ID is not found in the array
    }
    //method to retrieve the selected song based on index number
    public Song getCurrentSong(int index){
        return allSongs[index];
    }

    //create method to retrieve the index number of the next song
    public int getNextSong(int currentSongIndex){
        if(currentSongIndex >= allSongs.length-1){//if the current song is the last song
            return currentSongIndex;
    }else{
            return currentSongIndex+1; //go to the index number of the next song
        }
    }

    //create method to retrieve the index number of the PREV song
    public int getPrevSong(int currentsongIndex){
        if(currentsongIndex <= 0){//check if current song is the first song
            return currentsongIndex;
        }else{
            return currentsongIndex-1;//go to the index number of the prev song
        }
    }
}
