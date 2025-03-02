package frc.robot.commands;

import frc.robot.subsystems.Elevator1;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorUp extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Elevator1 m_elevator;

  public ElevatorUp(Elevator1 elevator) {
    m_elevator = elevator;

    //subsystem dependencies.
    addRequirements(elevator);
  }

  //called in init so it only runs once
  @Override
  public void initialize() {
    m_elevator.gotolevel(m_elevator.getSetPosiiton()+2.5);
  }

  //ends immediately
  @Override
  public boolean isFinished() {
    return true;
  }
}

//hey. why can't we just call this as a command right from the subsystem on this one?
//this is unnecessary, strange, and complicated