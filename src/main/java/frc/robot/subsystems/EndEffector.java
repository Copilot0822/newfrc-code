// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AnalogInput;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class EndEffector extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private SparkMax leftMax;
  private SparkMax rightMax;
  private AnalogInput stopAnalogInput;
  public EndEffector() {
    SparkMaxConfig config = new SparkMaxConfig();

    config.idleMode(IdleMode.kBrake);
    
    leftMax = new SparkMax(51, MotorType.kBrushless);
    config.inverted(true);
    config.openLoopRampRate(0.01);
    
    leftMax.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

    config.inverted(false);

    
    rightMax = new SparkMax(52, MotorType.kBrushless);

    rightMax.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    //SparkMaxConfig sparkconfig = new SparkMaxConfig();
    //sparkconfig.inverted(true);
    
    stopAnalogInput = leftMax.getAnalog();
    



  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  public boolean getSwitch(){
    return stopAnalogInput.getPosition() > 3;
  }

  public void runed(double output){
    leftMax.set(output);
    rightMax.set(output);
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;

  }

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("Intake Switch", stopAnalogInput.getPosition());
    SmartDashboard.putBoolean("Switch", getSwitch());
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
