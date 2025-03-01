// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;

public class Elevator1 extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public static DigitalInput HomeLimitSwitch = new DigitalInput(Constants.elevatorLimitSwitchPort);

  public static final TalonFX ElevatorLeader = new TalonFX(Constants.Elevator_Leader_Id);
  public static final TalonFX ElevatorFollower = new TalonFX(Constants.Elevator_Follower_Id);

  public static BooleanSupplier HomeLimitSwitchUnpressed = () -> HomeLimitSwitch.get();

  public Trigger HomeLimitSwitchUnpressedTrigger = new Trigger(HomeLimitSwitchUnpressed);

  public final PositionVoltage m_request = new PositionVoltage(0).withSlot(0);

  public double setPosition = 0.5;



  public Elevator1() {
    //HomeLimitSwitchUnpressedTrigger.onFalse(StopElevator());
    HomeLimitSwitchUnpressed.getAsBoolean();
    //ElevatorLeader.set(0.2);
    //ElevatorLeader.setPosition(0);
    ElevatorFollower.setControl(new Follower(ElevatorLeader.getDeviceID(), false));
    //ElevatorLeader.setControl(m_request.withPosition(setPosition));
    
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
  
  
  public void gotolevel(double targetPos){
    //targetPos = SmartDashboard.getNumber("TestNumber", 0);
    //System.out.println(targetPos);
    //setPosition = targetPos;
    if (targetPos > Constants.MaxMotorPosition) {
      targetPos = Constants.MaxMotorPosition;
    }
    if (targetPos < Constants.MinMotorPosition) {
      targetPos = Constants.MinMotorPosition;
    }
    setPosition = targetPos;
    ElevatorLeader.setControl(m_request.withPosition(targetPos));
  }
  public double getSetPosiiton(){
    return setPosition;
  }
  public double getAccel(){
    return ElevatorLeader.getAcceleration().getValueAsDouble();
  }
  public double getRealPostion(){
    return ElevatorLeader.getPosition().getValueAsDouble();
  }
  public double getVelocity(){
    return ElevatorLeader.getVelocity().getValueAsDouble();
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
    SmartDashboard.putNumber("Elevator Position", ElevatorLeader.getPosition().getValueAsDouble());
    SmartDashboard.putNumber("Elevator Set", setPosition);
    
    //Voltage tempvar8 = ElevatorLeader.getMotorVoltage().getValue();
     //SmartDashboard.putNumber("voltage of ElevatorLeader", tempvar8.magnitude());

     //if (tempvar8.magnitude()>0.1) {
      //SmartDashboard.putBoolean("ElevatorLeader", true);
     //}
     //if (tempvar8.magnitude()<0.1) {
      //SmartDashboard.putBoolean("ElevatorLeader", false);
      //  }

    //Voltage tempvar9 = ElevatorFollower.getMotorVoltage().getValue();
    //SmartDashboard.putNumber("voltage of ElevatorFollower", tempvar9.magnitude());
   
    //if (tempvar9.magnitude()>0.1) {
      //SmartDashboard.putBoolean("ElevatorFollower", true);
    //}
    //if (tempvar9.magnitude()<0.1) {
      //SmartDashboard.putBoolean("ElevatorFollower", false);
    //}
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
