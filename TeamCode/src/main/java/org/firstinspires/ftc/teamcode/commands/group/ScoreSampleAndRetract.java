package org.firstinspires.ftc.teamcode.commands.group;

import com.seattlesolvers.solverslib.command.ConditionalCommand;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.PrintCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Subsystem.Elevator;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;
import org.firstinspires.ftc.teamcode.commands.ElevatorController;
import org.firstinspires.ftc.teamcode.commands.OuttakeController;

public class ScoreSampleAndRetract extends SequentialCommandGroup {
    public ScoreSampleAndRetract(Outtake outtake, Elevator elevator){
        setName("ScoreSampleAndRetract");
        addCommands(
                new ParallelCommandGroup(
                        new ElevatorController(elevator, Elevator.ElevatorStates.SAMPLESCOREHIGH),
                        new WaitCommand(1000).andThen(new OuttakeController(outtake, Outtake.OuttakeStates.SAMPLESCORE,false))
                ),
                new WaitCommand(200),
                new InstantCommand(()->outtake.setClawOpen(true)),
                new OuttakeController(outtake, Outtake.OuttakeStates.TRANSFER,true),
                new ElevatorController(elevator, Elevator.ElevatorStates.TRANSFER)
        );
    }
}
