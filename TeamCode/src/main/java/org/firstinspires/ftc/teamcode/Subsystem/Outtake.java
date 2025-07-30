package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.solversHardware.SolversServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utilities.OuttakeConstants;

public class Outtake extends SubsystemBase {

    public enum OuttakeStates {
        TRANSFER,
        SPECIMENSCORE,
        SAMPLESCORE,
        SPECIMENPICKUP

    }

    private SolversServo outtakeLeftArmPivot;
    private SolversServo outtakeRightArmPivot;
    private SolversServo outtakeWrist;
    private SolversServo outtakeClaw;

    public OuttakeStates outtakeStates;
    public boolean outtakeClawOpen;

    private Telemetry telemetry;

    public Outtake (HardwareMap hardwareMap, Telemetry telemetry){
        outtakeLeftArmPivot = new SolversServo(hardwareMap.get(Servo.class,"leftouttakeArm"),0.01);
        outtakeRightArmPivot = new SolversServo(hardwareMap.get(Servo.class, "rightouttakeArm"), 0.01);
        outtakeWrist = new SolversServo(hardwareMap.get(Servo.class,"outtakeWrist"),0.01);
        outtakeClaw = new SolversServo(hardwareMap.get(Servo.class,"outtakeClaw"),0.01);

        outtakeLeftArmPivot.setDirection(Servo.Direction.REVERSE);
        outtakeRightArmPivot.setDirection(Servo.Direction.FORWARD);
        outtakeWrist.setDirection(Servo.Direction.FORWARD);
        outtakeClaw.setDirection(Servo.Direction.REVERSE);

        setOuttakeStates(OuttakeStates.TRANSFER);

        this.telemetry = telemetry;
    }
    public OuttakeStates getOuttakeStates(){ return outtakeStates;}

    public double getArmPosition(){ return outtakeRightArmPivot.getPosition();}
    public double getWristPosition(){ return outtakeWrist.getPosition();}

    public void setClawOpen (boolean open){
        if (open){
            outtakeClaw.setPosition(0.25);
        } else {
            outtakeClaw.setPosition(0.00);
        }
        this.outtakeClawOpen = open;
    }
    public boolean isClawOpen (){return outtakeClawOpen;}

    public void setOuttakeStates(OuttakeStates outtakeStates){
        switch (outtakeStates){
            case TRANSFER:
                outtakeLeftArmPivot.setPosition(OuttakeConstants.pivotArmTransfer);
                outtakeRightArmPivot.setPosition(OuttakeConstants.pivotArmTransfer);
                outtakeWrist.setPosition(OuttakeConstants.wristTransfer);
                setClawOpen(true);
                break;
            case SPECIMENSCORE:
                outtakeRightArmPivot.setPosition(OuttakeConstants.pivotArmSpecimenScore);
                outtakeLeftArmPivot.setPosition(OuttakeConstants.pivotArmSpecimenScore);
                outtakeWrist.setPosition(OuttakeConstants.wristSpecimenScore);
                setClawOpen(true);
                break;
            case SAMPLESCORE:
                outtakeLeftArmPivot.setPosition(OuttakeConstants.pivotArmSampleSCore);
                outtakeRightArmPivot.setPosition(OuttakeConstants.pivotArmSampleSCore);
                outtakeWrist.setPosition(OuttakeConstants.wristSampleScore);
                setClawOpen(true);
                break;
            case SPECIMENPICKUP:
                outtakeLeftArmPivot.setPosition(OuttakeConstants.pivotArmSpecimenPickUp);
                outtakeRightArmPivot.setPosition(OuttakeConstants.pivotArmSpecimenPickUp);
                outtakeWrist.setPosition(OuttakeConstants.wristSpecimenPickUp);
                setClawOpen(true);
                break;

        }

    }

    @Override
    public void periodic (){
        telemetry.addData("Outtake State ", getOuttakeStates());
        telemetry.addData("Outtake Claw ", isClawOpen());

    }


}
