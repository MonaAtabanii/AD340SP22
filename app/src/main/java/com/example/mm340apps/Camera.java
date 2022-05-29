package com.example.mm340apps;

public class Camera {
    String camId;
    String camDescription;
    String camPicUrl;
    double[] camCoordinates;

    public Camera(String camId, String camDescription, String camPicUrl, double[] camCoordinates) {
        this.camId = camId;
        this.camDescription = camDescription;
        this.camPicUrl = camPicUrl;
        this.camCoordinates = camCoordinates;
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

    public double[] getCamCoordinates() {
        return this.camCoordinates;
    }
}