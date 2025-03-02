package frc.robot.commands;

import frc.robot.subsystems.EndEffector;
import frc.robot.subsystems.PivotArm;
import edu.wpi.first.wpilibj2.command.Command;

public class Intake extends Command {
  //probably not necessary, not touching at a comp
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final EndEffector m_EndEffector;
  private final PivotArm m_Arm;
  private boolean hasCoral = false;

  public Intake(EndEffector endEffector, PivotArm pivotArm) {
    m_EndEffector = endEffector;
    m_Arm = pivotArm; //arm never used. why is it even in the constructor still
    //subsystem dependencies.
    addRequirements(endEffector);
  }

  //run end effector, reset variable
  @Override
  public void initialize() {
    m_EndEffector.runed(-0.3);
    hasCoral = false;
  }

  @Override
  public void execute() {
    //if dectecting a coral, set end variable to true
    if(m_EndEffector.getSwitch()){
      hasCoral = true;
    }
  }

  //when command ends, stop the end effector
  @Override
  public void end(boolean interrupted) {
    m_EndEffector.runed(0);
  }

  //end when we have a coral
  @Override
  public boolean isFinished() {
    return hasCoral;
  }
}
