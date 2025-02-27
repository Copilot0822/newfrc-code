// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Elevator1;
import frc.robot.subsystems.EndEffector;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PivotArm;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

/** An example command that uses an example subsystem. */
public class L4 extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //private final ExampleSubsystem m_subsystem;
  private final Elevator1 m_elevator;
  private final PivotArm m_PivotArm;
  private final EndEffector m_Effector;
  private boolean hasCoral;
  //private final Command outake;
  


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public L4(Elevator1 elevator, PivotArm pivot, EndEffector effector) {
    //m_subsystem = subsystem;
    m_elevator = elevator;
    m_PivotArm = pivot;
    m_Effector = effector;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elevator);
    addRequirements(pivot);
    addRequirements(effector);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_elevator.gotolevel(31.5);
    //m_PivotArm.goTo(2.75);

    //outake = new Outake(m_Effector, m_PivotArm);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_elevator.getVelocity() < 0.5){
      m_PivotArm.goTo(4.5);

    }
    else{
      m_PivotArm.goTo(2.75);
    }

   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Effector.runed(0);
    m_PivotArm.goTo(2.75);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return hasCoral;
  }
}
