package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ProfilesOne extends SubsystemBase{
    private static final TalonSRX MOTOR = new TalonSRX(1);
        private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(1,1);
        private final TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(0.5, 0.5); 
        private final ProfiledPIDController feedback = new ProfiledPIDController(1, 0, 0, constraints);
    
        public Command motorToVelocity(double velocity) {
            return new FunctionalCommand(
                () -> {setTargetVelocity(velocity);},
                () -> {calculateNextVoltageOutput();},
                (interrupt) -> {},
                () -> false,
                this
            ); 
            }
    
        private void setTargetVelocity(double velocity) {
            //Should be called at the start of the command.
            //We set the target velocity, and reset the error of the controller. 
            feedback.setGoal(new TrapezoidProfile.State(0, velocity)); 
            feedback.reset(MOTOR.getSelectedSensorVelocity());
    }

    private void calculateNextVoltageOutput() {
        //Get the next setpoint for the profile, and use it to calculate PID and feedforward outputs 
        final TrapezoidProfile.State targetState = feedback.getSetpoint();

        double feedbackOutput = feedback.calculate(MOTOR.getSelectedSensorVelocity(), targetState);
        double feedforwardOutput = feedforward.calculate(targetState.velocity,1);

        double voltage = feedforwardOutput + feedbackOutput;

        MOTOR.set(ControlMode.Current,voltage);

    }
}
