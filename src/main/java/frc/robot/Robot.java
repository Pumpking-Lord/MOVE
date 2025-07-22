// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private final CommandXboxController xbox = new CommandXboxController(0);
  private final TalonSRX Right = new TalonSRX(0);
  private final TalonSRX Left = new TalonSRX(0);
  public void TeleloPeriod(){
    if(xbox.getLeftY() != 0){
      //forward and backward
      Right.set(ControlMode.PercentOutput, xbox.getLeftY()*-1);
      Left.set(ControlMode.PercentOutput, xbox.getLeftY());
    }
    else if(xbox.getRightX() != 0){
      //spin left and right
      Left.set(ControlMode.PercentOutput, xbox.getRightX());
      Right.set(ControlMode.PercentOutput, xbox.getRightX());
    }
  }
}