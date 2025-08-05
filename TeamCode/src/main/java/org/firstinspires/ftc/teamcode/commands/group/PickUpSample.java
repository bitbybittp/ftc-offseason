package org.firstinspires.ftc.teamcode.commands.group;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Subsystem.Elevator;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.commands.IntakeController;

public class PickUpSample extends SequentialCommandGroup {

    public PickUpSample (Intake intake, Elevator elevator){
        setName("PickSample");
        addCommands(
                new IntakeController(intake, Intake.IntakeStates.INTAKEOUT, false),
                new WaitCommand(100),
                new InstantCommand(()-> intake.setClawOpen(false)),
                new IntakeController(intake, Intake.IntakeStates.HOVEROUT, false)
        );
    }
}
