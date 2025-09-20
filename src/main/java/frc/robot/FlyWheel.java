package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlyWheel extends SubsystemBase{
    private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(2, 1,2);
    private final WPI_TalonSRX motor = new WPI_TalonSRX(0);
    private final CANcoder encoder = new CANcoder(0); 
    private final StatusSignal<Angle> currentPose = encoder.getPosition();
    private final PIDController feedback = new PIDController(1, 0, 0);

    public Command motorToVelcity(double velocity){
        final double feedforwardOutput = feedforward.calculate(velocity);
        return Commands.run(() -> setVelocity(feedforwardOutput), this);
    }

    public void setVelocity(double velocity){
        motor.setVoltage(velocity);
        double feedBackOutput = feedback.calculate(currentPose.getValueAsDouble());

        double voltage = velocity + feedBackOutput;
        
        motor.setVoltage(voltage);
    }
}