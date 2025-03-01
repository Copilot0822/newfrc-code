// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.lang.annotation.Target;
import java.util.function.DoubleSupplier;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PhotonVision extends SubsystemBase {
  PhotonTrackedTarget bestTarget;
  GenericEntry Rotation3d_WidgetX,Rotation3d_WidgetY,Rotation3d_WidgetZ;
  private PhotonCamera p_photon = new PhotonCamera("Front Cam");
  /** Creates a new ExampleSubsystem. */
  public PhotonVision() {
    Rotation3d_WidgetX = Shuffleboard.getTab("SmartDashboard").add("Rotation3d X",0).getEntry();
    Rotation3d_WidgetY = Shuffleboard.getTab("SmartDashboard").add("Rotation3d Y",0).getEntry();
    Rotation3d_WidgetZ = Shuffleboard.getTab("SmartDashboard").add("Rotation3d Z",0).getEntry();
    //SmartDashboard.putNumber("PhotonX", p_photon.getLatestResult().getBestTarget().getBestCameraToTarget().getX());
      //SmartDashboard.putNumber("PhotonY", p_photon.getLatestResult().getBestTarget().getBestCameraToTarget().getY());
      //SmartDashboard.putNumber("Yaw", p_photon.getLatestResult().getBestTarget().getYaw());
      //SmartDashboard.putNumber("rX", Units.radiansToDegrees(p_photon.getLatestResult().getBestTarget().getBestCameraToTarget().getRotation().getX()));

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

  double skewy(PhotonTrackedTarget target) { return Units.radiansToDegrees(target.getBestCameraToTarget().getRotation().getX());}

  //double skewy(PhotonTrackedTarget target) { return Units.radiansToDegrees(target.getBestCameraToTarget().getRotation().getZ());}

  public double getYaw(){

    p_photon.setPipelineIndex(0);

    //System.out.println( "isConnected:" +  p_photon.isConnected());
    
    var result = p_photon.getLatestResult();
       

    double yaw;
    //result = p_photon.getLatestResult();

    boolean hasTargets = result.hasTargets();
    //System.out.println("hasTargets:"+hasTargets);
    if(hasTargets){
      PhotonTrackedTarget target = result.getBestTarget();
      //SmartDashboard.putNumber("skew", skewy(target));
      
      //yaw = skewy(target);
      //SmartDashboard.putNumber("yaw", yaw);
     
      //SmartDashboard.putNumber("pitch", target.getPitch());
      //System.out.println("getYaw:"+target.getYaw());
      //return target.getYaw();
      return Units.radiansToDegrees(target.getBestCameraToTarget().getRotation().getX());
    }
    else{
      //System.out.println("skew:-1");
      //SmartDashboard.putNumber("skew", -1);
      //SmartDashboard.putNumber("yaw", -1);
      //SmartDashboard.putNumber("pitch", -1);
      yaw = 0;
      
    }
    //SmartDashboard.putBoolean("has", hasTargets);
    return 0;
  
    
       

  }

  public boolean getHas(){
    p_photon.setPipelineIndex(0);

    //System.out.println( "isConnected:" +  p_photon.isConnected());
    
    var result = p_photon.getLatestResult();

    boolean oh = result.hasTargets();
    
    
    return oh;
  }
  public double getAmbiguity(){
    var result = p_photon.getLatestResult();
    if(result.hasTargets()){
      PhotonTrackedTarget target = result.getBestTarget();

      return target.getPoseAmbiguity();
    }
    return 0;
  }
  public double getX(){
    
    var result = p_photon.getLatestResult();
    if(result.hasTargets()){
      return result.getBestTarget().getBestCameraToTarget().getX();
    }
    //PhotonTrackedTarget target = result.getBestTarget();

    return 0;
  }
  public double getY(){
    var result = p_photon.getLatestResult();
    if(result.hasTargets()){
      return result.getBestTarget().getBestCameraToTarget().getY();
    }
    //PhotonTrackedTarget target = result.getBestTarget();

    return 0;
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
  //var result;

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Xoff", getX());
    SmartDashboard.putNumber("Yoff", getY());
    //double x = getYaw();
    //SmartDashboard.putNumber("oh", x);


    // This method will be called once per scheduler run


    //result = p_photon.getLatestResult();
    //if(result.hasTargets()){
      
      //SmartDashboard.putNumber("rY", Units.radiansToDegrees(result.getBestTarget().getBestCameraToTarget().getRotation().getY()));
      //SmartDashboard.putNumber("rZ", Units.radiansToDegrees(result.getBestTarget().getBestCameraToTarget().getRotation().getZ()));
    //}
   // else{
      
      //SmartDashboard.putNumber("PhotonY", -1);
   // }
    
    //SmartDashboard.putNumber("PhotonX", result.getBestTarget().getBestCameraToTarget().getX());
    //SmartDashboard.putNumber("PhotonY", result.getBestTarget().getBestCameraToTarget().getY());
    // if(p_photon.getAllUnreadResults().size() > 0){
    //   PhotonTrackedTarget targets = p_photon.getLatestResult().getBestTarget();


    //   Rotation3d_WidgetX.setValue(targets.getBestCameraToTarget().getX());
    //   Rotation3d_WidgetY.setValue(Units.radiansToDegrees(targets.getBestCameraToTarget().getRotation().getX()));
    //   //Rotation3d_WidgetZ.setValue(target.getBestTarget().getBestCameraToTarget().getZ());
    // }
    var results = p_photon.getAllUnreadResults();
        if (!results.isEmpty()) {
            // Camera processed a new frame since last
            // Get the last one in the list.
            var result = results.get(results.size() - 1);
            if (result.hasTargets()) {
              bestTarget = result.getBestTarget();
                // At least one AprilTag was seen by the camera
                int bestTargetID = result.getBestTarget().fiducialId;
                for (PhotonTrackedTarget target : result.getTargets()) {
                  if(target.fiducialId == bestTargetID){
                    Rotation3d_WidgetX.setValue(target.getBestCameraToTarget().getRotation().getX());
                    Rotation3d_WidgetY.setValue(Units.radiansToDegrees(target.getBestCameraToTarget().getRotation().getX()));
                  }
                }
              }
            }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
