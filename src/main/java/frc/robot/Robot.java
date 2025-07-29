// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private final CommandScheduler schd = CommandScheduler.getInstance();
  private final CommandXboxController xbox = new CommandXboxController(0);
  final Controls controls = new Controls();

  public Robot() {
    //TODO: DEfault command that drives
    DoubleSupplier leftY =() -> xbox.getLeftY();
    DoubleSupplier rightX =() -> xbox.getRightX();
    controls.setDefaultCommand(controls.drive(rightX, leftY));
  }




  @Override
  public void teleopPeriodic(){
    schd.run();
  }
}