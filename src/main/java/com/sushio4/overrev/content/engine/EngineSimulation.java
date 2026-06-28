package com.sushio4.overrev.content.engine;

public class EngineSimulation {
    private boolean running = false;
    private float rpm = 60;
    private float torqueAvailable = 60;
    
    private float peakRpm = 120;
    private float redline = 200;
    private float peakTorque = 40.0f * 4;
    private float baseFriction = 7;
    private float rpmFriction = 0.1f;
    private float pumpLoss = 0.15f;
    private float inertia = 30;

    public EngineSimulation() {

    }

    public void tick(float stressApplied, float currentRpm, float throttle) {
        rpm = currentRpm;
        throttle = Math.clamp(throttle, 0, 1);
        // air
        float breathing = 0;
        if(currentRpm <= 0) {}
        else if(currentRpm < peakRpm) {
            breathing = (float)Math.pow(currentRpm / peakRpm, 1.5f);
        }
        else {
            breathing = Math.max(0.0f, 1.0f - (currentRpm - peakRpm) / (redline - peakRpm));
        }

        //combustion
        float torqueProduced = peakTorque * breathing * throttle;

        //losses
        float losses = baseFriction + currentRpm * rpmFriction + pumpLoss * currentRpm * (1 - throttle) * (1 - throttle);

        //dynamics
        torqueAvailable = torqueProduced - losses;
        float netTorque = 0;
        if(currentRpm == 0) {
            netTorque = 0;
        }
        else {
            netTorque = torqueAvailable - stressApplied / currentRpm;
        }
        float acceleration = netTorque / inertia;

        rpm += acceleration;
        rpm = Math.max(0.0f, rpm);
        if(rpm < 3) rpm = 0;
    }

    public float getRpm() {
        return rpm;
    }

    public float getTorque() {
        return peakTorque;
    }
}
