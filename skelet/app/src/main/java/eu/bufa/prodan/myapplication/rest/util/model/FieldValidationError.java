package eu.bufa.prodan.myapplication.rest.util.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FieldValidationError {
    @SerializedName("field")
    List<String> field = new ArrayList<>();
    @SerializedName("location")
    String location;
    @SerializedName("messages")
    List<String> messages = new ArrayList<>();

    public List<String> getField() {
        return field;
    }

    public void setField(List<String> field) {
        this.field = field;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
