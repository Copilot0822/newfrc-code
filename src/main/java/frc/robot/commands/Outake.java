package frc.robot.commands;

import frc.robot.subsystems.EndEffector;
import frc.robot.subsystems.PivotArm;

import edu.wpi.first.wpilibj2.command.Command;

public class Outake extends Command {
  //unsure if necessary, not messing with it during comp
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final EndEffector m_EndEffector;
  private final PivotArm m_PivotArm;
  private boolean hasCoral = false;

  private boolean done = false;
  private double time;

 
  public Outake(EndEffector endEffector, PivotArm pivotarm) {
    m_EndEffector = endEffector;
    m_PivotArm = pivotarm;

    //unsure why requirements are commented out?? perhaps caused issues
    //subsystem dependencies.
    //addRequirements(endEffector);
    //addRequirements(pivotarm);
  }

  @Override
  public void initialize() {

    //if backward: run backward, else: run forward
    if(m_PivotArm.getPosition() < -6){
      m_EndEffector.runed(-0.4);
    }

    else{
      m_EndEffector.runed(0.4);
    }

    hasCoral = false;
    done = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //I CANNOT DISCERN HOW THIS WORKS. YET I CAN ONLY ASSUME THAT IT DOES. 
    //TODO: ASK BEN.
    if(!m_EndEffector.getSwitch() && !hasCoral){
      time = System.currentTimeMillis();
      hasCoral = true;
    }

    if(hasCoral && (System.currentTimeMillis()-time > 800)){
      done = true;

    }

    if(Math.abs(m_PivotArm.getPosition() - (m_PivotArm.getRealPostion())) <0.25){
      //m_EndEffector.runed(-0.2);
    }
  }

  //this does nothing. delete?
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_EndEffector.runed(0);
    //m_PivotArm.goTo(2.75);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
