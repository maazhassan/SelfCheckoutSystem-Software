package org.lsmr.selfcheckout.controlsoftware;

import java.util.Scanner;

import org.junit.validator.PublicClassValidator;
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
		if(w < 0) {System.out.println("weight can't negative");return;}
		
		s.baggingArea.add(i);
		if(bc.getWeight() <= (w + w2)*(s.scale.getSensitivity()/100) || bc.getWeight() >= (w + w2)*(s.scale.getSensitivity())/100) {
			System.out.println("please scan next item");
		}
		else {
			System.out.println("please remove item from bagging area");
		}
		
	}
	public void remove(Item i) { //remove was added, however it was not a requirement in the assignment, so testing will not be done for remove
		s.baggingArea.remove(i);
		System.out.println("item was removed");
	}
}
