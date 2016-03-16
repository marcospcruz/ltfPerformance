package br.com.marcospcruz.performance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.marcospcruz.performance.model.TransacaoTO;

public interface DaoImpl {

	List<TransacaoTO> populaObjetos(ResultSet rs) throws SQLException;

}
