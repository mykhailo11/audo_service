package org.hyt.audioservice.service;

import org.bson.Document;
import org.hyt.audioservice.model.dto.api.AudioDto;
import org.hyt.audioservice.model.dto.api.factory.DtoFactory;
import org.hyt.audioservice.service.api.AudioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseAudioMapper implements AudioMapper {

    private final DtoFactory _dtoFactory;

    @Autowired
    public BaseAudioMapper(DtoFactory dtoFactory){
        _dtoFactory = dtoFactory;
    }

    @Override
    public Document convert(AudioDto audio) {
        Document document = Document.parse("{}");
        document.put("id", audio.getId());
        document.put("title", audio.getTitle());
        document.put("artist", audio.getArtist());
        document.put("album", audio.getAlbum());
        document.put("albumPath", audio.getAlbumPath());
        document.put("duration", audio.getDuration());
        return document;
    }

    @Override
    public AudioDto map(Document document) {
        AudioDto dto = _dtoFactory.getAudioDto();
        if (document.containsKey("id")){
            dto.setId(document.getLong("id"));
        }
        if (document.containsKey("title")){
            dto.setTitle(document.getString("title"));
        }
        if (document.containsKey("artist")){
            dto.setArtist(document.getString("artist"));
        }
        if (document.containsKey("album")){
            dto.setAlbum(document.getString("album"));
        }
        if (document.containsKey("duration")){
            dto.setDuration(document.getLong("duration"));
        }
        return dto;
    }

}
