// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkAnalogSensor;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PivotArm extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private SparkMax pivot;
  private RelativeEncoder pivotEncoder;

  private SparkClosedLoopController pid;

  private SparkAnalogSensor pivotAnalogSensor;

  private SparkMaxConfig pivotConfig;

  //private int mode = 1;

  private double position = 0;
  
  
  public PivotArm() {
    pivot = new SparkMax(50, MotorType.kBrushless);
    pivotEncoder = pivot.getEncoder();
    pivotAnalogSensor = pivot.getAnalog();


    pivot.set(0);

    pivotConfig = new SparkMaxConfig();

    pivotConfig.closedLoop.outputRange(-0.1, 0.1);
    pivotConfig.idleMode(IdleMode.kBrake);

    pivotConfig.closedLoop.pidf(0.3, 0, 0, 0);//p was 0.2
    pivotConfig.closedLoop.iZone(0.1);
    pivotConfig.inverted(true);
    //pivotConfig.closedLoopRampRate(0.5);
    
    
    pivot.configure(pivotConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    pid = pivot.getClosedLoopController();

    //pivotEncoder.setPosition(0);




  }
  private boolean Zero(){
    return pivotAnalogSensor.getPosition() > 3;
  }

  public Command endeffectorpivot(double ArmPosition){
            return runOnce(
                () -> { 

                  /*if(ArmPosition<0){
                    pid.setReference(0, SparkBase.ControlType.kPosition);
                    position = 0;
                  }
                  else{
                    pid.setReference(ArmPosition, SparkBase.ControlType.kPosition);
                    position = ArmPosition;
                  }*/
                  pid.setReference(ArmPosition, SparkBase.ControlType.kPosition);
                  position = ArmPosition;

                  



                  

                } );
     }

  public void goTo(double pos){
    pid.setReference(pos, SparkBase.ControlType.kPosition);
    position = pos;
  }
    
  public double getPosition(){
    return position;
  }
  public double getVelocity(){
    return pivotEncoder.getVelocity();
  }
  public double getRealPostion(){
    return pivotEncoder.getPosition();
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
    if(Zero()){
      pivotEncoder.setPosition(0);

    }

    SmartDashboard.putNumber("Pivot Position", pivotEncoder.getPosition());
    SmartDashboard.putNumber("Pivot Set", position);
    SmartDashboard.putBoolean("Pivot Limit", Zero());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
