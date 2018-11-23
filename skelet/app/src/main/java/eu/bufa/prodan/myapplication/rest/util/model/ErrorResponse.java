package eu.bufa.prodan.myapplication.rest.util.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ErrorResponse {
    private Error error;
    private String status;
    private String statusText;

    @SerializedName("errors")
    private List<FieldValidationError> fieldValidationErrors;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public List<FieldValidationError> getFieldValidationErrors() {
        return fieldValidationErrors;
    }

    public void setFieldValidationErrors(List<FieldValidationError> fieldValidationErrors) {
        this.fieldValidationErrors = fieldValidationErrors;
    }
}
