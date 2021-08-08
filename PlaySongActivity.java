package com.example.p14musicstream;

import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlaySongActivity extends AppCompatActivity {
    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private int drawable;
    private int currentIndex = -1;
    private MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause = null;
    private SongCollection mySongCollection = new SongCollection();

    //this code is to contain the original sequence of the songs
    private SongCollection originalmySongCollection = new SongCollection();

    //seekbar code
    SeekBar seekbar;
    Handler handler = new Handler();

    //loop button code
    Button loopBtn;
    Boolean loopFlag = false;
    //shuffle button code
    Button shuffleBtn;
    Boolean shuffleFlag = false;

    List<Song> shuffleList = Arrays.asList(mySongCollection.allSongs);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        btnPlayPause = (Button)findViewById(R.id.btnPlayPause);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);

        //seek bar code
        seekbar = findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(player != null && player.isPlaying()) {
                    player.seekTo(seekBar.getProgress());
                }

            }

        });
        //code for buttons
        loopBtn = findViewById(R.id.loopBtn);
        shuffleBtn = findViewById(R.id.shuffleBtn);
        if(player !=null){
            seekbar.setMax(player.getDuration());
        }

    }
    //method to display song infomation
    public void displaySongBasedOnIndex(int selectedIndex){
        Song selectedSong = mySongCollection.getCurrentSong(selectedIndex);
        title = selectedSong.getTitle();
        artiste = selectedSong.getArtiste();
        fileLink = selectedSong.getFileLink();
        drawable = selectedSong.getDrawable();
        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);
        TextView txtArtiste = findViewById(R.id.txtArtiste);
        txtArtiste.setText(artiste);
        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        iCoverArt.setImageResource(drawable);
    }
    //create a method to play the song
    public void playSong(String songURL){
        //try to play music, if it fails, we will catch the error and display the error msg
        try{
            player.reset();
            player.setDataSource(songURL);
            player.prepare();
            player.start();
            stopWhenMusicEnds();
            btnPlayPause.setText("PAUSE");
            setTitle(title);//set the title of the page to display the song title
        }
        catch(IOException e){
            e.printStackTrace();//print the error message
        }
    }
    //create a method that will be triggered when the play button is pressed
    public void playOrPauseMusic(View myView){
        if(player.isPlaying()){//if the player is playing the song
            player.pause();
            btnPlayPause.setText("PLAY");
        }
        else{
            player.start();
            seekbar.setMax(player.getDuration());
            handler.removeCallbacks(p_bar);
            handler.postDelayed(p_bar, 1000);
            btnPlayPause.setText("PAUSE");
        }
    }
    //code  so that the seekbar can reset
    Runnable p_bar = new Runnable() {
        @Override
        public void run() {
            Log.d("temasek", "run: ");
            if (player != null && player.isPlaying()) {
                seekbar.setProgress(player.getCurrentPosition());
            }
            handler.postDelayed(this, 1000);
        }
    };
    @Override
    public void  onBackPressed(){
        super.onBackPressed();
        handler.removeCallbacks(p_bar);
        player.release();
    }
    //create a method to detect when the song finish playing and used to loop the music
    private void stopWhenMusicEnds(){
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mediaPlayer){

                if (loopFlag){
                    playOrPauseMusic(null);

                }else{
                    btnPlayPause.setText("PLAY");
                    Toast.makeText(getBaseContext(), " The song has finished playing.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //create a method for the next button
    public void playNext(View myView){
        currentIndex = mySongCollection.getNextSong(currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }
    //create method for the prev button
    public void playPrev(View myView){
        currentIndex = mySongCollection.getPrevSong(currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }
    //method to activate the loop function and change icon
    public void repeatSong(View view) {

         if (loopFlag) {
            loopBtn.setBackgroundResource(R.drawable.loopon);
        }else
            loopBtn.setBackgroundResource(R.drawable.loopstop);
                {
        //code to change the boolean, true turns to false and false turn to true
        loopFlag = !loopFlag;
    }
    }
    //method to activate the shuffle function and change icon
    public void shuffleSong(View view){
        if (shuffleFlag) {
            shuffleBtn.setBackgroundResource(R.drawable.shuffleon);
            mySongCollection = new SongCollection();
        }else{
            shuffleBtn.setBackgroundResource(R.drawable.shuffle);
            Collections.shuffle(shuffleList);
            shuffleList.toArray(mySongCollection.allSongs);
            }
        //code to change the boolean, true turns to false and false turn to true
        shuffleFlag = !shuffleFlag;
        }


    }

