package action;

import dao.VehicleAddDBAccess;

public class VehicleUpdateAction {
	VehicleAddDBAccess vehicleDBA = new VehicleAddDBAccess();
	public String execute(String[] data) {
			return vehicleDBA.vehicleAdd(data);
	}
	
	public String executeUpdate(String[] data) {
		return vehicleDBA.vehicleUpdate(data);
}

	
	public String[][] execute(String data) {
			return vehicleDBA.vehicleSearch(data);
	}
}
