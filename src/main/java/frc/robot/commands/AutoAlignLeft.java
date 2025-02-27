// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Driving;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PhotonVision;

import static edu.wpi.first.units.Units.Rotation;

import java.io.SequenceInputStream;
import java.io.Serial;

//import com.ctre.phoenix.time.StopWatch;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;




/** An example command that uses an example subsystem. */
public class AutoAlignLeft extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //private final ExampleSubsystem m_subsystem;
  //public CommandSwerveDrivetrain drivetrains = TunerConstants.createDrivetrain(); // My drivetrain
  //private StopWatch m_StopWatch;
  private final PhotonVision m_PhotonVision;
  private final Driving m_Driving;
  //MotionMagicController rotationPID;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoAlignLeft(PhotonVision photonVision, Driving driving) {
    //m_subsystem = subsystem;
    //drivetrain = drivetrain;
    m_PhotonVision = photonVision;
    m_Driving = driving;
    
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
    //addRequirements(drivetrain);
    addRequirements(photonVision);
    addRequirements(driving);
    
    
  }
  private boolean mode = false;
  private double outputVelocityX;
  private double outputVelocityY;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Driving.setMode(true);
    m_Driving.setRotation(0);
    mode = false;
    outputVelocityX = 0;

    outputVelocityY = 0;

    //m_StopWatch.start();

    //rotationPID = new MotionMagicController(0.019, 0, 0, 0, 0, 0);
    
  }

 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //MotionMagicController rotationPID;

    if(m_PhotonVision.getHas() && m_PhotonVision.getAmbiguity() < 0.8){
      double yaw = m_PhotonVision.getYaw();

      double scale1 = Math.pow(10, 2);
      //System.out.println(Math.floor((-yaw)*scale1)/scale1);

      double output = (Math.floor((-yaw)*scale1)/scale1)/2;
      //double output = (-yaw/2);

      //double outputVelocityX;
      //double outputVelocityY;
      if(m_PhotonVision.getAmbiguity()<0.5){

        if(output>0.1){
          output = 0.1;
        }
        else if(output <-0.1){
          output = -0.1;
        }
        if(Math.abs(output)<0.01&& !mode){
          mode = true;
          
        }
        if(mode){
          double scale = Math.pow(10, 4);
          outputVelocityX = Math.floor((m_PhotonVision.getX()-0.33)*scale)/scale/3;
         //outputVelocityY = (m_PhotonVision.getY()-0.125)/15;
          outputVelocityY = Math.floor((m_PhotonVision.getY()+0.2)*scale)/scale/3;
          //outputVelocityY = m_PhotonVision.getY(); 
          
          Math.max(-0.5, Math.min(outputVelocityX, 0.5));
          Math.max(-0.5, Math.min(outputVelocityY, 0.5));
        }else{
          outputVelocityX = 0;
          outputVelocityY = 0;
        }


        m_Driving.setX(outputVelocityX);
        m_Driving.setY(outputVelocityY);
        
        m_Driving.setRotation(output);
        //System.out.println( "VX:"+outputVelocityX+" VY:"+outputVelocityY + " output:"+ output);


    }
    else{
      m_Driving.setRotation(0);
    }
       
    
    
    //SmartDashboard.putNumber("sigma", x);
   } 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    m_Driving.setMode(false);
    m_Driving.setRotation(0);
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
