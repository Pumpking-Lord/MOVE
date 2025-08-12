package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.units.measure.Angle;

public class PID {
    private final double kp = 1;
    private final CANcoder encoder = new CANcoder(0); 
    private final TalonSRX rightCtrl = new TalonSRX(8);
    private final TalonSRX leftCtrl = new TalonSRX(5);
    private double speed = 0;
    
    private final StatusSignal<Angle> currentPose = encoder.getAbsolutePosition();
    
    public void move(int target) {
        speed = (target-currentPose.getValueAsDouble())*kp;
        rightCtrl.set(ControlMode.PercentOutput, speed);
        leftCtrl.set(ControlMode.PercentOutput, -speed);
    }
}