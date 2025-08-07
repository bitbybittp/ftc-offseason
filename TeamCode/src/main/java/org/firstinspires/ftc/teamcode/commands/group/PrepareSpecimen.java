package org.firstinspires.ftc.teamcode.commands.group;

import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystem.Elevator;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;
import org.firstinspires.ftc.teamcode.commands.ElevatorController;
import org.firstinspires.ftc.teamcode.commands.OuttakeController;

public class PrepareSpecimen extends SequentialCommandGroup {
    public PrepareSpecimen(Elevator elevator, Outtake outtake){
        setName("PrepareSpecimen");
        addCommands(
                new ElevatorController(elevator, Elevator.ElevatorStates.SPECIMENREADY),
                new OuttakeController(outtake, Outtake.OuttakeStates.SPECIMENSCORE,false)
        );
    }
}
