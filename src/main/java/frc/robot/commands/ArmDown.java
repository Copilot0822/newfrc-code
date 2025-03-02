package frc.robot.commands;

import frc.robot.subsystems.PivotArm;
import edu.wpi.first.wpilibj2.command.Command;


public class ArmDown extends Command {

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final PivotArm m_pivot;

  public ArmDown(PivotArm pivot) {
    m_pivot = pivot;

    //subsystem dependencies.
    addRequirements(pivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_pivot.goTo(m_pivot.getPosition()-0.25);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
