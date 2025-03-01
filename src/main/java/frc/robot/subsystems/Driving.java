// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.generated.TunerConstants_other;
import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;





public class Driving extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  final SwerveRequest.RobotCentric drive1;
  final SwerveRequest.FieldCentric drive;
  CommandSwerveDrivetrain drivetrainer;
  public Driving(CommandSwerveDrivetrain _drivetrain) {
    drivetrainer = _drivetrain;
    drive1 = new SwerveRequest.RobotCentric()
            .withDeadband(0).withRotationalDeadband(0) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    
    drive = new SwerveRequest.FieldCentric()
    .withDeadband(0.25).withRotationalDeadband(0.20) // Add a 10% deadband
    .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
  }
  

  private double x = 0;
  private double y = 0;
  private double r = 0.5;
  private boolean mode = false;
  //public CommandSwerveDrivetrain drivetrainer = TunerConstants.createDrivetrain();
  private final CommandXboxController joystick = new CommandXboxController(Constants.OperatorConstants.kDriverControllerPort);
  
  

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

  public void setX(double X) {
    x = Math.max(-1, Math.min(1, X));
  }
  public void setY(double Y){
    y = Math.max(-1, Math.min(1, Y));
  }
  public void setRotation(double R){
    r = Math.max(-1, Math.min(1, R));

  }
  public void setMode(Boolean type){
    mode = type;    

  }
  

  @Override
  public void periodic() {
    if(mode){
      /*final SwerveRequest.RobotCentric drive = new SwerveRequest.RobotCentric()
            .withDeadband(0).withRotationalDeadband(0) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors*/

      

      drivetrainer.setControl(drive1.withVelocityX(x*TunerConstants_other.kSpeedAt12Volts.in(MetersPerSecond)));

      drivetrainer.setControl(drive1.withVelocityY(y*TunerConstants_other.kSpeedAt12Volts.in(MetersPerSecond)));
      drivetrainer.setControl(drive1.withRotationalRate(r*RotationsPerSecond.of(0.75).in(RadiansPerSecond)));
      
    }
    else{
      if(DriverStation.isAutonomous()){

      }
      else{
        if(Math.abs(joystick.getRightX()) > 0.15){
          drivetrainer.setControl(drive.withRotationalRate(-joystick.getRightX() * RotationsPerSecond.of(0.75).in(RadiansPerSecond)));
        }
       else{
         drivetrainer.setControl(drive.withRotationalRate(0));
       }
 
       if(Math.sqrt(joystick.getLeftY()*joystick.getLeftY() + joystick.getLeftX()*joystick.getLeftX())>0.13){
         drivetrainer.setControl(drive.withVelocityY(-joystick.getLeftX() * TunerConstants_other.kSpeedAt12Volts.in(MetersPerSecond)));
         drivetrainer.setControl(drive.withVelocityX(-joystick.getLeftY() * TunerConstants_other.kSpeedAt12Volts.in(MetersPerSecond)));
       }
       else{
         drivetrainer.setControl(drive.withVelocityY(0));
         drivetrainer.setControl(drive.withVelocityX(0));
       }
       //drivetrainer.setControl(drive.withVelocityX(-joystick.getLeftY() * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond)));
 
       //drivetrainer.setControl(drive.withVelocityY(-joystick.getLeftX() * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond)));
 
       //drivetrainer.setControl(drive.withRotationalRate(-joystick.getRightX() * RotationsPerSecond.of(0.75).in(RadiansPerSecond)));
 
       joystick.y().onTrue(drivetrainer.runOnce(() -> drivetrainer.seedFieldCentric()));
        

      }
      //final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
      //      .withDeadband(0.05).withRotationalDeadband(0.05) // Add a 10% deadband
      //      .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
      // if(Math.abs(joystick.getLeftY())>0.15){
      //   drivetrainer.setControl(drive.withVelocityX(-joystick.getLeftY() * TunerConstants_other.kSpeedAt12Volts.in(MetersPerSecond)));
      // }
      // else{
      //   drivetrainer.setControl(drive.withVelocityX(0));
      // }
      // if(Math.abs(joystick.getLeftX())>0.15){
      //   drivetrainer.setControl(drive.withVelocityY(-joystick.getLeftX() * TunerConstants_other.kSpeedAt12Volts.in(MetersPerSecond)));
      // }
      // else{
      //   drivetrainer.setControl(drive.withVelocityY(0));
      // }
       /*if(Math.abs(joystick.getRightX()) > 0.15){
         drivetrainer.setControl(drive.withRotationalRate(-joystick.getRightX() * RotationsPerSecond.of(0.75).in(RadiansPerSecond)));
       }
      else{
        drivetrainer.setControl(drive.withRotationalRate(0));
      }

      if(Math.sqrt(joystick.getLeftY()*joystick.getLeftY() + joystick.getLeftX()*joystick.getLeftX())>0.13){
        drivetrainer.setControl(drive.withVelocityY(-joystick.getLeftX() * TunerConstants_other.kSpeedAt12Volts.in(MetersPerSecond)));
        drivetrainer.setControl(drive.withVelocityX(-joystick.getLeftY() * TunerConstants_other.kSpeedAt12Volts.in(MetersPerSecond)));
      }
      else{
        drivetrainer.setControl(drive.withVelocityY(0));
        drivetrainer.setControl(drive.withVelocityX(0));
      }
      //drivetrainer.setControl(drive.withVelocityX(-joystick.getLeftY() * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond)));

      //drivetrainer.setControl(drive.withVelocityY(-joystick.getLeftX() * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond)));

      //drivetrainer.setControl(drive.withRotationalRate(-joystick.getRightX() * RotationsPerSecond.of(0.75).in(RadiansPerSecond)));

      joystick.y().onTrue(drivetrainer.runOnce(() -> drivetrainer.seedFieldCentric())); */
    }

    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
