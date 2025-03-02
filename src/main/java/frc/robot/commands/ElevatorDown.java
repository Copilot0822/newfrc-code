package frc.robot.commands;

import frc.robot.subsystems.Elevator1;
import edu.wpi.first.wpilibj2.command.Command;


public class ElevatorDown extends Command {
  //unsure if needed! not changing at comp
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Elevator1 m_elevator;


  public ElevatorDown(Elevator1 elevator) {
    //m_subsystem = subsystem;
    m_elevator = elevator;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elevator);
  }

  //calls this elevator command on a move once
  @Override
  public void initialize() {
    m_elevator.gotolevel(m_elevator.getSetPosiiton()-2.5);
  }

  //ends immediately
  @Override
  public boolean isFinished() {
    return true;
  }
}

//unsure why this can't just be a command called from the subsystem :/