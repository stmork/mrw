package de.morknet.mrw.base;

import de.morknet.mrw.Modell;
import de.morknet.mrw.comm.Command;
import de.morknet.mrw.comm.MrwMessage;

/**
 * Diese Klasse verwaltet eine Lampe.
 * @author smork
 *
 */
public class Lampe extends DeviceUnit
{
	private static final long serialVersionUID = 1L;

	private int schwellwert;

	private int type;

	/**
	 * Initialisiert diese Lampe.
	 * @param modell Die Modelleisenbahn, auf der die Lampe angebracht ist.
	 * @param name Der Name der Lampe.
	 */
	public Lampe(final Modell modell, final String name, final int schwellwert, final int type)
	{
		super(modell, name);
		this.schwellwert = schwellwert;
		this.type = type;
	}

	/**
	 * Gibt den Schwellwert zurück, ab dem das Licht bei Unterschreiten einer Helligkeit eingeschaltet wird.
	 * @return Der Helligkeitsschwellwert.
	 */
	public int getSchwellwert()
	{
		return schwellwert;
	}

	/**
	 * Gibt den Lampentyp zurück.
	 * @return Der Lampentyp.
	 */
	public int getType()
	{
		return type;
	}

	@Override
	public MrwMessage createConfigMessage()
	{
		MrwMessage msg = null;

		if (type >= 0)
		{
			msg = MrwMessage.createCommandMsg(Command.CFGLGT, ctrl_id, unit_no);
	
			msg.addDataByte(pin);
			msg.addDataByte(schwellwert);
			msg.addDataByte(type);
		}
		return msg;
	}

	@Override
	protected Command getCommand()
	{
		return null;
	}

}
