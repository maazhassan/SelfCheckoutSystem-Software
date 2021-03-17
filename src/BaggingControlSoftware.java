package org.lsmr.selfcheckout.controlsoftware;

import java.util.Scanner;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class BaggingControlSoftware {
	
	public SelfCheckoutStation s;
	public BaggingController bc;
	
	public BaggingControlSoftware(SelfCheckoutStation a) {
		this.s = a;
		BaggingController e = new BaggingController();
		this.bc = e;
		s.baggingArea.register(bc);
	}
	
	public void place(Item i) {
		
		double w = bc.getWeight();
		double w2 = i.getWeight();
		if(w > s.scale.getWeightLimit()) {
			bc.overload(s.scale);
		}
		
		s.baggingArea.add(i);
		if(bc.getWeight() <= (w + w2)*(s.scale.getSensitivity()/100) || bc.getWeight() >= (w + w2)*(s.scale.getSensitivity())/100) {
			System.out.println("please scan next item");
		}
		else {
			System.out.println("please remove item from bagging area");
		}
		
	}
}
