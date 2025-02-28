// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Actuation;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ActuateUp extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //private final ExampleSubsystem m_subsystem;
  private final Actuation m_Actuation;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ActuateUp(Actuation actuation) {
    //m_subsystem = subsystem;
    m_Actuation = actuation;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(actuation);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Actuation.runActuator(0.7);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_Actuation.runActuator(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
