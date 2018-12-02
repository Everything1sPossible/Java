package com.sjh.DesignPattern.FacadePattern;

/**
 * @author sjh
 * @Title: MovieFacade
 * @ProjectName com.sjh.DesignPattern.FacadePattern
 * @Description: TODO
 * @date 2018/12/2 14:32
 */
public class MovieFacade {
    private Amplifier amplifier;
    private DvdPlayer dvdPlayer;
    private CdPlayer cdPlayer;


    public MovieFacade() {
        this.amplifier = new Amplifier();
        this.dvdPlayer = new DvdPlayer();
        this.cdPlayer = new CdPlayer();
    }

    public void watchMovie() {
        amplifier.on();
        dvdPlayer.on();
        cdPlayer.on();
    }
}
