package frc.robot.commands;

import frc.robot.subsystems.EndEffector;
import edu.wpi.first.wpilibj2.command.Command;

public class RunIntake extends Command {
  //unsure if this line is necessary or holdover from template. I suspect latter, but I'm not taking that chance at comp
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final EndEffector m_EndEffector;
  private final double output;


  public RunIntake(EndEffector endEffector, double out) {
    m_EndEffector = endEffector;
    //formerly known as 'in'. i changed that because "in = out" is just plain terrible
    //speed at which end effector is run
    output = out;

    //subsystem dependencies
    addRequirements(endEffector);
  }

  //when button pressed, set speed
  @Override
  public void initialize() {
    m_EndEffector.runed(output);
  }

  //when command interrupted, stop
  @Override
  public void end(boolean interrupted) {
    m_EndEffector.runed(0);
  }

  //this command has no IsFinished method because it's never used
  //it's only called by the operator controller on a WhileTrue
}
