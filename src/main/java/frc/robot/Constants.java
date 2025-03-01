// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }


  // Elevator Constants
  public static final int Elevator_Leader_Id = 30;
  public static final int Elevator_Follower_Id = 31;
   
  public static final double MaxMotorPosition = 31.5;
  public static final double MinMotorPosition = 0.5;
  public static final int elevatorLimitSwitchPort = 0;


  // Arm Constants
  public static final int armId = 50;

  // Auto Aim Constants
  public static final double leftOffset = -0.20;
  public static final double leftForwardOffest = 0.3;

  public static final double rightOffset = 0.145;
  public static final double rightForwardOffset = 0.4;

  public static final double poseAmbigMax = 0.5;
  public static final double rotationalrightoffset = 2.3;
  public static final double rotationalleftoffset = -1.25;
  public static final double maxRotationalOutput = 0.1;
  public static final double maxTranslationalOutput = 0.5;
  public static final double modeChangeLimit = 0.01;
  public static final double scaleRotation = 4;
  public static final double scaleTranslation = 4;
  
}
