package eu.bufa.prodan.myapplication.rest.util.model;

import java.util.ArrayList;
import java.util.List;

public class FileUploadResponse {
    private String category_id;
    private List<FileUpload> files = new ArrayList<>();

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public List<FileUpload> getFiles() {
        return files;
    }

    public void setFiles(List<FileUpload> files) {
        this.files = files;
    }
}
