package org.hyt.audioservice.model.dto.factory;

import org.hyt.audioservice.model.dto.BaseAudioDto;
import org.hyt.audioservice.model.dto.api.AudioDto;
import org.hyt.audioservice.model.dto.api.factory.DtoFactory;
import org.springframework.stereotype.Component;

@Component
public class BaseDtoFactory implements DtoFactory {

    @Override
    public AudioDto getAudioDto() {
        return new BaseAudioDto();
    }

}
