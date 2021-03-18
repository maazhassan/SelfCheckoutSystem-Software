import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteSlotListener;

public class BanknoteSlotController implements  BanknoteSlotListener {

    @Override
    public void banknoteInserted(BanknoteSlot slot) {
        System.out.println("A banknote was inserted.");
    }

    @Override
    public void banknoteEjected(BanknoteSlot slot) {
        System.out.println("A banknote was ejected.");
    }

    @Override
    public void banknoteRemoved(BanknoteSlot slot){
        System.out.println("A banknote was removed.");
    }

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " was enabled.");
    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getSimpleName() + " was disabled.");
    }
}
