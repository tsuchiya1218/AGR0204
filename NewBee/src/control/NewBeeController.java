/**
 * クラス名：	KiddaLaController
 * 概要　　：	KIDDA-LA業務システムコントローラ
 * 作成者名：
 * 作成日　：
 * 修正者名：
 * 修正日　：
 */

package control;

import java.util.ArrayList;



import model.Customer;
import action.BookingCheckAction;
import action.BookingCheckDisplayAction;
import action.CustomerSearchAction;
import action.CustomerSearchDisplayAction;
import action.RoomDisplayAction;
import action.ReviewsCheckDisplayAction;
import action.RoomUpdateAction;
import action.HotelUpdateAction;
import action.HotelDisplayAction;
import action.LiveDisplayAction;
import action.LiveUpdateAction;
import action.MainMenuDisplayAction;
import action.OrderChangeAction;
import action.OrderChangeDisplayAction;
import action.ReviewsCheckAction;
import action.SpotAddAction;
import action.SpotDisplayAction;
import action.SpotUpdateAction;
import action.VehicleAddDisplayAction;
import action.VehicleDisplayAction;
import action.VehicleUpdateDisplayAction;

public class NewBeeController {

	public static void customerSearchDisplay() {
		CustomerSearchDisplayAction action = new CustomerSearchDisplayAction();
		action.execute();
	}

	public static void spotDisplay() {
		SpotDisplayAction action = new SpotDisplayAction();
		action.execute();
	}
	public static void spotAddDisplay() {
		SpotDisplayAction action = new SpotDisplayAction();
		action.executeAdd();
	}
	public static void spotUpdateDisplay() {
		SpotDisplayAction action = new SpotDisplayAction();
		action.executeUpdate();
	}

	public static void liveDisplay() {
		LiveDisplayAction action = new LiveDisplayAction();
		action.execute();
	}
	public static void liveAddDisplay() {
		LiveDisplayAction action = new LiveDisplayAction();
		action.executeAdd();
	}
	public static void liveUpdateDisplay() {
		LiveDisplayAction action = new LiveDisplayAction();
		action.executeUpdata();
	}
	public static String[][] liveSearch(String data) {
		LiveUpdateAction lua = new LiveUpdateAction();
		return lua.execute(data);
	}
	public static String liveAdd(String[] data) {
		LiveUpdateAction lua = new LiveUpdateAction();
		return lua.execute(data);
	}
	
	
	public static void roomAddDisplay() {
		RoomDisplayAction action = new RoomDisplayAction();
		action.executeAdd();
	}
	public static void roomUpdateDisplay() {
		RoomDisplayAction action = new RoomDisplayAction();
		action.executeUpdate();
	}
	public static void roomDisplay() {
		RoomDisplayAction action = new RoomDisplayAction();
		action.execute();
	}
	public static String roomAdd(String[] data) {
		RoomUpdateAction raa = new RoomUpdateAction();
		return raa.execute(data);
	}
	public static String[][] roomSearch(String data) {
		HotelUpdateAction csa = new HotelUpdateAction();
		return csa.execute(data);
	}
	
	
	
	public static void hotelDisplay() {
		HotelDisplayAction action = new HotelDisplayAction();
		action.execute();
	}
	public static void hotelAddDisplay() {
		HotelDisplayAction action = new HotelDisplayAction();
		action.executeAdd();
	}
	public static void hotelUpdateDisplay() {
		HotelDisplayAction action = new HotelDisplayAction();
		action.executeUpdata();
	}

	public static void vehicleDisplay() {
		VehicleDisplayAction action = new VehicleDisplayAction();
		action.execute();
	}
	public static void vehicleAddDisplay() {
		VehicleAddDisplayAction action = new VehicleAddDisplayAction();
		action.execute();
	}
	public static void vehicleUpdateDisplay() {
		VehicleUpdateDisplayAction action = new VehicleUpdateDisplayAction();
		action.execute();
	}

	public static void orderChangeDisplay() {
		OrderChangeDisplayAction action = new OrderChangeDisplayAction();
		action.execute();
	}

	public static void bookingCheckDisplay() {
		BookingCheckDisplayAction action = new BookingCheckDisplayAction();
		action.execute();
	}

	
	
	public static void reviewsCheckDisplay() {
		ReviewsCheckDisplayAction action = new ReviewsCheckDisplayAction();
		action.execute();
	}
	public static String[][] reviewsSearch() {
		ReviewsCheckAction csa = new ReviewsCheckAction();
		return csa.execute();
	}
	public static String reviewsCancelDisplay(String[] data) {
		ReviewsCheckAction csa = new ReviewsCheckAction();
		return csa.execute(data);
	}
	public static String reviewsOkDisplay(String[] data) {
		ReviewsCheckAction csa = new ReviewsCheckAction();
		return csa.executeOk(data);
	}
	
	

	public static String[][] customerSearch(String[] data) {
		CustomerSearchAction csa = new CustomerSearchAction();
		return csa.execute(data);
	}
	
	public static String[][] hotelSearch(String data) {
		HotelUpdateAction csa = new HotelUpdateAction();
		return csa.execute(data);
	}

	public static String spotAdd(String[] data) {
		SpotAddAction taa = new SpotAddAction();
		return taa.execute(data);
	}
	


	public static String[][] spotUpdate(String[] data) {
		SpotUpdateAction taa = new SpotUpdateAction();
		return taa.execute(data);
	}

	

	public static String hotelAdd(String[] data) {
		HotelUpdateAction haa = new HotelUpdateAction();
		return haa.execute(data);
	}

	public static String[][] vehicleAdd(String[] data) {
		CustomerSearchAction csa = new CustomerSearchAction();
		return csa.execute(data);
	}

	public static String[][] orderSearch(String data) {
		OrderChangeAction oca = new OrderChangeAction();
		return oca.execute(data);
	}
	public static String orderChange(String[] data) {
		// TODO 自動生成されたメソッド・スタブ
		OrderChangeAction oca = new OrderChangeAction();
		return oca.execute(data);
	}
	
//	public static String[][] bookingOrder(String data) {
//		CustomerSearchAction csa = new CustomerSearchAction();
//		return csa.execute(data);
//	}

	public static String executeCancel(String[] data) {
		BookingCheckAction bca = new BookingCheckAction();
		return bca.executeCancel(data);
	}

	public static String executeRegister(String[] data) {
		BookingCheckAction bca = new BookingCheckAction();
		return bca.executeRegister(data);
	}
	
	public static String[][] bookingSearch() {
		BookingCheckAction bca = new BookingCheckAction();
		return bca.execute();
	}

	public static String[][] evaluateCheck(String[] data) {
		CustomerSearchAction csa = new CustomerSearchAction();
		return csa.execute(data);
	}

	public static String[][] emptyCheck(String[] data) {
		CustomerSearchAction csa = new CustomerSearchAction();
		return csa.execute(data);
	}

	public static void mainMenuDisplay() {
		MainMenuDisplayAction action = new MainMenuDisplayAction();
		action.execute();
	}

	public static Customer orderInputDisplay(String custId) throws Exception {
		Customer customer = new Customer(9999, "ダミー顧客", "ダミーコキャク", "99999999999", "東京都千代田区神田小川町9-9-9");
		return customer;
	}




	public static int customerModify(Customer customer) throws Exception {
		return 0;
	}


	public static int deliveryComplete(String custId) throws Exception {
		return 0;
	}








}