package org.firstinspires.ftc.teamcode.utilities;

import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

public class ButtonMap {
    public GamepadButton scoreSample;
    public GamepadButton scoreSpecimen;
    public GamepadButton pickSample;
    public GamepadButton prepareIntake;
    public GamepadButton retractAndTransfer;

    public ButtonMap(GamepadEx g1){
        scoreSample = new GamepadButton(g1, GamepadKeys.Button.LEFT_BUMPER);
        scoreSpecimen = new GamepadButton(g1, GamepadKeys.Button.RIGHT_BUMPER);
        pickSample = new GamepadButton(g1,GamepadKeys.Button.TRIANGLE);
        prepareIntake = new GamepadButton(g1, GamepadKeys.Button.CIRCLE);
        retractAndTransfer = new GamepadButton(g1,GamepadKeys.Button.CROSS);
    }
}
