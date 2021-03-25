import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CardReaderListener;

public class CardReaderController implements CardReaderListener {

    private final ControlSoftware software;

    /**
     * Creates a controller for card readers.
     * @param software The control software that this controller will be attached to.
     */
    public CardReaderController(ControlSoftware software) {
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
    public void cardInserted(CardReader reader) {
        System.out.println("A card was inserted.");
    }

    @Override
    public void cardRemoved(CardReader reader) {
        System.out.println("A card was removed.");
    }

    @Override
    public void cardTapped(CardReader reader) {
        System.out.println("A card was tapped.");
    }

    @Override
    public void cardSwiped(CardReader reader) {
        System.out.println("A card was swiped.");
    }

    @Override
    public void cardDataRead(CardReader reader, CardData data) {
        software.parseCardData(data);
    }
}
