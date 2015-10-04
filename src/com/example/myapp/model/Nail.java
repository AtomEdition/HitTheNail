package com.example.myapp.model;


public class Nail {

    private boolean pressed;

    public Nail(boolean pressed) {
        this.pressed = pressed;
    }

    public void changeState(){
        this.pressed = !this.pressed;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
