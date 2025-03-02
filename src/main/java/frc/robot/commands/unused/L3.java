package frc.robot.commands.unused;

import frc.robot.subsystems.Elevator1;
import frc.robot.subsystems.EndEffector;
import frc.robot.subsystems.PivotArm;
import edu.wpi.first.wpilibj2.command.Command;

public class L3 extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Elevator1 m_elevator;
  private final PivotArm m_PivotArm;
  private final EndEffector m_Effector;
  private boolean hasCoral;

  
  public L3(Elevator1 elevator, PivotArm pivot, EndEffector effector) {
    m_elevator = elevator;
    m_PivotArm = pivot;
    m_Effector = effector;
    
    //declare subsystem dependencies.
    addRequirements(elevator);
    addRequirements(pivot);
    addRequirements(effector);
  }

  @Override
  public void initialize() {
    m_elevator.gotolevel(19);
  }

  @Override
  public void execute() {
    if(m_elevator.getVelocity() < 0.8){
      m_PivotArm.goTo(1.35);
    }
    else{
      m_PivotArm.goTo(2.75);
    }
   
  }

  @Override
  public void end(boolean interrupted) {
    m_Effector.runed(0);
  }

  //hasCoral never changed. runs indefinitely.
  @Override
  public boolean isFinished() {
    return hasCoral;
  }
}

//likely nonfunctional. unsure where called, if anywhere