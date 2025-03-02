// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Elevator1;
import frc.robot.subsystems.PivotArm;
import edu.wpi.first.wpilibj2.command.Command;

public class L1 extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Elevator1 m_elevator;
  private final PivotArm m_PivotArm;
  
  private boolean done;


  public L1(Elevator1 elevator, PivotArm pivotArm) {
    m_elevator = elevator;
    m_PivotArm = pivotArm;

    //subsystem dependencies.
    addRequirements(elevator);
    addRequirements(pivotArm);

  }

  @Override
  public void initialize() {
    m_elevator.gotolevel(1);
    done = false;

  }

  @Override
  public void execute() {
    //if elevator at position and not marked done, mark done and move arm
    if(m_elevator.getRealPostion()<5 && !done){
      m_PivotArm.goTo(-6.5);
      done = true;
    }
  }

  //no end action, no override of super end command needed

  @Override
  public boolean isFinished() {
    return done;
  }
}
