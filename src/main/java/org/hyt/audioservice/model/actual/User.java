package org.hyt.audioservice.model.actual;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private long id;

    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String username;

    private String startPromotion;

    private String endPromotion;

    private boolean promoted;

    private List<AudioWrapper> favouriteAudio;

    private List<AudioWrapper> uploadedAudio;

    public long getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getUsername(){
        return username;
    }

    public String getStartPromotion(){
        return startPromotion;
    }

    public String getEndPromotion(){
        return endPromotion;
    }

    public boolean getPromoted(){
        return promoted;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setStartPromotion(String startPromotion){
        this.startPromotion = startPromotion;
    }

    public void setEndPromotion(String endPromotion){
        this.endPromotion = endPromotion;
    }

    public void setPromoted(boolean promoted){
        this.promoted = promoted;
    }

    public List<AudioWrapper> getFavouriteAudio(){
        return favouriteAudio;
    }

    public List<AudioWrapper> getUploadedAudio(){
        return uploadedAudio;
    }

    public void setFavouriteAudio(List<AudioWrapper> favouriteAudio){
        this.favouriteAudio = favouriteAudio;
    }

    public void setUploadedAudio(List<AudioWrapper> uploadedAudio){
        this.uploadedAudio = uploadedAudio;
    }

}
