package org.firstinspires.ftc.teamcode.commands.group;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.Subsystem.Elevator;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;
import org.firstinspires.ftc.teamcode.commands.ElevatorController;
import org.firstinspires.ftc.teamcode.commands.IntakeController;
import org.firstinspires.ftc.teamcode.commands.OuttakeController;

public class RetractAndTransfer extends SequentialCommandGroup {
    public RetractAndTransfer(Intake intake, Elevator elevator, Outtake outtake){
        setName("RetractAndTransfer");
        addCommands(
                new ElevatorController(elevator, Elevator.ElevatorStates.CLEARENCE),
                new IntakeController(intake, Intake.IntakeStates.TRANSFER, false),
                new WaitCommand(1000),
                new ElevatorController(elevator, Elevator.ElevatorStates.TRANSFER),
                new OuttakeController(outtake, Outtake.OuttakeStates.TRANSFER, true),
                new InstantCommand(()-> intake.setClawOpen(true)),
                new WaitCommand(500),
                new InstantCommand(()->outtake.setClawOpen(false))
        );
    }
}
