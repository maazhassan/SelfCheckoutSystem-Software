import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class BaggingController implements ElectronicScaleListener{
		
	private final ControlSoftware software;

	public BaggingController(ControlSoftware software) {
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
	public void weightChanged(ElectronicScale scale, double weightInGrams) {
		this.software.setBaggingAreaWeight(weightInGrams);
	}

	@Override
	public void overload(ElectronicScale scale) {
		System.out.print("Please remove item from scale.\n");
		
	}

	@Override
	public void outOfOverload(ElectronicScale scale) {
		System.out.print("Please continue to scan items.");
		
	}

}
