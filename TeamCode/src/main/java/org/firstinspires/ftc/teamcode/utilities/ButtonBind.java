package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystem.Elevator;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;
import org.firstinspires.ftc.teamcode.commands.group.CheckRobot;
import org.firstinspires.ftc.teamcode.commands.group.PickUpSample;
import org.firstinspires.ftc.teamcode.commands.group.PrepareIntake;
import org.firstinspires.ftc.teamcode.commands.group.RetractAndTransfer;
import org.firstinspires.ftc.teamcode.commands.group.ScoreSampleAndRetract;
import org.firstinspires.ftc.teamcode.commands.group.SpecimenScoreAndRetract;

public class ButtonBind{
    private ButtonMap buttonMap;
    public ButtonBind(GamepadEx g1, HardwareMap hardwareMap,Intake intake, Outtake outtake, Elevator elevator){
        buttonMap = new ButtonMap(g1);
        buttonMap.pickSample.whenPressed(new PickUpSample(intake, elevator));
        buttonMap.retractAndTransfer.whenPressed(new RetractAndTransfer(intake, elevator, outtake));
        buttonMap.prepareIntake.whenPressed(new PrepareIntake(elevator, intake));
        buttonMap.scoreSample.whenPressed(new ScoreSampleAndRetract(outtake, elevator));
        buttonMap.scoreSpecimen.whenPressed(new SpecimenScoreAndRetract(outtake, elevator));
        buttonMap.checkRobot.whenPressed(new CheckRobot(elevator));

    }

}
