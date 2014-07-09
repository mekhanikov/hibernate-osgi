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
		System.out.println("*** START");
		DataPoint dp = new DataPoint();
		dp.setName( "name" );
		try {
			dpService.add( dp );
		} catch (Exception e) {
			e.printStackTrace();
		}
		retrieveAll();
		DataPoint dataPointUpdated = dpService.get( Long.valueOf( dp.getId() ) );
		dataPointUpdated.setName( "dataPointUpdated name" );
		dpService.update( dataPointUpdated );
		System.out.println("*** get updated");
		retrieveAll();     

		dpService.deleteAll();

		System.out.println("*** get all, but all is deleted");
		retrieveAll();
		System.out.println("*** END");
	}

	private void retrieveAll() {
		List<DataPoint> dps = dpService.getAll();
		for (DataPoint dp2 : dps) {
			System.out.println(dp2.getId() + ", " + dp2.getName());
		}
	}

}
