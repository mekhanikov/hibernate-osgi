package com.hibernate.example.nonjpa;

import java.util.List;

import org.osgi.framework.BundleContext;

import com.hibernate.example.nonjpa.entity.DataPoint;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component(immediate=true)
public class Main {

	private DataPointService dpService;

	@Reference
	public void setDpService(DataPointService dpService) {
		this.dpService = dpService;
	}

	@Activate
	void acivate(BundleContext bc) {
		System.out.println("*** put");
		DataPoint dp = new DataPoint();
		dp.setName( "df" );
		System.out.println("*** before add");
		try {
		dpService.add( dp );
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("*** after add");
		//
		
		System.out.println("*** get");
		List<DataPoint> dps = dpService.getAll();
		for (DataPoint dp2 : dps) {
			System.out.println(dp2.getId() + ", " + dp2.getName());
		}
		//    return null;
	}

}
