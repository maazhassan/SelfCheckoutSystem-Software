package org.lsmr.selfcheckout;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteSlotListener;


public class BanknoteSlotController implements  BanknoteSlotListener {


    @Override
    public void banknoteInserted(BanknoteSlot slot) {
        System.out.println("A " + slot + "hasn been enabled");
    }

    @Override
    public void banknoteEjected(BanknoteSlot slot) {
        System.out.println("A " + slot + "hasn been enabled");
    }

    @Override
    public void banknoteRemoved(BanknoteSlot slot) {
        System.out.println("A " + slot + "hasn been disabled");

    }

    @Override
    public void coinInserted(CoinSlot slot)  {
        System.out.println("A coin was inserted.");
    }

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " has been enabled.");
    }


    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " has been disabled.");
    }
}
