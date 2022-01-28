
package model;

import java.net.URL;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OrderControlUtility {

	public static String[][] customerToArray(ArrayList<Customer> list) {

		int listSize = list.size();
		String[][] tableData = new String[listSize][4];

		for (int i = 0; i < listSize; i++) {

			Customer customer = list.get(i);
			tableData[i][0] = Integer.toString(customer.getCustId());
			tableData[i][1] = customer.getCustName();
			tableData[i][2] = customer.getKana();
			tableData[i][3] = customer.getAddress();
		}

		return tableData;
	}

	public static String[][] spotToArray(ArrayList<Spot> list) {

		int listSize = list.size();
		String[][] tableData = new String[listSize][4];

		for (int i = 0; i < listSize; i++) {

			Spot spot = list.get(i);
			tableData[i][0] = Integer.toString(spot.getCustId());
			tableData[i][1] = spot.getCustName();
			tableData[i][2] = spot.getKana();
			tableData[i][3] = spot.getAddress();
			tableData[i][4] = spot.getTime();
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
