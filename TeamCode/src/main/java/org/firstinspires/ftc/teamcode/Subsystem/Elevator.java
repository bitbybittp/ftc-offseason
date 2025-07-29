package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.solversHardware.SolversMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utilities.ElevatorConstants;

public class Elevator extends SubsystemBase {
    public enum ElevatorStates{
    TRANSFER,
    CLEARENCE,
    SPECIMENSCORE,
    SAMPLESCORELOW,
    SAMPLESCOREHIGH
    }
    public SolversMotor liftMotor;
    public Motor.Encoder liftEncoder;
    public ElevatorStates elevatorStates;
    public double target;
    public double power;
    public boolean isReached;
    public PIDFController elevatorController = new PIDFController(ElevatorConstants.p,ElevatorConstants.i,ElevatorConstants.d, ElevatorConstants.f);

    public Elevator (HardwareMap hardwareMap){
        liftMotor = new SolversMotor(hardwareMap.get(DcMotor.class,"viperMotor"),0.01);
        liftEncoder = new Motor(hardwareMap,"viperMotor").encoder;

        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        liftEncoder.reset();
        elevatorController.setTolerance(0.25);
    }
    public ElevatorStates getElevatorStates(){ return elevatorStates;}

    public double getElevatorPosition(){ return getElevatorPosition();}

    public boolean elevatorFinished(){
        isReached = getElevatorPosition()==0 && liftEncoder.getCorrectedVelocity()==0;
        return isReached;
    }

    public void setElevatorTargetPositon(ElevatorStates elevatorStates){
        switch (elevatorStates){
            case TRANSFER:
                target = ElevatorConstants.transferHeight;
            break;
            case SAMPLESCORELOW:
                target = ElevatorConstants.sampleScoreLow;
            break;
            case CLEARENCE:
                target = ElevatorConstants.clearanceHeight;
            break;
            case SPECIMENSCORE:
                target = ElevatorConstants.specimenScore;
            break;
            case SAMPLESCOREHIGH:
                target = ElevatorConstants.sampleScoreHigh;
        }
    }

    public void toPosition(){
         power = elevatorController.calculate(getElevatorPosition(), target);

        if (target==0){
            liftMotor.setPower(0);
        } else {
            liftMotor.setPower(power);
        }
    }

    public void periodic(Telemetry telemetry){
        telemetry.addData("Elevator Position ", getElevatorPosition());
        telemetry.addData("Current Elevator State ", getElevatorStates());
        telemetry.addData("Power Value ",power);
        telemetry.addData("Target Position ", target);

    }


}
