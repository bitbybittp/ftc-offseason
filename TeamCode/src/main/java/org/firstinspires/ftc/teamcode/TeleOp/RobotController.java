package org.firstinspires.ftc.teamcode.TeleOp;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystem.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystem.Elevator;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;
import org.firstinspires.ftc.teamcode.commands.DrivetrainController;
import org.firstinspires.ftc.teamcode.commands.group.CheckRobot;
import org.firstinspires.ftc.teamcode.commands.group.PickUpSample;
import org.firstinspires.ftc.teamcode.utilities.ButtonBind;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

@TeleOp(name = "TestOp")
public class RobotController extends CommandOpMode {
    private Drivetrain drivetrain;
    private Intake intake;
    private Outtake outtake;
    private Elevator elevator;
    private GamepadEx g1;
    private Button pickSample;

    long lastTime = 0;

    @Override
    public void initialize() {
        drivetrain = new Drivetrain(hardwareMap, telemetry);
        intake = new Intake(hardwareMap, telemetry);
        outtake = new Outtake(hardwareMap, telemetry);
        elevator = new Elevator(hardwareMap, telemetry);
        g1 = new GamepadEx(gamepad1);
        pickSample = new GamepadButton(g1, GamepadKeys.Button.A);
        pickSample.whenPressed(new CheckRobot(elevator));
        drivetrain.setDefaultCommand(new DrivetrainController(
                drivetrain,
                g1::getLeftY,
                g1::getLeftX,
                g1::getRightX
        ));

        register(drivetrain);
    }

    public void run() {
        CommandScheduler.getInstance().run();

        long currentTime = System.nanoTime();
        double loopTimeMs = (currentTime - lastTime) / 1_000_000.0;
        lastTime = currentTime;
        telemetry.addData("Loop Time (ms): ", loopTimeMs);

        CommandScheduler.getInstance().onCommandInitialize(command->{
            telemetry.addData("Command Works: ", command.getName());
        });

        telemetry.update();
        updateTelemetry(telemetry);
    }
}
