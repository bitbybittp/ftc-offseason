package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.solversHardware.SolversServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utilities.IntakeConstants;

public class Intake extends SubsystemBase {
    public enum IntakeStates{
        TRANSFER,
        INTAKEIN,
        INTAKEOUT,
        HOVERIN,
        HOVEROUT
    }
    private SolversServo linkage;
    private SolversServo linkage2;
    private SolversServo intakeArmRight;
    private SolversServo intakeArmleft;
    private SolversServo intakeWrist;
    private SolversServo intakeClaw;
    public  boolean clawOpen;
    public IntakeStates intakeStates;

    private Telemetry telemetry;

    public Intake (HardwareMap hardwareMap, Telemetry telemetry){
        linkage = new SolversServo(hardwareMap.get(Servo.class, "linkageServo"),0.01);
        linkage2 = new SolversServo(hardwareMap.get(Servo.class, "linkageServo2"),0.01);
        intakeArmleft = new SolversServo(hardwareMap.get(Servo.class, "leftPivotingServo"),0.01);
        intakeArmRight = new SolversServo(hardwareMap.get(Servo.class, "rightPivotingServo"),0.01);
        intakeWrist = new SolversServo(hardwareMap.get(Servo.class, "intakeArm"),0.01);
        intakeClaw = new SolversServo(hardwareMap.get(Servo.class, "intakeClaw"),0.01);

        linkage.setDirection(Servo.Direction.FORWARD);
        linkage2.setDirection(Servo.Direction.REVERSE);
        intakeArmleft.setDirection(Servo.Direction.REVERSE);
        intakeArmRight.setDirection(Servo.Direction.FORWARD);
        intakeWrist.setDirection(Servo.Direction.FORWARD);
        intakeClaw.setDirection(Servo.Direction.FORWARD);

        intakeStates = IntakeStates.TRANSFER;

        setIntakeState(IntakeStates.TRANSFER);

        setClawOpen(true);

        this.telemetry = telemetry;
    }

    public IntakeStates getIntakeStates() {
        return intakeStates;
    }
    public double getWristPosition (){
        return intakeWrist.getPosition();
    }
    public double getArmPosition(){
        return intakeArmRight.getPosition();
    }
    public void setClawOpen(boolean open){
        if(open){
            intakeClaw.setPosition(0.25);
        } else {
            intakeClaw.setPosition(0.0);
        }
        this.clawOpen = open;
    }
    public boolean clawOpen(){
        return clawOpen;
    }

    public void setIntakeState(IntakeStates intakeStates){
        switch(intakeStates){
            case HOVERIN:
                linkage.setPosition(IntakeConstants.linkageIn);
                linkage2.setPosition(IntakeConstants.linkageIn);
                intakeArmleft.setPosition(IntakeConstants.hover);
                intakeArmRight.setPosition(IntakeConstants.hover);
                intakeWrist.setPosition(IntakeConstants.deg180);
                break;
            case INTAKEIN:
                linkage.setPosition(IntakeConstants.linkageIn);
                linkage2.setPosition(IntakeConstants.linkageIn);
                intakeArmleft.setPosition(IntakeConstants.pickUp);
                intakeArmRight.setPosition(IntakeConstants.pickUp);
                intakeWrist.setPosition(IntakeConstants.deg180);
                break;
            case TRANSFER:
                linkage.setPosition(IntakeConstants.linkageIn);
                linkage2.setPosition(IntakeConstants.linkageIn);
                intakeArmleft.setPosition(IntakeConstants.intakeArmtransfer);
                intakeArmRight.setPosition(IntakeConstants.intakeArmtransfer);
                intakeWrist.setPosition(IntakeConstants.deg180);
                break;
            case HOVEROUT:
                linkage.setPosition(IntakeConstants.linkageOut);
                linkage2.setPosition(IntakeConstants.linkageOut);
                intakeArmleft.setPosition(IntakeConstants.hover);
                intakeArmRight.setPosition(IntakeConstants.hover);
                intakeWrist.setPosition(IntakeConstants.deg180);
                break;
            case INTAKEOUT:
                linkage.setPosition(IntakeConstants.linkageOut);
                linkage2.setPosition(IntakeConstants.linkageOut);
                intakeArmleft.setPosition(IntakeConstants.pickUp);
                intakeArmRight.setPosition(IntakeConstants.pickUp);
                intakeWrist.setPosition(IntakeConstants.deg180);
                break;
        }
    }

    @Override
    public void periodic (){
        telemetry.addData("Intake State ", getIntakeStates().toString());
        telemetry.addData("claw ", clawOpen());

    }

}
