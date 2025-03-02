package frc.robot.commands.unused;

import frc.robot.subsystems.Elevator1;
import frc.robot.subsystems.EndEffector;
import frc.robot.subsystems.PivotArm;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class L4 extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Elevator1 m_elevator;
  private final PivotArm m_PivotArm;
  private final EndEffector m_Effector;
  private boolean hasCoral;
  
  public L4(Elevator1 elevator, PivotArm pivot, EndEffector effector) {
    m_elevator = elevator;
    m_PivotArm = pivot;
    m_Effector = effector;

    //subsystem dependencies.
    addRequirements(elevator);
    addRequirements(pivot);
    addRequirements(effector);
  }

  @Override
  public void initialize() {
    m_elevator.gotolevel(31.5);
  }

  @Override
  public void execute() {

    //get... velocity? i presume to prevent overshooting, but a weird method
    if(m_elevator.getVelocity() < 0.5){
      m_PivotArm.goTo(4.5);
    }
    else{
      m_PivotArm.goTo(2.75);
    }

   
  }

  @Override
  public void end(boolean interrupted) {
    m_Effector.runed(0);
    m_PivotArm.goTo(2.75);
  }

  //note: has coral is never changed anywhere, and this command would run indefinitely or until interrupted if called
  @Override
  public boolean isFinished() {
    return hasCoral;
  }
}

//appears to be nonfunctional and unused. if okay, delete