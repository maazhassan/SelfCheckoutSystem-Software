import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BarcodeScannerListener;

import java.util.ArrayList;
import java.util.List;

public class ScannerListener implements BarcodeScannerListener {

    private List<Barcode> scannedBarcodes = new ArrayList<>();

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {

    }

    @Override
    public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
        scannedBarcodes.add(barcode);
    }

    public List<Barcode> getScannedBarcodes() {
        return scannedBarcodes;
    }

}
