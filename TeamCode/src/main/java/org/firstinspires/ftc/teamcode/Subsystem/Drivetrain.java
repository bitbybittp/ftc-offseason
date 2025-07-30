package org.firstinspires.ftc.teamcode.Subsystem;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.localization.Pose;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.solversHardware.SolversMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
import org.firstinspires.ftc.teamcode.utilities.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {

    private SolversMotor leftFront;
    private SolversMotor leftRear;
    private SolversMotor rightFront;
    private SolversMotor rightRear;

    public Follower follower;
    public Pose startPose;

    private Telemetry telemetry;

    public Drivetrain (HardwareMap hardwareMap, Telemetry telemetry){
        leftFront = new SolversMotor(hardwareMap.get(DcMotor.class, DrivetrainConstants.leftFrontMotorName),0.01);
        rightFront = new SolversMotor(hardwareMap.get(DcMotor.class,DrivetrainConstants.rightFrontMotorName), 0.01);
        leftRear = new SolversMotor(hardwareMap.get(DcMotor.class, DrivetrainConstants.leftRearMotorName), 0.01);
        rightRear = new SolversMotor(hardwareMap.get(DcMotor.class, DrivetrainConstants.rightRearMotorName),0.01);

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        startPose = new Pose(0,0,0);
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);

        this.telemetry = telemetry;
    }
    public void setMovementVectors(double forwardDrive, double lateralDrive, double heading){
        follower.setTeleOpMovementVectors(forwardDrive, lateralDrive, heading, true);
    }
    public void setVectors(double forwardDrive, double lateralDrive, double heading){
        follower.setTeleOpMovementVectors(forwardDrive, lateralDrive, heading, true);
    }

    public void startTeleop(){
        follower.startTeleopDrive();
        follower.update();
    }

    @Override
    public void periodic(){
        telemetry.addData("X Position ",follower.getPose().getX());
        telemetry.addData("Y Position ", follower.getPose().getY());
        telemetry.addData("Heading ", follower.getPose().getHeading());
        follower.update();

    }
}
