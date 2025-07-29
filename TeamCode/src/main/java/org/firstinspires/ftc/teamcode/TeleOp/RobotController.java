package org.firstinspires.ftc.teamcode.TeleOp;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystem.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystem.Elevator;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;
import org.firstinspires.ftc.teamcode.commands.DrivetrainController;
import org.firstinspires.ftc.teamcode.utilities.ButtonBind;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TestOp")
public class RobotController extends CommandOpMode {
    private Drivetrain drivetrain;
    private Elevator elevator;
    private Intake intake;
    private Outtake outtake;
    private ButtonBind buttonBind;
    private GamepadEx g1;

    @Override
    public void initialize() {
        drivetrain = new Drivetrain(hardwareMap);
        elevator = new Elevator(hardwareMap);
        intake = new Intake(hardwareMap);
        outtake = new Outtake(hardwareMap);
        g1 = new GamepadEx(gamepad1);
        buttonBind = new ButtonBind(g1, intake, elevator, outtake);

        drivetrain.setDefaultCommand(new DrivetrainController(
                drivetrain,
                g1 ::getLeftY,
                g1 ::getLeftX,
                g1 ::getRightX
        ));
        register(drivetrain,outtake,elevator,intake);

    }
    public void run(){
        CommandScheduler.getInstance().run();
    }
}
