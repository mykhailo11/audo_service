package org.hyt.audioservice.model.dto.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hyt.audioservice.model.dto.BaseAudioDto;

@JsonDeserialize(as = BaseAudioDto.class)
@JsonSerialize(as = BaseAudioDto.class)
public interface AudioDto {

    public long getId();

    public String getTitle();

    public String getArtist();

    public String getAlbum();

    public String getAlbumPath();

    public long getDuration();

    public void setId(long id);

    public void setTitle(String title);

    public void setArtist(String artist);

    public void setAlbum(String album);

    public void setAlbumPath(String albumPath);

    public void setDuration(long duration);

}
