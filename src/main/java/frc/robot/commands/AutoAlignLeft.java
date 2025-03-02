package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.generated.TunerConstants_other;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Driving;
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

//TODO: remove unused imports. ensure nothing commented would be coming back


public class AutoAlignLeft extends Command {
  //unsure if necessary, don't remove at comp
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final PhotonVision m_PhotonVision;
  private final Driving m_Driving;


  public AutoAlignLeft(PhotonVision photonVision, Driving driving) {
    
    m_PhotonVision = photonVision;
    m_Driving = driving;
    
    //subsystem dependencies.
    addRequirements(photonVision);
    addRequirements(driving);
    
    
  }
  private boolean mode = false;
  private double outputVelocityX;
  private double outputVelocityY;
  private boolean done;
  private double time;
  private double timeout;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Driving.setMode(true);
    m_Driving.setRotation(0);
    m_Driving.setX(0);
    m_Driving.setY(0);

    mode = false;

    outputVelocityX = 0;
    outputVelocityY = 0;

    done = false;
    time = System.currentTimeMillis();
    
  }

 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //MotionMagicController rotationPID;

    if(m_PhotonVision.getHas() && m_PhotonVision.getAmbiguity() < 0.8){
      double yaw = m_PhotonVision.getYaw();

      double scaleRotation = Math.pow(10, Constants.scaleRotation);
      //System.out.println(Math.floor((-yaw)*scale1)/scale1);

      double output = Math.floor((-((yaw)-Constants.rotationalleftoffset)*1)*scaleRotation)/scaleRotation;
      //double output = (-yaw/2);

      //double outputVelocityX;
      //double outputVelocityY;
      if(m_PhotonVision.getAmbiguity()<Constants.poseAmbigMax){

        if(output>Constants.maxRotationalOutput){
          output = 0.1;
        }
        else if(output <-1*Constants.maxRotationalOutput){
          output = -0.1;
        }
        if(Math.abs(output)<Constants.modeChangeLimit&& !mode){
          mode = true;
          
        }
        if(mode){
          double scale = Math.pow(10, Constants.scaleTranslation);
          outputVelocityX = Math.floor((m_PhotonVision.getX()-Constants.leftForwardOffest)*scale)/scale/3;
         //outputVelocityY = (m_PhotonVision.getY()-0.125)/15;
          outputVelocityY = Math.floor((m_PhotonVision.getY()-Constants.leftOffset)*scale)/scale/3;
          //outputVelocityY = m_PhotonVision.getY(); 
        
          Math.max(-1*Constants.maxTranslationalOutput, Math.min(outputVelocityX, Constants.maxTranslationalOutput));
          Math.max(-1*Constants.maxTranslationalOutput, Math.min(outputVelocityY, Constants.maxTranslationalOutput));
        }else{
          outputVelocityX = 0;
          outputVelocityY = 0;
        }


        m_Driving.setX(outputVelocityX);
        m_Driving.setY(outputVelocityY);
        
        m_Driving.setRotation(output);

        //clever little timer, ends after certain amount of time for sure
        //here so we don't accidentally shoot it into the bot
        if(System.currentTimeMillis()-time > 10000){
          done = true;   
        }

        
        //System.out.println( "VX:"+outputVelocityX+" VY:"+outputVelocityY + " output:"+ output)

    }
    else{
      m_Driving.setRotation(0);
    }
   }
  }

  @Override
  public void end(boolean interrupted) {

    m_Driving.setMode(false);
    m_Driving.setX(0);
    m_Driving.setY(0);
    m_Driving.setRotation(0);
    //TODO: set print to something more logical, and distinct from elevator print
    System.out.println("Sigma");
  }


  //ends on that timer, or is interrupted
  @Override
  public boolean isFinished() {
    return done;
  }
}
