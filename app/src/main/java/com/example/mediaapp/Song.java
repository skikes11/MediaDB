package com.example.mediaapp;

import java.io.Serializable;

public class Song implements Serializable {
    private Integer id;
    private String Name;
    private String Singer;
    private String Type;
    private int FileMp3;
    private Integer PlaylistID;

    public Song(Integer id, String name, String singer, String type, int fileMp3, Integer playlistID) {
        this.id = id;
        Name = name;
        Singer = singer;
        Type = type;
        FileMp3 = fileMp3;
        PlaylistID = playlistID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getFileMp3() {
        return FileMp3;
    }

    public void setFileMp3(int fileMp3) {
        FileMp3 = fileMp3;
    }

    public Integer getPlaylistID() {
        return PlaylistID;
    }

    public void setPlaylistID(Integer playlistID) {
        PlaylistID = playlistID;
    }
}
