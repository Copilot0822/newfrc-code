package frc.robot.commands;

import frc.robot.subsystems.PivotArm;
import edu.wpi.first.wpilibj2.command.Command;

public class ArmUp extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final PivotArm m_pivot;

  public ArmUp(PivotArm pivot) {
    m_pivot = pivot;

    //subsystem dependencies.
    addRequirements(pivot);
  }

  //sets pivot arm
  @Override
  public void initialize() {
    m_pivot.goTo(m_pivot.getPosition()+0.25);
  }

  // ends immediately
  @Override
  public boolean isFinished() {
    return true;
  }
}
