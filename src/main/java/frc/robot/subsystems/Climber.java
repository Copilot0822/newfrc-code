// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  double multiplier = 500;

  final SparkMax m_Leader = new SparkMax(41, MotorType.kBrushless);

  private SparkClosedLoopController pid = m_Leader.getClosedLoopController();

  private CommandXboxController m_Controller = new CommandXboxController(Constants.OperatorConstants.kDriverControllerPort);
  private com.revrobotics.AnalogInput m_Input;

  public Climber() {
    SparkMaxConfig config = new SparkMaxConfig();
    config.inverted(true);
    config.smartCurrentLimit(80, 50);

    ClosedLoopConfig pidConfig = new ClosedLoopConfig();
    
    pidConfig.p(0.01);
    pidConfig.i(0);
    pidConfig.d(0.05);
    pidConfig.outputRange(-1, 0.2);

    config.closedLoop.apply(pidConfig);
    config.closedLoopRampRate(0.5);

    m_Leader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    m_Input = m_Leader.getAnalog();
    

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
    // This method will be called once per scheduler run

    //double out = (m_Controller.getLeftTriggerAxis() - m_Controller.getRightTriggerAxis()) * multiplier;
    if(m_Input.getPosition()<3){
      if(m_Controller.getLeftTriggerAxis() < 0.15 && m_Controller.getRightTriggerAxis() < 0.15){
        m_Leader.set(0);
      }
      else{
        double out = (m_Controller.getLeftTriggerAxis() - m_Controller.getRightTriggerAxis());
        //pid.setReference(out, ControlType.kVelocity);
  
        m_Leader.set(out);
  
  
      }

    }
    else{m_Leader.set(0);}

    

    //pid.setReference(out, ControlType.kVelocity);


  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
