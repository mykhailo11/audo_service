package org.hyt.audioservice.service.api.factory;

import org.hyt.audioservice.model.dto.api.factory.DtoFactory;
import org.hyt.audioservice.service.api.AudioMapper;

public interface AudioMapperFactory {

    public AudioMapper getMapper(DtoFactory dtoFactory);

}
