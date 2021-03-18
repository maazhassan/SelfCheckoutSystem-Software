package org.lsmr.selfcheckout;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteValidatorListener;

import java.util.Currency;

public class BanknoteValidatorController<ControlSoftware> implements BanknoteValidatorListener {

    private final ControlSoftware software;

    /**
     * Creates a controller for coin validators.
     * @param software The control software that this controller will be attached to.
     */

    public BanknoteValidatorController(ControlSoftware software) {this.software = software;}

    @Override
    public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value) {
        software.decreaseTotal(value);
    }

    @Override
    public void invalidBanknoteDetected(BanknoteValidator validator) {
        System.out.println("An invalid banknote bill was inserted.");
    }

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device)  {
        System.out.println("A " + device.getClass().getSimpleName() + " has been inserted.");
    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " has been inserted.");
    }
}
