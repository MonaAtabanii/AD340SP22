package com.example.mm340apps;

public class Camera {
    String camId;
    String camDescription;
    String camPicUrl;
    Double[] camCoordinates;

    public Camera(String camId, String camDescription, String camPicUrl, Double[] camCoordinates) {
        this.camId = camId;
        this.camDescription = camDescription;
        this.camPicUrl = camPicUrl;
        this.camCoordinates = camCoordinates;
    }

    public Camera() {

    }

    public String getCamId() {
        return this.camId;
    }

    public String getCamPicUrl() {
        return this.camPicUrl;
    }

    public String getCamDescription() {
        return this.camDescription;
    }

    public Double[] getCamCoordinates() {
        return this.camCoordinates;
    }

    public void setCamId(String camId) {
        this.camId = camId;
    }

    public void setCamDescription(String camDescription) {
        this.camDescription = camDescription;
    }

    public void setCamPicUrl(String camPicUrl) {
        this.camPicUrl = camPicUrl;
    }

    public void setCamCoordinates(Double[] camCoordinates) {
        this.camCoordinates = camCoordinates;
    }
}