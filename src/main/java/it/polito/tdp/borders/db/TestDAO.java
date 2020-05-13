package it.polito.tdp.borders.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Border;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();

		Map<Integer,Country> idMap = new HashMap<>();
		List<Country> paesiAnno = new ArrayList<>();
		
		dao.loadAllCountries(idMap);
		
		System.out.println(dao.creaConfini(idMap, paesiAnno, 1918));
	}
}
