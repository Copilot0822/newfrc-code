package frc.robot;

import java.util.function.BooleanSupplier;

import choreo.auto.AutoFactory;
import choreo.auto.AutoRoutine;
import choreo.auto.AutoTrajectory;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.Intake;
import frc.robot.commands.ElevatorToPosition;

import frc.robot.subsystems.EndEffector;
import frc.robot.subsystems.PivotArm;
import frc.robot.subsystems.Elevator1;

public class AutoRoutines {
    private final AutoFactory m_factory;
    public boolean startLeft;
    private Intake _intake;
    private ElevatorToPosition _level4;

    public AutoRoutines(AutoFactory factory, EndEffector f, PivotArm p, Elevator1 e) {
        m_factory = factory;
        _intake = new Intake(f, p);
        //TODO: double check these numbers w Ben
        _level4 = new ElevatorToPosition(e, p, f, 31.5, -3.1);
    }

    public AutoRoutine testPath() {
        final AutoRoutine routine = m_factory.newRoutine("test path");
        final AutoTrajectory path = routine.trajectory("testPath");
        routine.active().onTrue(
            path.resetOdometry()
            .andThen(path.cmd())
        );
        
        return routine;
    }

    public AutoRoutine basicAuto() {
        final AutoRoutine routine = m_factory.newRoutine("just move");
        final AutoTrajectory path_start = routine.trajectory("null");
        routine.active().onTrue(path_start.resetOdometry().andThen(path_start.cmd()));

        return routine;
    }
    
}
