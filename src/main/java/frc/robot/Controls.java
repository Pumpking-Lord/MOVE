package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Controls extends SubsystemBase{
    private final TalonSRX rightCtrl = new TalonSRX(8);
    private final TalonSRX leftCtrl = new TalonSRX(5);
    private final double speedCtrl = 0.4;
    
    public Command drive(double spin, double move) {
        return this.runOnce(() -> {
            if(move > 0.1 || move < -0.1){
                //move forward and backwards
                rightCtrl.set(ControlMode.PercentOutput, move*speedCtrl);
                leftCtrl.set(ControlMode.PercentOutput, move*-speedCtrl);
            }
            else if(spin > 0.1 || spin < -0.1){
                //spin left and right
                leftCtrl.set(ControlMode.PercentOutput, spin*speedCtrl);
                rightCtrl.set(ControlMode.PercentOutput, spin*speedCtrl);
            }
        });
    }
}
