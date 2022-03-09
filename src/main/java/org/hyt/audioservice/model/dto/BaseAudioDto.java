package org.hyt.audioservice.model.dto;

import org.hyt.audioservice.model.dto.api.AudioDto;

public class BaseAudioDto implements AudioDto {

    private long id;

    private String title;

    private String artist;

    private String album;

    private long duration;

    private String albumPath;

    public BaseAudioDto(){}

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public long getDuration() {
        return duration;
    }

    public String getAlbumPath() {
        return albumPath;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

    public void setAlbum(String album){
        this.album = album;
    }

    public void setAlbumPath(String albumPath) {
        this.albumPath = albumPath;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

}
