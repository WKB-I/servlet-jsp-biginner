package model;

import java.util.List;

public class PostMutterLogic {
	public void exucute(Mutter mutter, List<Mutter> mutterList) {
		mutterList.add(0, mutter); //add for top
	}

}
