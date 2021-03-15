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
        System.out.println("A " + device.getClass().getName() + " has been enabled.");
    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
        System.out.println("A " + device.getClass().getName() + " has been disabled.");
    }

    @Override
    public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
        this.software.updatePurchaseList(barcode);
    }
}
