package org.hyt.audioservice.service.api;

import org.hyt.audioservice.model.dto.api.AudioDto;

import java.io.InputStream;
import java.util.List;

public interface AudioService {

    List<AudioDto> getAll() throws Exception;

    AudioDto getById(long id) throws Exception;

    InputStream getAudio(long id) throws Exception;

    AudioDto add(AudioDto audio, InputStream stream) throws Exception;

    AudioDto remove(long id) throws Exception;

}
