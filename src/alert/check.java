package alert;

import java.util.List;

import models.friends;

public class check {
	public List<friends> listMF;
	public List<friends> listRF;
	
	public check(List<friends> l1, List<friends> l2) {
		listMF = l1;
		listRF = l2;
	}
	
	public int checkInMF(int user_id, int id) {
		if (listMF == null) {
			return 1;
		}
		else {
			for (int i = 0; i < listMF.size(); i++) {
				if (user_id == listMF.get(i).getU1() && id == listMF.get(i).getU2()) {
					return 0;
				}
				if (user_id == listMF.get(i).getU2() && id == listMF.get(i).getU1()) {
					return 0;
				}
			}
			return 1;
		}
	}
	
	public int checkInRF(int user_id, int id) {
		if (listRF == null) {
			return 1;
		}
		else {
			for (int i = 0; i < listRF.size(); i++) {
				if (user_id == listRF.get(i).getU1() && id == listRF.get(i).getU2()) {
					return 0;
				}
				if (user_id == listRF.get(i).getU2() && id == listRF.get(i).getU1()) {
					return 0;
				}
			}
			return 1;
		}
	}
	
	public int checkSendAndAccept(int user_id, int id) {
		if (listRF == null) {
			return 1;
		}
		else {
			for (int i = 0; i < listRF.size(); i++) {
				// send
				if (user_id == listRF.get(i).getU1() && id == listRF.get(i).getU2()) {
					return 2;
				}
				// accept
				if (user_id == listRF.get(i).getU2() && id == listRF.get(i).getU1()) {
					return -2;
				}
			}
			return 1;
		}
	}
}
