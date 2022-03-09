package org.hyt.audioservice.service;

import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.Document;
import org.hyt.audioservice.model.dto.api.AudioDto;
import org.hyt.audioservice.service.api.AudioMapper;
import org.hyt.audioservice.service.api.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class BaseAudioService implements AudioService {

    private final GridFsTemplate _gridFsTemplate;

    private final GridFsOperations _gridFsOperations;

    private final AudioMapper _audioMapper;

    @Autowired
    public BaseAudioService(
        GridFsTemplate gridFsTemplate,
        AudioMapper audioMapper,
        GridFsOperations gridFsOperations
    ){
        _gridFsTemplate = gridFsTemplate;
        _audioMapper = audioMapper;
        _gridFsOperations = gridFsOperations;
    }

    @Override
    public List<AudioDto> getAll() {
        List<AudioDto> list = new ArrayList<>();
        GridFSFindIterable iterable = _gridFsTemplate.find(
            Query.query(
                Criteria.where("metadata._contentType").is("audio/mpeg")
            )
        );
        iterable
            .map(gridFs -> _audioMapper.map(gridFs.getMetadata()))
            .into(list);
        return list;
    }

    @Override
    public AudioDto getById(long id) {
        GridFSFile file = _getById(id);
        Document document;
        if (file != null && (document = file.getMetadata()) != null) {
            return _audioMapper.map(document);
        }
        return null;
    }

    @Override
    public InputStream getAudio(long id) throws Exception{
        GridFSFile file = _getById(id);
        if (file != null) {
            return _gridFsTemplate.getResource(file).getInputStream();
        }
        return null;
    }

    @Override
    public AudioDto add(AudioDto audio, InputStream stream) {
        Document metadata = _audioMapper.convert(audio);
        _gridFsTemplate.store(
            stream,
            String.format("%s-%s.mp3", audio.getArtist(), audio.getTitle()),
            "audio/mpeg",
            metadata
        );
        return getById(audio.getId());
    }

    @Override
    public AudioDto remove(long id){
        AudioDto audio = getById(id);
        if (audio != null){
            _gridFsTemplate.delete(Query.query(Criteria.where("metadata.id").is(id)));
        }
        return audio;
    }

    private GridFSFile _getById(long id){
        return _gridFsTemplate.findOne(
            Query.query(Criteria.where("metadata.id").is(id))
        );
    }

}
