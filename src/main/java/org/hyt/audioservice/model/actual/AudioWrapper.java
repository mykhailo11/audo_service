package org.hyt.audioservice.model.actual;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;

public class AudioWrapper implements Serializable {

    @DBRef
    private GridFSFile audio;

    public GridFSFile getAudio(){
        return audio;
    }

    public void setAudio(GridFSFile audio){
        this.audio = audio;
    }

}
