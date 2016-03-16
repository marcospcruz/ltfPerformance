package br.com.marcospcruz.performance.util;

public enum CodigoCompensacaoEnum {

	BlockedBalance(9), CrossReading(10), InvalidData(5), LowBalance(6), DebitedCreditLimit(
			8), Approved(0), AbsentDevice(1), BlockedDevice(2), Duplicated(3), LaterCompensated(
			4), OutOfTime(7);

	private final int value;

	private CodigoCompensacaoEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
