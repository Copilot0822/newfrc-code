// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.commands.ActuateDown;
import frc.robot.commands.ActuateUp;
import frc.robot.commands.ArmDown;
import frc.robot.commands.ArmUp;
import frc.robot.commands.AutoAlignLeft;
import frc.robot.commands.AutoAlignRight;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.Intake;
import frc.robot.commands.L1;
import frc.robot.commands.L2;
import frc.robot.commands.L3;
import frc.robot.commands.L4;
//import frc.robot.commands.L3;
//import frc.robot.commands.L4;
import frc.robot.commands.Outake;
import frc.robot.commands.RunIntake;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.Actuation;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Driving;
import frc.robot.subsystems.Elevator1;
import frc.robot.subsystems.EndEffector;
import frc.robot.subsystems.PhotonVision;
import frc.robot.subsystems.PivotArm;

public class RobotContainer {
    private double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

    /* Setting up bindings for necessary control of the swerve drive platform 
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.15).withRotationalDeadband(MaxAngularRate * 0.15) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
    private final SwerveRequest.RobotCentric forwardStraight = new SwerveRequest.RobotCentric()
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage);*/

    //private final Telemetry logger = new Telemetry(MaxSpeed);

    private final CommandXboxController joystick = new CommandXboxController(0);
   // private final CommandXboxController andrew = new CommandXboxController(1);

  public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

    //private final Arm arm = new Arm();
    //private final Arm1 armed = new Arm1();
    private final Elevator1 elevator = new Elevator1();
    private final PivotArm pivot = new PivotArm();
    private final EndEffector effector = new EndEffector();
    private final Climber climber = new Climber();
    private final Actuation actuation = new Actuation();
    private final Driving driving = new Driving(drivetrain);
    private final PhotonVision photon = new PhotonVision();

    
    

    /* Path follower */
   //private final SendableChooser<Command> autoChooser;

    public RobotContainer() {
        //autoChooser = AutoBuilder.buildAutoChooser("Tests");
        //SmartDashboard.putData("Auto Mode", autoChooser);
        NamedCommands.registerCommand("Leftaim", new AutoAlignLeft(photon, driving));
        NamedCommands.registerCommand("Rightaim", new AutoAlignRight(photon, driving));

        configureBindings();
    }

    private void configureBindings() {

        //andrew.povUp().onTrue(new ElevatorUp(elevator));
        //andrew.povDown().onTrue(new ElevatorDown(elevator));
        //andrew.povRight().onTrue(new ArmUp(pivot));
        //andrew.povLeft().onTrue(new ArmDown(pivot));
        //andrew.povUp().onTrue(new L4(elevator));
        //joystick.povRight().onTrue(new L3(elevator));
        //joystick.povDown().toggleOnTrue(new L2(elevator, pivot, effector));
        //joystick.povLeft().onTrue(new L1(elevator));

        //joystick.povUp().toggleOnTrue(new L4(elevator, pivot, effector));
        //joystick.povRight().toggleOnTrue(new L3(elevator, pivot, effector));
        //joystick.povLeft().toggleOnTrue(new L2(elevator, pivot, effector));
        //joystick.povDown().toggleOnTrue(new L1(elevator));

        //joystick.povDown().onTrue(new Intake(effector, pivot));
        joystick.leftBumper().toggleOnTrue(new AutoAlignLeft(photon, driving));
        joystick.rightBumper().toggleOnTrue(new AutoAlignRight(photon, driving));



        //joystick.b().toggleOnTrue(new Intake(effector, pivot));
        joystick.a().toggleOnTrue(new Outake(effector, pivot));
        joystick.start().toggleOnTrue(new ActuateUp(actuation));
        joystick.back().toggleOnTrue(new ActuateDown(actuation));
        //joystick.povLeft().toggleOnTrue(new RunIntake(effector));
        
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        /*drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
                drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
            )
        );*/

        //joystick.a().whileTrue(drivetrain.applyRequest(() -> brake));
        /*joystick.b().whileTrue(drivetrain.applyRequest(() ->
            point.withModuleDirection(new Rotation2d(-joystick.getLeftY(), -joystick.getLeftX()))
        ));*/

        /*joystick.pov(0).whileTrue(drivetrain.applyRequest(() ->
            forwardStraight.withVelocityX(0.5).withVelocityY(0))
        );
        joystick.pov(0).whileTrue(drivetrain.applyRequest(() ->
            forwardStraight.withVelocityX(-0.5).withVelocityY(0))
        );*/

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
        //joystick.back().and(joystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        //joystick.back().and(joystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        //joystick.start().and(joystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        //joystick.start().and(joystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

        // reset the field-centric heading on left bumper press
        //joystick.y().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));
        

        //drivetrain.registerTelemetry(logger::telemeterize);
    }

    public Command getAutonomousCommand() {
        /* Run the path selected from the auto chooser */
        //return autoChooser.getSelected();
        return null;
    }
}
