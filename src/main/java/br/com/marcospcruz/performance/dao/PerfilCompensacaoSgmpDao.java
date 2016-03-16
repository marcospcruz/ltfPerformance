package br.com.marcospcruz.performance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.marcospcruz.performance.model.PerfilCompensacaoSgmpTO;
import br.com.marcospcruz.performance.model.TransacaoTO;
import br.com.marcospcruz.performance.util.ConstantesEnum;
import br.com.marcospcruz.performance.util.Database;

public class PerfilCompensacaoSgmpDao extends Database {

	@Override
	public List<PerfilCompensacaoSgmpTO> readAll(long timeInMillis1,
			long timeInMillis2) throws Exception {

		String query = queriesProperties
				.getProperty(ConstantesEnum.SgmpQueryName.getValue().toString());

		List<PerfilCompensacaoSgmpTO> objetos = null;

		Connection connection = null;

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			connection = getConnection();

			ps = connection.prepareStatement(query);

			java.sql.Date paramDate1 = new java.sql.Date(timeInMillis1);

			java.sql.Date paramDate2 = new java.sql.Date(timeInMillis2);

			ps.setDate(1, paramDate1);

			ps.setDate(2, paramDate2);

			rs = ps.executeQuery();

			objetos = populaObjetos(rs);

		} catch (SQLException e) {

			e.printStackTrace();
			
			throw new Exception("FATAL!");

		} finally {

			close(connection, ps, rs);

		}

		return objetos;

	}

	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<PerfilCompensacaoSgmpTO> populaObjetos(ResultSet rs)
			throws SQLException {
		// TODO Auto-generated method stub

		List<PerfilCompensacaoSgmpTO> objetos = new ArrayList<PerfilCompensacaoSgmpTO>();

		while (rs.next()) {

			PerfilCompensacaoSgmpTO sgmpTrn = new PerfilCompensacaoSgmpTO(
					rs.getTimestamp("data_compensacao"));

			sgmpTrn.setIdClasseContrato(rs.getInt("idclassecontrato"));

			sgmpTrn.setSequencialLote(rs.getInt("sequenciallote"));

			sgmpTrn.setCodigoCompensacao(rs.getInt("idcodigocompensacao"));

			objetos.add(sgmpTrn);

		}

		return objetos;
	}

}
