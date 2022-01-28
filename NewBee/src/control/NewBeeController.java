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
import model.OrderDetail;
import view.DeliveryConfirmFrame;
import action.BookingCheckAction;
import action.BookingCheckDisplayAction;
import action.CustomerSearchAction;
import action.CustomerSearchDisplayAction;
import action.EmptyCheckDisplayAction;
import action.ReviewsCheckDisplayAction;
import action.HoteAddDisplayAction;
import action.HotelDisplayAction;
import action.HotelUpdateDisplayAction;
import action.LiveAddDisplayAction;
import action.LiveDisplayAction;
import action.LiveUpdateDisplayAction;
import action.MainMenuDisplayAction;
import action.OrderChangeAction;
import action.OrderChangeDisplayAction;
import action.PrintOutAction;
import action.SpotAddAction;
import action.SpotAddDisplayAction;
import action.SpotDisplayAction;
import action.SpotUpdateAction;
import action.SpotUpdateDisplayAction;
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
		SpotAddDisplayAction action = new SpotAddDisplayAction();
		action.execute();
	}

	public static void spotUpdateDisplay() {
		SpotUpdateDisplayAction action = new SpotUpdateDisplayAction();
		action.execute();
	}

	public static void liveDisplay() {
		LiveDisplayAction action = new LiveDisplayAction();
		action.execute();
	}

	public static void liveAddDisplay() {
		LiveAddDisplayAction action = new LiveAddDisplayAction();
		action.execute();
	}

	public static void liveUpdateDisplay() {
		LiveUpdateDisplayAction action = new LiveUpdateDisplayAction();
		action.execute();
	}

	public static void hotelDisplay() {
		HotelDisplayAction action = new HotelDisplayAction();
		action.execute();
	}

	public static void hotelAddDisplay() {
		HoteAddDisplayAction action = new HoteAddDisplayAction();
		action.execute();
	}

	public static void hotelUpdateDisplay() {
		HotelUpdateDisplayAction action = new HotelUpdateDisplayAction();
		action.execute();
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

	public static void emptyCheckDisplay() {
		EmptyCheckDisplayAction action = new EmptyCheckDisplayAction();
		action.execute();
	}

	public static String[][] customerSearch(String[] data) {
		CustomerSearchAction csa = new CustomerSearchAction();
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

	public static String[][] liveAdd(String[] data) {
		CustomerSearchAction csa = new CustomerSearchAction();
		return csa.execute(data);
	}

	public static String[][] hotelAdd(String[] data) {
		CustomerSearchAction csa = new CustomerSearchAction();
		return csa.execute(data);
	}

	public static String[][] vehicleAdd(String[] data) {
		CustomerSearchAction csa = new CustomerSearchAction();
		return csa.execute(data);
	}

	public static String[][] orderChange(String[] data) {
		OrderChangeAction oca = new OrderChangeAction();
		return oca.execute(data);
	}

//	public static String[][] bookingOrder(String data) {
//		CustomerSearchAction csa = new CustomerSearchAction();
//		return csa.execute(data);
//	}

	public static String bookingCheck(String data) {
		BookingCheckAction csa = new BookingCheckAction();
		return csa.execute(data);
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

	public static String[][] itemMenuDisplay() throws Exception {
		return null;
	}

	public static ArrayList<OrderDetail> deliveryConfirm(String custId) throws Exception {
		return null;
	}

	public static ArrayList<OrderDetail> orderRegister(ArrayList<OrderDetail> orderDetailList) throws Exception {
		return null;
	}

	public static int customerModify(Customer customer) throws Exception {
		return 0;
	}

	public static void printOut(DeliveryConfirmFrame frame) throws Exception {
		PrintOutAction action = new PrintOutAction();
		action.execute(frame);
	}

	public static int deliveryComplete(String custId) throws Exception {
		return 0;
	}

}