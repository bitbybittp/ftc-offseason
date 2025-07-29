package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystem.Intake;

public class IntakeController extends CommandBase {

    private Intake intake;
    private Intake.IntakeStates intakeStates;
    private boolean clawOpen;

    public IntakeController (Intake intake, Intake.IntakeStates intakeStates,boolean clawOpen){
        this.intake = intake;
        this.intakeStates = intakeStates;
        this.clawOpen = intake.clawOpen;
        addRequirements(intake);
    }

    public void initialize(){
        intake.setIntakeState(intakeStates);
        intake.setClawOpen(clawOpen);
    }

    //public boolean isFinished(){
        //return true;
   // }
}
