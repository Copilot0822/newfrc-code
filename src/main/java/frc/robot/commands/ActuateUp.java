package frc.robot.commands;

import frc.robot.subsystems.Actuation;
import edu.wpi.first.wpilibj2.command.Command;


public class ActuateUp extends Command {
  //unsure if needed, don't change at comp
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final Actuation m_Actuation;

  
  public ActuateUp(Actuation actuation) {
    m_Actuation = actuation;

    //subsystem dependencies.
    addRequirements(actuation);
  }

  //called in init so it only runs once
  @Override
  public void initialize() {
    m_Actuation.runActuator(0.7);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
