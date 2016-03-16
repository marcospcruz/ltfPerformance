package br.com.marcospcruz.performance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.marcospcruz.performance.model.PerfilCompensacaoSgmpTO;
import br.com.marcospcruz.performance.model.PistaTO;
import br.com.marcospcruz.performance.model.PracaTO;
import br.com.marcospcruz.performance.model.TransacaoTO;
import br.com.marcospcruz.performance.util.ConstantesEnum;
import br.com.marcospcruz.performance.util.Database;

public class TransacaoDao extends Database {

	public TransacaoDao() {

		super();

	}

	@Override
	public List<TransacaoTO> readAll(long timeInMillis1, long timeInMillis2)
			throws Exception {
		// TODO Auto-generated method stub

		String query = queriesProperties.getProperty(ConstantesEnum.SgapQueryName.getValue().toString());

		List<TransacaoTO> objetos = null;

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
	@SuppressWarnings("rawtypes")
	public List<TransacaoTO> populaObjetos(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub

		List<TransacaoTO> objetos = new ArrayList<TransacaoTO>();

		// MyLoggerSingleton.getInstance().writeLog(getClass(),
		// "Total of Records: " + totalRows);
		//
		// MyLoggerSingleton.getInstance().writeLog(getClass(), "Progress:\t");

		while (rs.next()) {

			TransacaoTO transacao = new TransacaoTO();

			transacao
					.setDataHoraTransacao(rs.getTimestamp("datahoratransacao"));

			transacao.setSequencial(rs.getInt("sequencial"));

			transacao.setPista(new PistaTO(rs.getString("idcomplementolocal")));

			transacao.getPista().setPraca(new PracaTO(rs.getInt("idLocal")));

			transacao.setValorPagamento(rs.getInt("valorpagamento"));

			transacao.setPlacaVeiculo(rs.getString("placaveiculo"));

			Date dataCompensacao = rs.getTimestamp("data_compensacao");

			if (dataCompensacao != null) {

				PerfilCompensacaoSgmpTO sgmpTrn = new PerfilCompensacaoSgmpTO(
						dataCompensacao);

				sgmpTrn.setIdClasseContrato(rs.getInt("idclassecontrato"));

				sgmpTrn.setSequencialLote(rs.getInt("sequenciallote"));

				sgmpTrn.setCodigoCompensacao(rs.getInt("idcodigocompensacao"));

				transacao.setPerfilCompensacaoSGMP(sgmpTrn);

			}

			objetos.add(transacao);
			//
			// float percentage = (objetos.size() / totalRows) * 100;
			//
			// if ((perkcentage % 10) == 0)
			//
			// MyLoggerSingleton.getInstance().writeLog(percentage + "%\t");

		}

		return objetos;
	}

}
