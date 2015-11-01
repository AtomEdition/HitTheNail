package com.AtomEdition.HitTheNail.model;


public class Nail {

    private boolean pressed;

    private boolean visibility = true;

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

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

}
