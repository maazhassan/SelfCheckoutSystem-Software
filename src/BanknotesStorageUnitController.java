package org.lsmr.selfcheckout;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteSlotListener;

abstract class BanknotesStorageUnitController implements org.lsmr.selfcheckout.devices.listeners.BanknoteStorageUnitListener{

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " has been inserted.");
    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " has been ejected.");
    }


    @Override
    public void banknotesFull(BanknoteStorageUnit unit) {
        System.out.println("The banknote bill storage unit is full.");
    }

    @Override
    public void banknoteAdded(BanknoteStorageUnit unit) {
        System.out.println("A banknote bill has been added to the banknote storage unit.");
    }

    @Override
    public void banknotesLoaded(BanknoteStorageUnit unit) {
        System.out.println("Some banknote bills were loaded into the banknote storage unit.");
    }

    @Override
    public void banknotesUnloaded(BanknoteStorageUnit unit)
    {
        System.out.println("The banknote storage unit was emptied.");
    }
}

