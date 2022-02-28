
package model;

import java.net.URL;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControlUtility {

	public static String[][] customerToArray(ArrayList<Customer> list) {

		int listSize = list.size();
		String[][] tableData = new String[listSize][5];

		for (int i = 0; i < listSize; i++) {

			Customer customer = list.get(i);
			tableData[i][0] = customer.getCustomerid();
			tableData[i][1] = customer.getName();
			tableData[i][2] = customer.getEmail();
			tableData[i][3] = customer.getTel();
			tableData[i][4] = customer.getAddress();
		}

		return tableData;
	}

	public static String[][] reviewsToArray(ArrayList<Reviews> list) {

		int listSize = list.size();
		String[][] tableData = new String[listSize][5];

		for (int i = 0; i < listSize; i++) {

			Reviews reviews = list.get(i);
			tableData[i][0] = reviews.getCustomerId();
			tableData[i][1] = reviews.getCustomerName();
			tableData[i][2] = reviews.getOrderId();
			tableData[i][3] = reviews.getReview();
			tableData[i][4] = reviews.getDate();
		}

		return tableData;
	}

	public static String[][] spotToArray(ArrayList<Spot> list) {

		int listSize = list.size();
		String[][] tableData = new String[listSize][7];

		for (int i = 0; i < listSize; i++) {

			Spot spot = list.get(i);
			tableData[i][0] = spot.getSpotId();
			tableData[i][1] = spot.getsName();
			tableData[i][2] = spot.getAddress();
			tableData[i][3] = spot.getAccess();
			tableData[i][4] = spot.getTime();
			tableData[i][5] = spot.getComment();
			tableData[i][6] = spot.getImgPath();
		}


		return tableData;
	}


	public static void systemErrorMessage(JFrame frame, Exception e) {

		JOptionPane.showMessageDialog(frame, e + "：管理者に連絡してください。", "【システムエラー】", JOptionPane.ERROR_MESSAGE);
	}

	public static void setIconImage(JFrame frame) {

		URL imgResource = frame.getClass().getClassLoader().getResource("");
		ImageIcon icon = new ImageIcon(imgResource);
		frame.setIconImage(icon.getImage());
	}
}
