package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystem.Drivetrain;

import java.util.function.DoubleSupplier;

public class DrivetrainController  extends CommandBase {
    private Drivetrain drivetrain;
    private DoubleSupplier forward;
    private DoubleSupplier lateral;
    private DoubleSupplier heading;
    public DrivetrainController(Drivetrain drivetrain, DoubleSupplier forward, DoubleSupplier lateral, DoubleSupplier heading) {

        this.drivetrain = drivetrain;
        this.forward = forward;
        this.lateral = lateral;
        this.heading = heading;

        addRequirements(drivetrain);
    }
    @Override
    public void initialize(){
        drivetrain.startTeleop();
    }

    @Override
    public void execute (){
        drivetrain.setMovementVectors(forward.getAsDouble(), lateral.getAsDouble(), heading.getAsDouble());
        drivetrain.follower.update();
    }
}
