// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Elevator1;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PivotArm;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class L1 extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //private final ExampleSubsystem m_subsystem;
  private final Elevator1 m_elevator;
  private final PivotArm m_PivotArm;
  
  private boolean done;



  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public L1(Elevator1 elevator, PivotArm pivotArm) {
    //m_subsystem = subsystem;
    m_elevator = elevator;
    m_PivotArm = pivotArm;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elevator);
    addRequirements(pivotArm);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //m_elevator.gotolevel(2);
    m_elevator.gotolevel(1.55);
    done = false;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_elevator.getRealPostion()<2 && !done){
      m_PivotArm.goTo(-6.7);
      done = true;

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_PivotArm.goTo(3.125);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
