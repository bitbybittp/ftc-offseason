package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystem.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystem.Elevator;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;
import org.firstinspires.ftc.teamcode.commands.DrivetrainController;
import org.firstinspires.ftc.teamcode.utilities.ButtonBind;

@TeleOp(name = "TestOp")
public class RobotController extends CommandOpMode {
    private Drivetrain drivetrain;
    private Intake intake;
    private Outtake outtake;
    private Elevator elevator;
    private GamepadEx g1;
    private ButtonBind buttonBind;

    long lastTime = 0;

    @Override
    public void initialize() {
        g1 = new GamepadEx(gamepad1);
        drivetrain = new Drivetrain(hardwareMap, telemetry);
        intake = new Intake(hardwareMap, telemetry);
        outtake = new Outtake(hardwareMap, telemetry);
        elevator = new Elevator(hardwareMap, telemetry);
        buttonBind = new ButtonBind(g1,hardwareMap,intake, outtake, elevator);
        register(drivetrain,intake,outtake,elevator);
    }

    public void run() {
        CommandScheduler.getInstance().run();

        long currentTime = System.nanoTime();
        double loopTimeMs = (currentTime - lastTime) / 1_000_000.0;
        lastTime = currentTime;
        telemetry.addData("Loop Time (ms): ", loopTimeMs);
        updateTelemetry(telemetry);
    }
}
