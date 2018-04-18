package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by harut on 08.03.2018.
 */

public class AudioMusic {
    public Music[] music=new Music[4];
    public AudioMusic(){

//        music[1].play();
    }
    public void StartMusicAndLoop(){
           music[1]= Gdx.audio.newMusic(Gdx.files.internal("MusicForLib/music1.mp3"));
           music[2]= Gdx.audio.newMusic(Gdx.files.internal("MusicForLib/music2.mp3"));
    }
}
