import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CoinValidatorListener;

import java.math.BigDecimal;

public class CoinValidatorController implements CoinValidatorListener {

    private final ControlSoftware software;

    public CoinValidatorController(ControlSoftware software) {
        this.software = software;
    }

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " has been enabled.");
    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " has been disabled.");
    }

    @Override
    public void validCoinDetected(CoinValidator validator, BigDecimal value) {
        software.decreaseTotal(value);
    }

    @Override
    public void invalidCoinDetected(CoinValidator validator) {
        System.out.println("An invalid coin was inserted.");
    }
}
