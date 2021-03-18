import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteValidatorListener;

import java.util.Currency;

public class BanknoteValidatorController implements BanknoteValidatorListener {

    private final ControlSoftware software;

    /**
     * Creates a controller for banknote validators.
     * @param software The control software that this controller will be attached to.
     */

    public BanknoteValidatorController(ControlSoftware software) {this.software = software;}

    @Override
    public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value) {
        software.decreaseTotal(value);
    }

    @Override
    public void invalidBanknoteDetected(BanknoteValidator validator) {
        System.out.print("An invalid banknote bill was inserted.\n");
    }

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device)  {
        System.out.println("A " + device.getClass().getSimpleName() + " has been enabled.");
    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " has been disabled.");
    }
}
