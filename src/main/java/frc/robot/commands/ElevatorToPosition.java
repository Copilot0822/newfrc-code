package frc.robot.commands;

import frc.robot.subsystems.Elevator1;
import frc.robot.subsystems.EndEffector;
import frc.robot.subsystems.PivotArm;
import edu.wpi.first.wpilibj2.command.Command;

//the command formerly known as "L2". 
//I have changed it because changing functionality without changing the name is bad practice

public class ElevatorToPosition extends Command {
  //unsure if necessary, not experimenting at a comp
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Elevator1 m_elevator;
  private final PivotArm m_PivotArm;
  private final EndEffector m_Effector; 
  
  private boolean done;
  private double position;
  private double ArmPosition;
  private double time;
  

  public ElevatorToPosition(Elevator1 elevator, PivotArm pivot, EndEffector effector, double Position, double arm) {
    ArmPosition = arm;
    m_elevator = elevator;
    m_PivotArm = pivot;
    m_Effector = effector; //not used, delete maybe
    position = Position;


    //subsystem dependencies.
    addRequirements(elevator);
    addRequirements(pivot);
  }

  //go to position on arm and pivot 
  @Override
  public void initialize() {
    time = System.currentTimeMillis(); 
    //this line was under the constructor, which worked because all commands are made ne w when called in bot container
    //that's not a great way to go about it. I've moved it here
    //the functionality should be the same, but now we can construct and use the same command mutliple times
    //while refreshing the timer

    m_elevator.gotolevel(position);
    m_PivotArm.goTo(ArmPosition);
    done = false;
  }

  //just waiting for a time?? wouldn't it be more efficient to go by elevator pos?? 
  //TODO: ask ben why it's like this
  @Override
  public void execute() {
    //a clever little replacement for a timer. wait until
     if(System.currentTimeMillis()-time > 4000){
      done = true;
     }
  }

  @Override
  public void end(boolean interrupted) {
    //I'm only leaving this print line because it's for the drivers benefit I presume, so they're used to that
    //TODO: change after comp to something less nonsensical
    System.out.println("sigma");
  }

  @Override
  public boolean isFinished() {
    return done;
  }
}
