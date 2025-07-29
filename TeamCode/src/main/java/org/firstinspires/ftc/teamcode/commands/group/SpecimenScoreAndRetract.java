package org.firstinspires.ftc.teamcode.commands.group;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Subsystem.Elevator;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;
import org.firstinspires.ftc.teamcode.commands.ElevatorController;
import org.firstinspires.ftc.teamcode.commands.OuttakeController;

public class SpecimenScoreAndRetract extends SequentialCommandGroup {
    public SpecimenScoreAndRetract(Outtake outtake, Elevator elevator){
        setName("SpecimenScoreAndRetract");
        addCommands(
                new OuttakeController(outtake, Outtake.OuttakeStates.SPECIMENSCORE,false),
                new ElevatorController(elevator, Elevator.ElevatorStates.SPECIMENSCORE),
                new InstantCommand(()->outtake.setClawOpen(false)).alongWith(new WaitCommand(300)),
                new ElevatorController(elevator, Elevator.ElevatorStates.TRANSFER).alongWith(new InstantCommand(()->outtake.setClawOpen(true)))
        );
    }
}
