import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BarcodeScannerListener;

public class ScannerController implements BarcodeScannerListener {

    private final ControlSoftware software;

    public ScannerController(ControlSoftware software) {
        this.software = software;
    }

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.print("A " + device.getClass().getSimpleName() + " has been enabled.\n");
    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.print("A " + device.getClass().getSimpleName() + " has been disabled.\n");
    }

    @Override
    public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
        this.software.updatePurchaseList(barcode);
        this.software.increaseTotal(barcode);
    }
}
