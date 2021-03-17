import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinTray;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CoinTrayListener;

public class CoinTrayController implements CoinTrayListener {
    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " has been enabled.");
    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " has been disabled.");
    }

    @Override
    public void coinAdded(CoinTray tray) {
        System.out.print("A coin was added to the coin tray.\n");
    }
}
