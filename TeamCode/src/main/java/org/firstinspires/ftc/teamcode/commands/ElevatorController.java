package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.teamcode.Subsystem.Elevator;

public class ElevatorController extends CommandBase {
    private Elevator elevator;
    private Elevator.ElevatorStates elevatorStates;

    public ElevatorController(Elevator elevator, Elevator.ElevatorStates elevatorStates){
        this.elevator = elevator;
        this.elevatorStates = elevatorStates;
        addRequirements(elevator);
    }

    @Override
    public void initialize(){elevator.setElevatorTargetPositon(elevatorStates);}

    @Override
    public void execute(){
        elevator.toPosition();
    }

    public boolean isFinished(){
        return elevator.elevatorFinished();
   }
}
