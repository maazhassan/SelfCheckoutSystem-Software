package org.lsmr.selfcheckout.controlsoftware;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class BaggingController implements ElectronicScaleListener{
		
	public double weight;
	public int counter;
	
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		System.out.println("A " + device.getClass().getSimpleName() + " has been enabled.");
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		System.out.println("A " + device.getClass().getSimpleName() + " has been disabled.");
		
	}

	@Override
	public void weightChanged(ElectronicScale scale, double weightInGrams) {
		double change = weightInGrams- weight;
		System.out.println("Item was added with weight:" + weightInGrams);
		
		weight = weightInGrams;
		counter ++;
		
	}

	@Override
	public void overload(ElectronicScale scale) {
		System.out.println("Please remove item from scale");
		
	}

	@Override
	public void outOfOverload(ElectronicScale scale) {
		System.out.println("please continue to scan items");
		
	}

}
