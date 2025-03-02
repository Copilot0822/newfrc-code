package frc.robot.commands;

import frc.robot.subsystems.Actuation;
import edu.wpi.first.wpilibj2.command.Command;

public class ActuateDown extends Command {
  //unsure if necessary, not messing with it during comp
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Actuation m_Actuation;

  public ActuateDown(Actuation actuation) {
    m_Actuation = actuation;
    
    //subsystem dependencies.
    addRequirements(actuation);
  }

  //run only once in init
  @Override
  public void initialize() {
    m_Actuation.runActuator(0.2);
  }

  //end immediately
  @Override
  public boolean isFinished() {
    return true;
  }
}

//why can't the actuator just run from a subsytem command why do we need all this