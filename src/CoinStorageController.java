import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CoinStorageUnitListener;

public class CoinStorageController implements CoinStorageUnitListener {

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " has been enabled.");
    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " has been disabled.");
    }

    @Override
    public void coinsFull(CoinStorageUnit unit) {
        System.out.println("The coin storage unit is full.");
    }

    @Override
    public void coinAdded(CoinStorageUnit unit) {
        System.out.println("A coin has been added to the coin storage unit.");
    }

    @Override
    public void coinsLoaded(CoinStorageUnit unit) {
        System.out.println("Some coins were loaded into the coin storage unit.");
    }

    @Override
    public void coinsUnloaded(CoinStorageUnit unit) {
        System.out.println("The coin storage unit was emptied.");
    }
}
