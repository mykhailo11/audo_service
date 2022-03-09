package org.hyt.audioservice.service.api;

import org.bson.Document;
import org.hyt.audioservice.model.dto.api.AudioDto;
import org.springframework.stereotype.Service;

@Service
public interface AudioMapper {

    public AudioDto map(Document document);

    public Document convert(AudioDto audio);

}
