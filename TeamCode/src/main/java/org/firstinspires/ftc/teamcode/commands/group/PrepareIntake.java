package org.firstinspires.ftc.teamcode.commands.group;

import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Subsystem.Elevator;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.commands.ElevatorController;
import org.firstinspires.ftc.teamcode.commands.IntakeController;

public class PrepareIntake extends SequentialCommandGroup {
     public PrepareIntake(Elevator elevator, Intake intake){
         setName("ReadySample");
         addCommands(
                 new ElevatorController(elevator, Elevator.ElevatorStates.CLEARENCE).alongWith(new IntakeController(intake, Intake.IntakeStates.HOVEROUT, true))
         );
     }

}
