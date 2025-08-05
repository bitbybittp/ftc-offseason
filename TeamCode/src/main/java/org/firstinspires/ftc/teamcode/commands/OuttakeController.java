package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

public class OuttakeController extends CommandBase {
    private Outtake outtake;
    public boolean clawOpen;
    public Outtake.OuttakeStates outtakeStates;

    public OuttakeController(Outtake outtake, Outtake.OuttakeStates outtakeStates, boolean clawOpen){
        this.outtake = outtake;
        this.outtakeStates = outtakeStates;
        this.clawOpen =clawOpen;

        addRequirements(outtake);

    }
    @Override
    public void initialize(){
        outtake.setOuttakeState(outtakeStates);
        outtake.setClawOpen(clawOpen);
    }

    //public boolean isFinished(){
        //return true;
    //}
}
