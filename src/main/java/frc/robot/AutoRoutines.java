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
import frc.robot.commands.L1;
import frc.robot.commands.AutoAlignLeft;
import frc.robot.commands.AutoAlignRight;

import frc.robot.subsystems.EndEffector;
import frc.robot.subsystems.PivotArm;
import frc.robot.subsystems.Elevator1;
import frc.robot.subsystems.Driving;
import frc.robot.subsystems.PhotonVision;

public class AutoRoutines {
    private final AutoFactory m_factory;
    public boolean startLeft;
    private Intake _intake;
    private ElevatorToPosition _level4;
    private L1 _level1;
    private AutoAlignLeft _alignLeft;
    private AutoAlignRight _alignRight;



    public AutoRoutines(AutoFactory factory, EndEffector f, PivotArm p, Elevator1 e, Driving d, PhotonVision v) {
        m_factory = factory;
        _intake = new Intake(f, p);
        //TODO: double check these numbers w Ben
        _level4 = new ElevatorToPosition(e, p, f, 31.5, -3.1);
        _level1 = new L1(e, p);
        _alignLeft = new AutoAlignLeft(v, d);
        _alignRight = new AutoAlignRight(v, d);
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
        final AutoTrajectory path_start = routine.trajectory("startRto2");
        routine.active().onTrue(path_start.resetOdometry().andThen(path_start.cmd()));

        return routine;
    }

    public AutoRoutine autoLeft() {
        final AutoRoutine routine = m_factory.newRoutine("left auto");
        final AutoTrajectory path_start = routine.trajectory("startLto6");
        final AutoTrajectory feeder_6 = routine.trajectory("6toL");
        final AutoTrajectory reef_5 = routine.trajectory("Lto5");

        routine.active().onTrue(
            path_start.resetOdometry()
            .andThen(path_start.cmd())
            .andThen(_alignLeft) //NOTE: This may throw off the Choreo alignment. but I think it's worth at least making one point
            .andThen(_level4)
            .andThen(_intake) //TODO: is that the right command?? i really don't think it is
            .andThen(_level1)
            .andThen(feeder_6.cmd())
            .andThen(_intake)
            .andThen(reef_5.cmd())
            .andThen(_alignLeft) //see line 77 comment
            .andThen(_level4)
            .andThen(_intake)
            .andThen(_level1)
        );

        return routine;
    }

    public AutoRoutine autoRight() {
        final AutoRoutine routine = m_factory.newRoutine("right auto");
        final AutoTrajectory path_start = routine.trajectory("startRto2");
        final AutoTrajectory feeder_2 = routine.trajectory("2toR");
        final AutoTrajectory reef_3 = routine.trajectory("Rto3");
 
        routine.active().onTrue(
            path_start.resetOdometry()
            .andThen(path_start.cmd())
            .andThen(_alignRight) //see line 77 comment
            .andThen(_level4)
            .andThen(_intake) //TODO: is that the right command??
            .andThen(_level1)
            .andThen(feeder_2.cmd())
            .andThen(_intake)
            .andThen(reef_3.cmd())
            .andThen(_alignLeft) //see line 77 comment
            .andThen(_level4)
            .andThen(_intake)
            .andThen(_level1)
        );

        return routine;
    }
    
}
