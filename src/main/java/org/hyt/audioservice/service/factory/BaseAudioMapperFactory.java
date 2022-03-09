package org.hyt.audioservice.service.factory;

import org.hyt.audioservice.model.dto.api.factory.DtoFactory;
import org.hyt.audioservice.service.BaseAudioMapper;
import org.hyt.audioservice.service.api.AudioMapper;
import org.hyt.audioservice.service.api.factory.AudioMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class BaseAudioMapperFactory implements AudioMapperFactory {

    @Override
    public AudioMapper getMapper(DtoFactory dtoFactory) {
        return new BaseAudioMapper(dtoFactory);
    }

}
